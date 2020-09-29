import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PartitionKEqualSumSubsetsTest {
    @Test
    public void test() {
        Assertions.assertTrue(new PartitionKEqualSumSubsets().canPartitionKSubsets(new int[]{960, 3787, 1951, 5450, 4813, 752, 1397, 801, 1990, 1095, 3643, 8133, 893, 5306, 8341, 5246}, 6));
        Assertions.assertTrue(new PartitionKEqualSumSubsets().canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4));
    }
}
