import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/design-browser-history/
 * <p>
 * You have a browser of one tab where you start on the homepage and you can visit another url,
 * get back in the history number of steps or move forward in the history number of steps.
 * <p>
 * Implement the BrowserHistory class:
 * <p>
 * BrowserHistory(string homepage) Initializes the object with the homepage of the browser.
 * void visit(string url) Visits url from the current page. It clears up all the forward history.
 * string back(int steps) Move steps back in history. If you can only return x steps in the history and steps > x, you will return only x steps.
 * Return the current url after moving back in history at most steps.
 * string forward(int steps) Move steps forward in history. If you can only forward x steps in the history and steps > x, you will forward only x steps.
 * Return the current url after forwarding in history at most steps.
 * <p>
 * Input:
 * ["BrowserHistory","visit","visit","visit","back","back","forward","visit","forward","back","back"]
 * [["leetcode.com"],["google.com"],["facebook.com"],["youtube.com"],[1],[1],[1],["linkedin.com"],[2],[2],[7]]
 * Output:
 * [null,null,null,null,"facebook.com","google.com","facebook.com",null,"linkedin.com","google.com","leetcode.com"]
 * <p>
 * Explanation:
 * BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
 * browserHistory.visit("google.com");       // You are in "leetcode.com". Visit "google.com"
 * browserHistory.visit("facebook.com");     // You are in "google.com". Visit "facebook.com"
 * browserHistory.visit("youtube.com");      // You are in "facebook.com". Visit "youtube.com"
 * browserHistory.back(1);                   // You are in "youtube.com", move back to "facebook.com" return "facebook.com"
 * browserHistory.back(1);                   // You are in "facebook.com", move back to "google.com" return "google.com"
 * browserHistory.forward(1);                // You are in "google.com", move forward to "facebook.com" return "facebook.com"
 * browserHistory.visit("linkedin.com");     // You are in "facebook.com". Visit "linkedin.com"
 * browserHistory.forward(2);                // You are in "linkedin.com", you cannot move forward any steps.
 * browserHistory.back(2);                   // You are in "linkedin.com", move back two steps to "facebook.com" then to "google.com". return "google.com"
 * browserHistory.back(7);                   // You are in "google.com", you can move back only one step to "leetcode.com". return "leetcode.com"
 */
public class DesignBrowserHistory {
    List<String> urls = new ArrayList<>();
    int curIndex; //tells us where we at in the list
    int maxIndex; //tells us the max index we can go in the list, size of the list > maxIndex, because we overwrite the list

    /**
     * Approach: Use list to allow us to jump to any index in O(1) time, can also use hashmap to do the same
     * Other suboptimal approaches are to use Doubly linked list or two stacks (one for forward history, push to it when going back, and another
     * for back history, push to it when visiting)
     * <p>
     * {@link DesignPhoneDirectorySimplified} {@link DesignALeaderboard} {@link DesignSearchAutocompleteSystem} related design problems
     */
    public DesignBrowserHistory(String homepage) {
        urls.add(homepage);
    }

    public void visit(String url) {
        if (curIndex + 1 < urls.size()) {
            //if the new index is within bounds of the list, overwrite the index
            urls.set(curIndex + 1, url);
        } else {
            //add the url at the end
            urls.add(url);
        }
        curIndex++;
        maxIndex = curIndex; //visiting an url always resets the maxIndex, so you can't go beyond this url
    }

    public String back(int steps) {
        int targetIndex = Math.max(curIndex - steps, 0); //ensures that we don't go out of bounds
        String url = urls.get(targetIndex);
        curIndex = targetIndex; //don't forget to update the current index
        return url;
    }

    public String forward(int steps) {
        int targetIndex = Math.min(curIndex + steps, maxIndex); //ensures that we don't go beyond maxIndex
        String url = urls.get(targetIndex);
        curIndex = targetIndex;
        return url;
    }
}
