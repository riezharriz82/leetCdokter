package experience;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Create a service that can allow any client to schedule any arbitrarily job that should be executed
 * at a specified date and time in future in a thread safe manner
 *
 * Initially I told that I will manage state in a hashmap, maintain a time -> job mapping
 * But then interviewer hinted to use PriorityQueue to make life easier
 */
class TaskSchedulerService {
    TaskScheduler taskScheduler = new TaskScheduler();

    public TaskSchedulerService() {
        new Thread(taskScheduler).start();
    }

    public void submitTask(TaskScheduler.Task task) {
        taskScheduler.submitTask(task);
    }

    public static class TaskScheduler implements Runnable {
        PriorityQueue<Task> queue = new PriorityQueue<>(Comparator.comparing(o -> o.scheduledTime));
        ReentrantLock lock = new ReentrantLock();

        public void submitTask(Task task) {
            lock.lock();
            System.out.println("Trying to add");
            queue.add(task);
            System.out.println("Size " + queue.size());
            lock.unlock();
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();
                if (!queue.isEmpty()) {
                    Task candidate = queue.peek();
                    System.out.println(LocalDateTime.now() + " Found a task " + candidate.scheduledTime + " " + candidate.scheduledTime.compareTo(LocalDateTime.now()));
                    if (candidate.scheduledTime.compareTo(LocalDateTime.now()) <= 0) {
                        //if the task can be executed, execute it in a different thread
                        queue.poll();
                        new Thread(candidate.task).start(); //Thread pool
                    }
                }
                lock.unlock();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public static class Task {
            int taskId;
            Runnable task;

            public Task(int taskId, Runnable task, LocalDateTime scheduledTime, int repeat) {
                this.taskId = taskId;
                this.task = task;
                this.scheduledTime = scheduledTime;
                this.repeat = repeat;
            }

            LocalDateTime scheduledTime;
            int repeat; //optional repeat delay
        }
    }
}

public class DirectiMachineCoding {
    public static void main(String[] args) {
        TaskSchedulerService taskSchedulerService = new TaskSchedulerService();
        TaskSchedulerService.TaskScheduler.Task task = new TaskSchedulerService.TaskScheduler.Task(1, () -> System.out.println("Hello World"), LocalDateTime.of(2021, Month.MARCH, 3, 15, 52, 45), 0);
        taskSchedulerService.submitTask(task);
    }
}
