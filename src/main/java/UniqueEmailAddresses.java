import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/unique-email-addresses/
 * <p>
 * Every email consists of a local name and a domain name, separated by the @ sign.
 * <p>
 * For example, in alice@leetcode.com, alice is the local name, and leetcode.com is the domain name.
 * <p>
 * Besides lowercase letters, these emails may contain '.'s or '+'s.
 * <p>
 * If you add periods ('.') between some characters in the local name part of an email address, mail sent there will be forwarded
 * to the same address without dots in the local name.  For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.
 * (Note that this rule does not apply for domain names.)
 * <p>
 * If you add a plus ('+') in the local name, everything after the first plus sign will be ignored. This allows certain emails to be filtered,
 * for example m.y+name@email.com will be forwarded to my@email.com.  (Again, this rule does not apply for domain names.)
 * <p>
 * It is possible to use both of these rules at the same time.
 * <p>
 * Given a list of emails, we send one email to each address in the list.  How many different addresses actually receive mails?
 * <p>
 * Input: ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
 * Output: 2
 * Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails
 */
public class UniqueEmailAddresses {
    /**
     * Approach: Straight forward string parsing question, Google loves string parsing question !
     * Whenever special characters like . and + is encountered, check whether domain name is being parsed or not.
     */
    public int numUniqueEmails(String[] emails) {
        Set<String> uniqueEmails = new HashSet<>();
        for (String email : emails) {
            int index = 0;
            String parsedEmail = "";
            boolean isDomainName = false;
            while (index < email.length()) {
                char c = email.charAt(index);
                if (c == '.') {
                    if (isDomainName) {
                        parsedEmail += c;
                    }
                    index++;
                } else if (c == '+') {
                    if (isDomainName) {
                        parsedEmail += c;
                        index++;
                    } else {
                        while (email.charAt(index) != '@') {
                            index++;
                        }
                    }
                } else if (c == '@') {
                    parsedEmail += c;
                    index++;
                    isDomainName = true;
                } else {
                    parsedEmail += c;
                    index++;
                }
            }
            uniqueEmails.add(parsedEmail);
        }
        return uniqueEmails.size();
    }
}
