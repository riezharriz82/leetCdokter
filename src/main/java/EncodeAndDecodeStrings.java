import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/encode-and-decode-strings/
 * <p>
 * Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.
 * <p>
 * The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.
 */
public class EncodeAndDecodeStrings {
    char delimiter = 258; //since the input can have characters only from ascii 0 - 256, we can try with any ascii character > 256
    String delim = new String(new char[]{delimiter});

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        //There are multiple solutions, to solve this problem, it can be done by choosing a delimiter any escaping that
        //delimiter in the input string, eg. choose # as delimiter if input contains #, replace it by ##
        //separate two words by " # "
        //decode by reversing the process, split by " # " and then replace ## by #

        //Another generic solution is to use chunked transfer encoding used in http 1.1 in which we encode the length of chunk using fixed bytes
        //and then the chunk message
        //eg. if the input string is abcd efg hijkl, encode it to 0004abcd0003edfg0005hijkl
        //4 bytes were used to denote size of the preceding message followed by the message
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
            sb.append(delim);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        String[] split = s.split(delim, -1); //using -1 takes care of empty splits, otherwise empty splits would have been skipped
        //if you don't want to rely on -1, manually implement the split function, i did manual implementation in my initial implementation
        List<String> res = new ArrayList<>();
        for (int i = 0; i < split.length - 1; i++) {
            res.add(split[i]);
        }
        return res;
    }
}
