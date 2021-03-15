import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/encode-and-decode-tinyurl/
 * <p>
 * TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl
 * and it returns a short URL such as http://tinyurl.com/4e9iAk.
 * <p>
 * Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work.
 * You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
 */
public class EncodeAndDecodeTinyURL {
    Map<String, String> map = new HashMap<>();
    int currentId;

    /**
     * Approach: In order to reduce the length, we need to change base to encode more information per character.
     * A simple way would be to encode in base 62. 26 characters for lower case, 26 for upper case and 10 for digits.
     * Now to encode it we would need to convert input url to an integer, we can do that by
     * 1. Using UUID
     * 2. Using MD5 hash - 128 bits, integer can only represent 64 bits, so need to use the first half of hash
     * 3. Keeping track of monotonically increasing identifier
     * 4. Generate a random 8 character encoded url and check whether it's available. If no, retry.
     * <p>
     * {@link EncodeAndDecodeStrings}
     */
    public String encode(String longUrl) {
        currentId++;
        String base62 = encodeIdToBase62();
        map.put(base62, longUrl);
        return base62;
    }

    private String encodeIdToBase62() {
        StringBuilder sb = new StringBuilder();
        int temp = currentId;
        while (temp != 0) {
            int rem = temp % 62;
            if (rem <= 26) {
                sb.append((char) ('a' + rem)); //gotcha, make sure to convert it to char
            } else if (rem <= 52) {
                sb.append((char) ('A' + (rem - 26)));
            } else {
                sb.append((char) ('0' + (rem - 52)));
            }
            temp /= 62;
        }
        return sb.reverse().toString();
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(shortUrl);
    }


}
