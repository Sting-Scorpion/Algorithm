package DynamicProgramming;

public class Rob {
    /**
     * Leetcode 213
     * 所有的房屋都围成一圈，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * @param nums 房屋内的金额
     * @return 不触动警报装置的情况下，能够偷窃到的最高金额
     */
    public static int robCircle(int[] nums){
        int n = nums.length;
        // 不偷 0，则为 [1, (n - 1)] 的打家劫舍
        int dp0 = 0;
        int dp1 = 0;
        for(int i = 1; i < n; i++){
            int t = Math.max(dp1, dp0 + nums[i]);
            dp0 = dp1;
            dp1 = t;
        }
        int re1 = dp1;
        // 偷 0，则为 [2, n - 2] 的打家劫舍
        dp0 = nums[0];
        dp1 = nums[0];
        for(int i = 2; i < n - 1; i++){
            int t = Math.max(dp1, dp0 + nums[i]);
            dp0 = dp1;
            dp1 = t;
        }
        int re2 = dp1;
        return Math.max(re1, re2);
    }

    /**
     * Leetcode 198
     * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * @param nums 房屋内的金额
     * @return 不触动警报装置的情况下，能够偷窃到的最高金额
     */
    public static int rob(int[] nums){
        int n = nums.length;
        int dp0 = 0;
        int dp1 = nums[0];
        for(int i = 1; i < n; i++){
            int t = Math.max(dp1, dp0 + nums[i]);
            dp0 = dp1;
            dp1 = t;
        }
        return dp1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        /*
         * 分析：
         * {1, 2, 3, 1, (1, 2, 3, 1)}
         * 最终选取{1, 3}
         */
        System.out.println(robCircle(nums));
    }
}
