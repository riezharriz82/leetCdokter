/**
 * https://leetcode.com/problems/find-the-celebrity/
 * <p>
 * Suppose you are at a party with n people (labeled from 0 to n - 1), and among them, there may exist one celebrity.
 * The definition of a celebrity is that all the other n - 1 people know him/her, but he/she does not know any of them.
 * <p>
 * Now you want to find out who the celebrity is or verify that there is not one.
 * The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information about whether A knows B.
 * You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).
 * <p>
 * You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n).
 * There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.
 * <p>
 * Input: graph = [[1,1,0],[0,1,0],[1,1,1]]
 * Output: 1
 * Explanation: There are three persons labeled with 0, 1 and 2. graph[i][j] = 1 means person i knows person j, otherwise graph[i][j] = 0 means person i does not know person j.
 * The celebrity is the person labeled as 1 because both 0 and 2 know him but 1 does not know anybody.
 * <p>
 * Input: graph = [[1,0,1],[1,1,0],[0,1,1]]
 * Output: -1
 * Explanation: There is no celebrity.
 * <p>
 * Constraints:
 * n == graph.length
 * n == graph[i].length
 * 2 <= n <= 100
 * graph[i][j] is 0 or 1.
 * graph[i][i] == 1
 * <p>
 * Follow up: If the maximum number of allowed calls to the API knows is 3 * n, could you find a solution without exceeding the maximum number of calls?
 */
class Relation {
    boolean knows(int a, int b) {
        return false;
    }
}

public class FindTheCelebrity extends Relation {
    /**
     * Approach: Lower constraints indicate that n^2 solution would work, and it does work.
     * But in order to reduce time complexity to O(n), we need to eliminate a number every call.
     * Consider a candidate index i, if i knows j, then i can't be celebrity but j can be.
     * So we make j a new candidate index and repeat the process for remaining indices.
     * <p>
     * At the end, we verify whether our chosen candidate is actually a celebrity or not
     * <p>
     * {@link FindTheTownJudge} {@link MajorityElement2} related problem
     */
    public int findCelebrity(int n) {
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) { //if candidate knows i, then candidate can't be a celebrity, need to choose a new candidate
                candidate = i;
            }
            //if candidate does not knows i, it can still be a celebrity, do nothing
        }
        for (int i = 0; i < n; i++) {
            if (i != candidate) {
                //actually verify whether the candidate is a celebrity or not
                if (!knows(i, candidate) || knows(candidate, i)) {
                    return -1;
                }
            }
        }
        return candidate;
    }

    /**
     * Approach: Brute force, for each people, find how many people knows him. If it equals n-1, he is the celebrity
     */
    public int findCelebrityBruteForce(int n) {
        for (int i = 0; i < n; i++) {
            int peopleThatKnowsI = 0;
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    if (!knows(i, j) && knows(j, i)) {
                        peopleThatKnowsI++;
                    } else {
                        break;
                    }
                }
            }
            if (peopleThatKnowsI == n - 1) {
                return i;
            }
        }
        return -1;
    }
}

