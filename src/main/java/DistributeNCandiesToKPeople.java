/**
 * https://leetcode.com/problems/distribute-candies-to-people/
 * <p>
 * We distribute some number of candies, to a row of n = num_people people in the following way:
 * <p>
 * We then give 1 candy to the first person, 2 candies to the second person, and so on until we give n candies to the last person.
 * <p>
 * Then, we go back to the start of the row, giving n + 1 candies to the first person, n + 2 candies to the second person, a
 * nd so on until we give 2 * n candies to the last person.
 * <p>
 * This process repeats (with us giving one more candy each time, and moving to the start of the row after we reach the end) until we run out of candies.
 * The last person will receive all of our remaining candies (not necessarily one more than the previous gift).
 * <p>
 * Return an array (of length num_people and sum candies) that represents the final distribution of candies.
 * <p>
 * Input: candies = 7, num_people = 4
 * Output: [1,2,3,1]
 * Explanation:
 * On the first turn, ans[0] += 1, and the array is [1,0,0,0].
 * On the second turn, ans[1] += 2, and the array is [1,2,0,0].
 * On the third turn, ans[2] += 3, and the array is [1,2,3,0].
 * On the fourth turn, ans[3] += 1 (because there is only one candy left), and the final array is [1,2,3,1].
 */
public class DistributeNCandiesToKPeople {
    /**
     * Just simulate the distribution, my initial approach was similar but the implementation was crude
     */
    public int[] distributeCandies(int candies, int num_people) {
        int[] res = new int[num_people];
        int index = 0;
        //try giving 1, 2, 3, 4, 5, 6 ... candies until we run of candies
        while (candies > 0) {
            int toGive = Math.min(candies, index + 1); //to not give more candies than total candies
            res[index % num_people] += toGive;
            candies -= toGive;
            index++;
        }
        return res;
    }
}
