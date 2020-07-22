package alternate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * https://leetcode.com/problems/smallest-common-region/
 * <p>
 * You are given some lists of regions where the first region of each list includes all other regions in that list.
 * <p>
 * Naturally, if a region X contains another region Y then X is bigger than Y. Also by definition a region X contains itself.
 * <p>
 * Given two regions region1, region2, find out the smallest region that contains both of them.
 * <p>
 * If you are given regions r1, r2 and r3 such that r1 includes r3, it is guaranteed there is no r2 such that r2 includes r3.
 * <p>
 * It's guaranteed the smallest region exists.
 * <p>
 * Input:
 * regions = [["Earth","North America","South America"],
 * ["North America","United States","Canada"],
 * ["United States","New York","Boston"],
 * ["Canada","Ontario","Quebec"],
 * ["South America","Brazil"]],
 * region1 = "Quebec",
 * region2 = "New York"
 * Output: "North America"
 */
public class SmallestCommonRegion {
    //can probably be also done by building the tree and then find the LCA
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        HashMap<String, String> hm = new HashMap<>();
        for (List<String> item : regions) {
            String parent = item.get(0);
            for (int i = 1; i < item.size(); i++) {
                hm.put(item.get(i), parent); //store parent for each region
            }
        }

        HashSet<String> used = new HashSet<>(); //create a set of parents for the region1
        while (region1 != null) {
            used.add(region1);
            region1 = hm.get(region1);
        }

        //iteratively walk the parent graph for region2, first region present in both region1 and region2 is the smallest common region
        while (!used.contains(region2)) {
            region2 = hm.get(region2);
        }

        return region2;
    }
}
