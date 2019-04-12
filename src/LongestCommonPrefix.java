
public class LongestCommonPrefix {

	public String longestCommonPrefix(String[] strs) {
		int min = Integer.MAX_VALUE;
		for ( int i = 0; i < strs.length; i++) {
			if (strs[i].length() < min )
				min = strs[i].length();
		}
		
		String ans = "";
		for (int i = 0; i < min; i++ ) {
			int stringsMatched = 1;
			for (int j = 1; j < strs.length; j++ ) {
				
				if (strs[j].charAt(i) == strs[0].charAt(i) ){
					stringsMatched++;
				}
			}
			if ( stringsMatched == strs.length )
				ans += strs[0].charAt(i);
			else
				break;
		}
		return ans;
	}
}
