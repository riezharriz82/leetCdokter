import java.util.*;

/**
 * https://leetcode.com/problems/repeated-dna-sequences/
 * <p>
 * All DNA is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T', for example: "ACGAATTCCG".
 * When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 * <p>
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 * <p>
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * Output: ["AAAAACCCCC","CCCCCAAAAA"]
 * <p>
 * Input: s = "AAAAAAAAAAAAA"
 * Output: ["AAAAAAAAAA"]
 */
public class RepeatedDNASequence {

    /**
     * Approach: Use rolling hash to identify whether a substring is repeated in o(1) time, in my original solution i used to create substring
     * and then update the count, creating substring is linear time complexity.
     * <p>
     * To visualize the rolling hash try to think of it as below
     * Given string 256785, we need to generate all integer substrings of size 3 i.e. 256, 567, 678, 785
     * Once we have 256, we can generate 567 in constant time using maths
     * 256 - 2 * 100 = 56
     * 56 * 10 = 560
     * 560 + 7 = 567
     * <p>
     * We follow the same approach for creating rolling hash for this problem as well, here we map a,t,g,c to an unique integer
     * There is no possibility of overflow because 4^10 lies within int bounds
     */
    public List<String> findRepeatedDnaSequencesRollingHash(String s) {
        if (s.length() < 10) {
            return new ArrayList<>();
        }
        Set<Integer> uniqueHashes = new HashSet<>();
        Set<String> repeatedStrings = new HashSet<>();
        int currentHash = 0;
        int[] mapping = new int[26];
        mapping['A' - 'A'] = 0;
        mapping['T' - 'A'] = 1;
        mapping['G' - 'A'] = 2;
        mapping['C' - 'A'] = 3;
        for (int i = 0; i < 10; i++) {
            currentHash = 4 * currentHash + mapping[s.charAt(i) - 'A']; //for string ATGC = A*4^3 + T*4^2 + G*4 + C*1
        }
        uniqueHashes.add(currentHash);
        for (int i = 10; i < s.length(); i++) {
            currentHash -= (mapping[s.charAt(i - 10) - 'A'] * (int) Math.pow(4, 9)); //subtract the hash contributed by the first digit
            currentHash = 4 * currentHash + mapping[s.charAt(i) - 'A']; //add the new contribution of the current digit
            if (!uniqueHashes.add(currentHash)) { //if current hash is already present in uniqueHashes, then current hash is a duplicate
                repeatedStrings.add(s.substring(i - 9, i + 1));
            }
        }
        return new ArrayList<>(repeatedStrings);
    }

    /**
     * Keep track of count of occurrences of each substring, add to the result if a substring occurred twice
     */
    public List<String> findRepeatedDnaSequences(String s) {
        if (s.length() < 10) {
            return new ArrayList<>();
        }
        Map<String, Integer> map = new HashMap<>();
        List<String> repeatedStrings = new ArrayList<>();
        int begin = 0, end = 9;
        while (end < s.length()) {
            String candidate = s.substring(begin, end + 1);
            map.put(candidate, map.getOrDefault(candidate, 0) + 1);
            end++;
            begin++;
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                repeatedStrings.add(entry.getKey());
            }
        }
        return repeatedStrings;
    }
}
