import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/search-suggestions-system/
 * <p>
 * Given an array of strings products and a string searchWord. We want to design a system that suggests at most three product names
 * from products after each character of searchWord is typed. Suggested products should have common prefix with the searchWord.
 * If there are more than three products with a common prefix return the three lexicographically minimums products.
 * <p>
 * Return list of lists of the suggested products after each character of searchWord is typed.
 * <p>
 * Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
 * Output: [
 * ["mobile","moneypot","monitor"],
 * ["mobile","moneypot","monitor"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"]
 * ]
 * Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
 * After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
 * After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
 */
public class SearchSuggestionsSystem {
    Trie root = new Trie();

    /**
     * Approach: Build a Trie from the products and store all the products ending at the current prefix directly in Trie to avoid DFS
     * <p>
     * {@link DesignSearchAutocompleteSystem} {@link WordSearch2}
     */
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        for (String product : products) {
            addProduct(product);
        }
        ArrayList<List<String>> result = new ArrayList<>();
        Trie temp = root;
        for (int i = 0; i < searchWord.length(); i++) {
            char c = searchWord.charAt(i);
            if (temp.letters[c - 'a'] == null) { //if current word is not present, add empty list till the end of the searchWord
                while (i < searchWord.length()) {
                    result.add(new ArrayList<>());
                    i++;
                }
                break;
            }
            temp = temp.letters[c - 'a'];
            List<String> allProducts = temp.products;
            //find the top 3 product
            Collections.sort(allProducts);
            ArrayList<String> top3Products = new ArrayList<>();
            for (int j = 0; j < 3 && j < allProducts.size(); j++) {
                top3Products.add(allProducts.get(i));
            }
            result.add(top3Products);
        }
        return result;
    }

    private void addProduct(String product) {
        Trie temp = root;
        for (char c : product.toCharArray()) {
            if (temp.letters[c - 'a'] == null) {
                temp.letters[c - 'a'] = new Trie();
            }
            temp = temp.letters[c - 'a'];
            temp.products.add(product); // insert the product name directly in trie node
        }
    }

    private static class Trie {
        Trie[] letters = new Trie[26];
        List<String> products = new ArrayList<>();
    }
}
