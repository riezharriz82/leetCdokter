import java.util.Random;

/**
 * https://leetcode.com/problems/generate-random-point-in-a-circle/
 * <p>
 * Given the radius and x-y positions of the center of a circle, write a function randPoint which generates a uniform random point in the circle.
 * <p>
 * Note:
 * input and output values are in floating-point.
 * radius and x-y position of the center of the circle is passed into the class constructor.
 * a point on the circumference of the circle is considered to be in the circle.
 * randPoint returns a size 2 array containing x-position and y-position of the random point, in that order.
 * <p>
 * Input:
 * ["Solution","randPoint","randPoint","randPoint"]
 * [[1,0,0],[],[],[]]
 * Output: [null,[-0.72939,-0.65505],[-0.78502,-0.28626],[-0.83119,-0.19803]]
 * <p>
 * Input:
 * ["Solution","randPoint","randPoint","randPoint"]
 * [[10,5,-7.5],[],[],[]]
 * Output: [null,[11.52438,-8.33273],[2.46992,-16.21705],[11.13430,-12.42337]]
 */
public class GenerateRandomPointInACircle {
    /**
     * Approach: Rejection Sampling, choosing random points in a circle directly is tough, instead we can use we can generate
     * random points in a square and then reject points that are not inside a circle.
     * <p>
     * A caveat is that generating a random double returns a random double between 0 and 1, hence we need to offset the ratio from the
     * smallestX and smallestY allowed.
     * <p>
     * {@link RandomPickWithWeight} {@link LinkedListRandomNode} {@link Rand10FomRand7} related math problems related to picking random values
     */

    double minX, maxX, minY, maxY, centreX, centreY, radius;

    public GenerateRandomPointInACircle(double radius, double x_center, double y_center) {
        centreX = x_center;
        centreY = y_center;
        minX = x_center - radius;
        maxX = x_center + radius;
        minY = y_center - radius;
        maxY = y_center + radius;
        this.radius = radius;
    }

    public double[] randPoint() {
        //rejection sampling, if the generated random point lies outside the circle, reject it
        while (true) {
            double xOffset = new Random().nextDouble();
            double yOffset = new Random().nextDouble();
            //xOffset is between [0,1). If the x range is from [6,15], we need to properly offset it
            //e.g if xOffset was 0.5, then we should convert it to 6 + (15-6)*0.5 = 10.5
            double randomX = minX + ((maxX - minX) * xOffset);
            double randomY = minY + ((maxY - minY) * yOffset);
            if (validPoint(randomX, randomY)) {
                return new double[]{randomX, randomY};
            }
        }
    }

    private boolean validPoint(double x, double y) {
        //put x, y in the circle equation
        return ((x - centreX) * (x - centreX)) + ((y - centreY) * (y - centreY)) - (radius * radius) <= 0;
    }
}
