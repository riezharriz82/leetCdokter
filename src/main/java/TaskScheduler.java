import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/task-scheduler/
 * You are given a char array representing tasks CPU need to do. It contains capital letters A to Z where each letter
 * represents a different task. Tasks could be done without the original order of the array.
 * Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.
 * <p>
 * However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array),
 * that is that there must be at least n units of time between any two same tasks.
 * <p>
 * You need to return the least number of units of times that the CPU will take to finish all the given tasks.
 * <p>
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation:
 * A -> B -> idle -> A -> B -> idle -> A -> B
 * There is at least 2 units of time between any two same tasks.
 */
public class TaskScheduler {
    /**
     * Problem is similar to rearranging a string where similar characters are at least k distance apart (leetcode hard question)
     * <p>
     * Approach: Start with the most frequent task, place it in the result, now you can't place the same task in the cooldown period ie. n
     * What do you do in between? Try to get the remaining tasks (if any) for the next n cycle.
     * Decrement the task count and put it again in the queue to be picked up in the future
     */
    public int leastInterval(char[] tasks, int n) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }
        //max heap of task frequency
        PriorityQueue<Pair<Character, Integer>> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()));
        map.forEach((k, v) -> {
            pq.add(new Pair<>(k, v));
        });
        int res = 0;
        while (!pq.isEmpty()) {
            List<Pair<Character, Integer>> processedTasks = new ArrayList<>();
            //keep track of processed tasks so that it can be decremented and put it back
            int timeSpent = 0;
            Pair<Character, Integer> top = pq.poll();
            timeSpent++;
            processedTasks.add(top);
            //same task can't be picked up till the cooldown is completed, so try to see if any other tasks can be picked up meanwhile
            for (int i = 0; i < n; i++) {
                if (!pq.isEmpty()) {
                    top = pq.poll();
                    processedTasks.add(top);
                    timeSpent++;
                }
            }
            for (Pair<Character, Integer> processedTask : processedTasks) {
                if (processedTask.getValue() > 1) {
                    pq.add(new Pair<>(processedTask.getKey(), processedTask.getValue() - 1));
                }
            }
            if (pq.isEmpty()) { //end condition, when all elements are processed, just add the time spent after the last cool down
                res += timeSpent;
            } else {
                res += n + 1; //cool down time + 1 (the first task which was picked)
            }
        }
        return res;
    }
}
