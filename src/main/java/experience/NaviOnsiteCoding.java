package experience;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * The exchange follows a FirstInFirstOut Price-Time order-matching rule, which states that: "The first order in the order-book at a price level is
 * the first order matched. All orders at the same price level are filled according to time priority".
 * <p>
 * The exchange works like a market, lower selling prices and higher buying prices get priority.
 * <p>
 * A trade is executed when a buy price is greater than or equal to a sell price. The trade is recorded at the price of the sell order regardless of
 * the price of the buy order.
 * <p>
 * Got insta reject after this round, after discussing with Nilendu, realized that probably interviewer was looking for a priority queue based
 * solution. Although the time complexity of this solution is the same and all the tests also did pass, not sure why got insta rejected.
 * <p>
 * After reading the internet, best way would be to maintain two priority queues
 * 1. For buy (sorted based on price descending, use time for breaking ties)
 * 2. For sell (sorted based on price ascending, use time for breaking ties)
 * This pattern tries to sell at lowest price and buys at highest price possible, given the constraint the buy price >= sell price
 * Code would have been much simpler this way
 */
public class NaviOnsiteCoding {
    public TreeMap<String, TreeSet<OrderInfo>> buyTrades = new TreeMap<>(); //ticker -> set(order)
    public TreeMap<String, TreeSet<OrderInfo>> sellTrades = new TreeMap<>();
    Comparator<OrderInfo> comparator = (o1, o2) -> {
        if (o1.price == o2.price) {
            return o1.timeStamp.compareTo(o2.timeStamp);
        } else {
            return Double.compare(o1.price, o2.price);
        }
    };

    public void match(Trade trade, OrderInfo orderInfo) {
        if (trade.type == TradeType.BUY) {
            TreeSet<OrderInfo> pendingSell = sellTrades.computeIfAbsent(trade.ticker, __ -> new TreeSet<>(comparator));
            int quantityToFulfill = orderInfo.quantity;
            NavigableSet<OrderInfo> orderInfos = pendingSell.headSet(orderInfo, true); //sell orders <= current buy
            while (!orderInfos.isEmpty() && quantityToFulfill > 0) {
                OrderInfo smallestSellOrder = orderInfos.first(); //smallest sell order
                if (smallestSellOrder == null) { //if no trade can satisfy the buy order
                    break;
                }
                int currentQuantity = smallestSellOrder.quantity;
                sellTrades.get(trade.ticker).remove(smallestSellOrder); //remove the sell trade
                if (currentQuantity - quantityToFulfill > 0) {
                    //if current sell trade quantity is greater than buy trade insert back the left over quantity
                    sellTrades.computeIfAbsent(trade.ticker, __ -> new TreeSet<>(comparator))
                            .add(new OrderInfo(smallestSellOrder.orderId, smallestSellOrder.price, smallestSellOrder.timeStamp,
                                    currentQuantity - quantityToFulfill));
                }
                System.out.println("StockExchange.Trade matched " + smallestSellOrder.price + " " + smallestSellOrder.timeStamp + " " + Math.min(currentQuantity,
                        quantityToFulfill));
                quantityToFulfill -= currentQuantity;
            }
            if (quantityToFulfill > 0) {
                //if the trade could not be satisfied, insert back the unfulfilled quantity
                buyTrades.computeIfAbsent(trade.ticker, __ -> new TreeSet<>(comparator))
                        .add(new OrderInfo(orderInfo.orderId, orderInfo.price, orderInfo.timeStamp, quantityToFulfill));
            }
        } else {
            TreeSet<OrderInfo> pendingBuys = buyTrades.computeIfAbsent(trade.ticker, __ -> new TreeSet<>(comparator));
            int quantityToFulfill = orderInfo.quantity;
            NavigableSet<OrderInfo> orderInfos = pendingBuys.tailSet(orderInfo, true);
            while (!orderInfos.isEmpty() && quantityToFulfill > 0) {
                OrderInfo largestBuyOrder = orderInfos.last();
                if (largestBuyOrder == null) { //if no trade can satisfy the buy order
                    break;
                }
                int currentQuantity = largestBuyOrder.quantity;
                buyTrades.get(trade.ticker).remove(largestBuyOrder);
                if (currentQuantity - quantityToFulfill > 0) {
                    //if current buy trade quantity is greater than sell trade insert back the left over quantity
                    buyTrades.computeIfAbsent(trade.ticker, __ -> new TreeSet<>(comparator))
                            .add(new OrderInfo(largestBuyOrder.orderId, largestBuyOrder.price, largestBuyOrder.timeStamp,
                                    currentQuantity - quantityToFulfill));
                }
                System.out.println("StockExchange.Trade matched " + largestBuyOrder.price + " " + largestBuyOrder.timeStamp + " " + Math.min(currentQuantity,
                        quantityToFulfill));
                quantityToFulfill -= currentQuantity;
            }
            if (quantityToFulfill > 0) {
                //if the trade could not be satisfied, insert back the unfulfilled quantity
                sellTrades.computeIfAbsent(trade.ticker, __ -> new TreeSet<>(comparator))
                        .add(new OrderInfo(orderInfo.orderId, orderInfo.price, orderInfo.timeStamp, quantityToFulfill));
            }
        }
    }

    static class OrderInfo {
        int orderId;
        double price;
        String timeStamp; //Ideally LocalDateTime
        int quantity;

        public OrderInfo(int orderId, double price, String timeStamp, int quantity) {
            this.orderId = orderId;
            this.price = price;
            this.timeStamp = timeStamp;
            this.quantity = quantity;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            OrderInfo orderInfo = (OrderInfo) o;
            return orderId == orderInfo.orderId && Double.compare(orderInfo.price, price) == 0 && quantity == orderInfo.quantity && Objects.equals(timeStamp, orderInfo.timeStamp);
        }

        @Override
        public int hashCode() {
            return Objects.hash(orderId, price, timeStamp, quantity);
        }
    }

    static class Trade {
        String ticker;
        TradeType type;

        public Trade(String ticker, TradeType type) {
            this.ticker = ticker;
            this.type = type;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Trade trade = (Trade) o;
            return Objects.equals(ticker, trade.ticker) && type == trade.type;
        }

        @Override
        public int hashCode() {
            return Objects.hash(ticker, type);
        }
    }

    enum TradeType {
        BUY, SELL
    }
}
