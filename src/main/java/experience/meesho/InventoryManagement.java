package experience.meesho;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class InventoryManagement {

    private static final int TTL = 500; //milliseconds
    /**
     * Problem statement: https://drive.google.com/file/d/1AWJHmMOSLdcRTFT2MSmOlm5GlLsOJIoi/view
     * <p>
     * Design Inventory management system, which allows to block specific inventory of a specific productId for some time, before a payment is confirmed.
     * In case payment isn't confirmed after a specific amount of time, the blocked inventories are released.
     * Ensure thread safe operations keeping higher throughput in mind.
     * <p>
     * ~1.30 hours given for implementation. Platform used: Hackerrank.
     * Had a tough time dealing with @equals() and @hashCode() for custom classed. Copied the generated code from IntelliJ
     * Didn't implement separate read/write lock per product and didn't used PriorityBlockingQueue and ConcurrentHashMap. Rest everything is similar.
     * Was able to demo the code successfully.
     */
    Map<String, Product> inventory = new ConcurrentHashMap<>(); //productId -> product
    Map<String, Order> orderInfo = new ConcurrentHashMap<>(); //orderId -> order
    //No direct ConcurrentHashSet<>(), instead rely on keySet() of ConcurrentHashMap<>();
    //https://stackoverflow.com/questions/6992608/why-there-is-no-concurrenthashset-against-concurrenthashmap
    Set<Order> confirmedOrders = new ConcurrentHashMap<Order, Boolean>().keySet(); //set of confirmed orders, required by cleaner thread to ignore already confirmed orders
    //pending orders sorted in increasing order of expiry time, this ensures the earliest order is processed first
    PriorityBlockingQueue<PendingOrder> pendingOrders = new PriorityBlockingQueue<>(100, (o1, o2) -> Long.compare(o1.expiryTime, o2.expiryTime));
    Map<String, ReentrantReadWriteLock> locks = new ConcurrentHashMap<>(); //productId -> lock, required to perform thread safe operations on a product
    //supports read and write lock for better throughput

    public static void main(String[] args) throws InterruptedException {
        InventoryManagement inventoryManagement = new InventoryManagement();
        inventoryManagement.startProcessingExpiredOrder(); //start the background cleaner thread
        inventoryManagement.createProduct("1", "test", 100);
        System.out.println("Original quantity: " + inventoryManagement.getInventory("1"));
        inventoryManagement.blockInventory("1", 20, "order-1");
        System.out.println("After blocking 20 quantities: " + inventoryManagement.getInventory("1"));
        inventoryManagement.confirmOrder("order-1");
        Thread.sleep(1000);
        System.out.println("After releasing inventory : " + inventoryManagement.getInventory("1"));
    }

    public void createProduct(String productId, String name, int count) {
        Product product = new Product(name, count);
        inventory.put(productId, product);
        locks.put(productId, new ReentrantReadWriteLock());
    }

    public int getInventory(String productId) {
        ReentrantReadWriteLock.ReadLock readLock = locks.get(productId).readLock();
        try {
            readLock.lock(); //acquire a read lock
            return inventory.get(productId).availableCount;
        } finally {
            readLock.unlock();
        }
    }

    public void blockInventory(String productId, int count, String orderId) {
        Product product = inventory.get(productId);
        ReentrantReadWriteLock.WriteLock writeLock = locks.get(productId).writeLock();
        try {
            writeLock.lock(); //acquire a write lock as inventory needs to be updated
            if (product.availableCount < count) {
                throw new RuntimeException("Available inventory " + product.availableCount + " is less than inventory requested " + count);
            }
            product.availableCount -= count;
        } finally {
            writeLock.unlock();
        }
        Order order = new Order(productId, count);
        orderInfo.put(orderId, order);
        long currentTimeMillis = System.currentTimeMillis();
        long expiryTimeMillis = currentTimeMillis + TTL;
        //Put the order in pending orders queue
        pendingOrders.add(new PendingOrder(expiryTimeMillis, order));
    }

    public void confirmOrder(String orderId) {
        Order order = orderInfo.get(orderId);
        confirmedOrders.add(order);
    }

    public void startProcessingExpiredOrder() {
        ReleaseInventory releaseInventory = new ReleaseInventory(pendingOrders);
        new Thread(releaseInventory).start();
    }

    private static class PendingOrder {
        long expiryTime;
        Order order;

        public PendingOrder(long expiryTime, Order order) {
            this.expiryTime = expiryTime;
            this.order = order;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PendingOrder that = (PendingOrder) o;
            return expiryTime == that.expiryTime && Objects.equals(order, that.order);
        }

        @Override
        public int hashCode() {
            return Objects.hash(expiryTime, order);
        }
    }

    private static class Order {
        String productId;
        int quantity;

        public Order(String productId, int quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Order order = (Order) o;
            return quantity == order.quantity && Objects.equals(productId, order.productId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(productId, quantity);
        }
    }

    private static class Product {
        String name;
        int availableCount;

        public Product(String name, int availableCount) {
            this.name = name;
            this.availableCount = availableCount;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Product product = (Product) o;
            return availableCount == product.availableCount && Objects.equals(name, product.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, availableCount);
        }
    }

    private class ReleaseInventory implements Runnable {
        PriorityBlockingQueue<PendingOrder> pendingOrders;

        public ReleaseInventory(PriorityBlockingQueue<PendingOrder> pendingOrders) {
            this.pendingOrders = pendingOrders;
        }

        @Override
        public void run() {
            while (true) {
                while (pendingOrders.isEmpty()) {
                    //while there is no pending orders, busy sleep
                    //can be optimized to use wait() and notify()
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Pending orders: " + pendingOrders.size());
                while (!pendingOrders.isEmpty() && pendingOrders.peek().expiryTime <= System.currentTimeMillis()) { //if this order is expired, process it
                    PendingOrder pendingOrder = pendingOrders.remove();
                    if (!confirmedOrders.contains(pendingOrder.order)) {
                        //if order is not confirmed yet
                        Order order = pendingOrder.order;
                        Product product = inventory.get(order.productId);
                        product.availableCount += order.quantity;
                        System.out.println("Released " + order.quantity + " quantities of " + product.name);
                    }
                }
            }
        }
    }
}
