import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/k-closest-points-to-origin/
 * <p>
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 * <p>
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 * <p>
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 * <p>
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 */
public class KClosestPointToOrigin {
    public int[][] kClosestUsingPriorityQueue(int[][] points, int K) {
        PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparingInt(o -> -o.distance)); //notice the negative sign, indicating max heap
        for (int[] point : points) {
            int x = point[0];
            int y = point[1];
            int distance = x * x + y * y;
            if (pq.size() < K) {
                pq.add(new Point(x, y, distance));
            } else {
                if (pq.peek().distance > distance) {
                    //need only k closest points, found a closer point, hence removing the farthest point
                    pq.remove();
                    pq.add(new Point(x, y, distance));
                }
            }
        }
        int[][] result = new int[K][2];
        int index = K - 1;
        while (index >= 0) { //the problem accepts answers in any order, still returning in correct order
            Point farthestPoint = pq.remove();
            result[index][0] = farthestPoint.x;
            result[index--][1] = farthestPoint.y;
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
        //problem is asking to return them in any order
        return Arrays.copyOfRange(points, 0, K);
    }

    private static class Point {
        int x;
        int y;
        int distance;

        public Point(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
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
