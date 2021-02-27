/**
 * https://leetcode.com/problems/find-the-town-judge/
 * <p>
 * In a town, there are N people labelled from 1 to N.  There is a rumor that one of these people is secretly the town judge.
 * <p>
 * If the town judge exists, then:
 * <p>
 * The town judge trusts nobody.
 * Everybody (except for the town judge) trusts the town judge.
 * There is exactly one person that satisfies properties 1 and 2.
 * You are given trust, an array of pairs trust[i] = [a, b] representing that the person labelled a trusts the person labelled b.
 * <p>
 * If the town judge exists and can be identified, return the label of the town judge.  Otherwise, return -1.
 * <p>
 * Input: N = 2, trust = [[1,2]]
 * Output: 2
 * <p>
 * Input: N = 3, trust = [[1,3],[2,3]]
 * Output: 3
 */
public class FindTheTownJudge {
    /**
     * Consider each person as nodes in graph, town judge would have in-degree - out-degree = N - 1
     */
    public int findJudge(int N, int[][] trust) {
        int[] counter = new int[N + 1];
        for (int[] edges : trust) {
            counter[edges[1]]++; //increase the indegree
            counter[edges[0]]--; //decrease the outdegree
        }
        for (int i = 0; i <= N; i++) {
            if (counter[i] == N - 1) {
                return i;
            }
        }
        return -1;
    }
}
