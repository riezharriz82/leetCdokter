import static org.junit.Assert.*;

import org.junit.Test;

public class TestLongestCommonPrefix {

	/**
	 * 
	 */
	@Test
	public void testLongestCommonPrefix() {
		assertTrue(new LongestCommonPrefix().longestCommonPrefix(new String[] { "" }) == "");
		assertTrue(new LongestCommonPrefix().longestCommonPrefix(new String[] { "flower", "flow", "flight" }).equals("fl"));
		assertTrue(new LongestCommonPrefix().longestCommonPrefix(new String[] { "abc", "cba"}).equals(""));
	}

}
