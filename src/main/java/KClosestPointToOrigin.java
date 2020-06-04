import javafx.util.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/k-closest-points-to-origin/
 */
public class KClosestPointToOrigin {
    public int[][] kClosestUsingPriorityQueue(int[][] points, int K) {
        long dist;
        PriorityQueue<Pair<Long, Pair<Integer, Integer>>> priorityQueue = new PriorityQueue<>(K, new Comparator<Pair<Long, Pair<Integer, Integer>>>() {
            @Override
            public int compare(Pair<Long, Pair<Integer, Integer>> o1, Pair<Long, Pair<Integer, Integer>> o2) {
                return (int) (o2.getKey() - o1.getKey());
            }
        });
        for (int i = 0; i < points.length; i++) {
            int x = points[i][0];
            int y = points[i][1];
            dist = x * x + y * y;
            if (priorityQueue.isEmpty())
                priorityQueue.add(new Pair<>(dist, new Pair<>(x, y)));
            else if (priorityQueue.size() == K) { //queue is full, need to remove element if we have found a smaller distance
                if (priorityQueue.peek().getKey() > dist) { //we have found a smaller distance
                    priorityQueue.poll();
                    priorityQueue.add(new Pair<>(dist, new Pair<>(x, y)));
                }
            } else {
                priorityQueue.add(new Pair<>(dist, new Pair<>(x, y)));
            }
        }
        int[][] result = new int[K][2];
        int tmp = 0;
        for (Pair<Long, Pair<Integer, Integer>> k : priorityQueue) {
            result[tmp][0] = k.getValue().getKey();
            result[tmp][1] = k.getValue().getValue();
            tmp++;
        }
        return result;
    }

    //This solution is preferred since its complexity is linear
    public int[][] kClosestUsingQuickSelect(int[][] points, int K) {
        int l = 0, len = points.length, r = len - 1;
        while (l <= r) {
            int mid = partition(points, l, r);
            if (mid == K)
                break;
            else if (mid < K) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }

        }
        return Arrays.copyOfRange(points, 0, K);
    }

    private int partition(int[][] points, int l, int r) {
        int pivot = distance(points[r]);
        int i = l;
        for (int tmp = l; tmp < r; tmp++) {
            if (distance(points[tmp]) < pivot) {
                swap(points, tmp, i);
                i++;
            }
        }
        swap(points, i, r);
        return i;

    }

    private void swap(int[][] points, int i, int j) {
        int[] tmp = points[i];
        points[i] = points[j];
        points[j] = tmp;
    }

    private int distance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}
