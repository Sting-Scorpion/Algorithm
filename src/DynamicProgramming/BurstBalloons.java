package DynamicProgramming;

/* Leetcode 312 */
public class BurstBalloons {
    /**
     * 戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 的分数。
     * 如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
     * @param nums 每个气球的分数
     * @return 能获得的最大分数
     */
    public static int burstBalloons(int[] nums){
        int n = nums.length;
        int[] arr = new int[n + 2];
        arr[0] = 1;
        System.arraycopy(nums, 0, arr, 1, n);
        arr[n + 1] = 1;
        int[][] dp = new int[n + 2][n + 2];
        for(int i = 1; i <= n; i++){
            dp[i][i] = arr[i - 1] * arr[i] * arr[i + 1];
        }
        for(int l = n; l >= 1; l--){
            for(int r = l + 1; r <= n; r++){
                // 两端位置特殊
                int score = Math.max(
                        arr[l - 1] * arr[l] * arr[r + 1] + dp[l + 1][r], // 最后打 l 位置的气球
                        arr[l - 1] * arr[r] * arr[r + 1] + dp[l][r - 1] // 最后打 r 位置的气球
                );
                for(int k = l + 1; k < r; k++){
                    score = Math.max(score, arr[l - 1] * arr[k] * arr[r + 1] + dp[l][k - 1] + dp[k + 1][r]);
                }
                dp[l][r] = score;
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 5, 8};
        /*
         * 分析：
         * {3, 1, 5, 8} -> {3, 5, 8} -> {3, 8} -> {8} -> {}
         * 3 * 1 * 5 + 3 * 5 * 8 + 1 * 3 * 8 + 1 * 8 * 1
         * = 15 + 120 + 24 + 8
         * = 167
         */
        System.out.println(burstBalloons(nums));
    }
}
