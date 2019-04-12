
public class PalindromeNumber {

	public boolean isPalindrome(int x) {
		int original = x;
		if (x < 0)
			return false;
		if (x == 0)
			return true;

		String res = "";
		while (x != 0) {
			res += x % 10;
			x /= 10;
		}
		try {
			int reverse = Integer.valueOf(res);
			return reverse == original;
		} catch (Exception ex) {
			return false;
		}

	}
}
