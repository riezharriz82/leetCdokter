/**
 * https://leetcode.com/problems/high-five/
 * <p>
 * Given a list of scores of different students, return the average score of each student's top five scores in the order of each student's id.
 * <p>
 * Each entry items[i] has items[i][0] the student's id, and items[i][1] the student's score.  The average score is calculated using integer division.
 * <p>
 * Input: [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
 * Output: [[1,87],[2,88]]
 * Explanation:
 * The average of the student with id = 1 is 87.
 * The average of the student with id = 2 is 88.6. But with integer division their average converts to 88.
 * <p>
 * Note:
 * 1 <= items.length <= 1000
 * items[i].length == 2
 * The IDs of the students is between 1 to 1000
 * The score of the students is between 1 to 100
 * For each student, there are at least 5 scores
 */
public class HighFive {
    /**
     * Approach: Need top 5 scores for each student, can find it using priority queue but given the constraints, we can use humble
     * counting sort to do it in linear time.
     * Care must be taken to pick as many scores that are needed and can be picked up
     */
    public int[][] highFive(int[][] items) {
        int[][] studentScores = new int[1001][101];
        int highestId = 0;
        for (int[] item : items) {
            int studentId = item[0];
            int score = item[1];
            studentScores[studentId][score]++;
            highestId = Math.max(highestId, studentId);
        }
        int[][] res = new int[highestId][2];
        for (int id = 1; id <= highestId; id++) {
            int sum = 0, need = 5;
            for (int score = 100; score >= 1; score--) {
                int freq = studentScores[id][score];
                if (freq > 0) {
                    int pick = Math.min(freq, need); //tricky condition, don't pick more than needed or more than available
                    sum += (pick * score);
                    need -= pick;
                }
                if (need == 0) {
                    break;
                }
            }
            res[id - 1][0] = id;
            res[id - 1][1] = sum / 5;
        }
        return res;
    }
}
