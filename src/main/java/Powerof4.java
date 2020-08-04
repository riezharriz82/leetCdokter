public class Powerof4 {
    //https://leetcode.com/problems/power-of-four/
    public boolean isPowerOfFour(int num) {
        //n & (n-1) == 0 resets the rightmost set bit and checks that there is only one bit set
        //And'ing the number with 1010101010101010101010101010101 allows us to check the location of the set bit
        return (num & (num - 1)) == 0 && (num & 0b1010101010101010101010101010101) != 0;
    }

    //https://leetcode.com/problems/power-of-two
    public boolean isPowerOfTwo(int num) {
        return (num & (num - 1)) == 0;
    }

    //https://leetcode.com/problems/power-of-three/solution/
    public boolean isPowerOfThree(int n) {
        //numbers in radix 10 and are power of 10 are 1, 10, 100, 10000
        //number in radix 2 (binary) which are power of 2 are  1, 10, 100, 1000
        //numbers in radix k which are power of k are 1, 10, 100, 1000
        return Integer.toString(n, 3).matches("10*$");
    }
}
