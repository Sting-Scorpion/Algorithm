package DynamicProgramming;

/* Leetcode 494 */
public class FindTargetSumWays {
    /**
     * 给你一个非负整数数组 nums 和一个整数 target 。
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个表达式：
     * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同表达式的数目。
     * @param nums 非负整数数组
     * @param target 运算结果
     * @return 不同表达式的数目
     */
    public static int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for(int num: nums){
            sum += num;
        }
        int dif = sum - target;
        if(dif < 0 || dif % 2 != 0){
            return 0;
        }
        /*
         * 思考：
         * 一部分取正一部分取负，则可以看作为
         * 有两个背包A、B，A 背包存放标记为正的元素，B 背包存放标记为负的元素
         * 则有 sum(A) - sum(B) = target
         * 同时 sum(A) + sum(B) = sum
         * 因此可得 sum(A) = (target + sum) / 2, sum(B) = (target - sum) / 2
         * 则本题可以转化为：
         * 给定一个大小为 sum(A)（或sum(B)） 的背包，求装满背包的方法数
         */
        int n = nums.length;
        // 按照 sum(A) 来求解
        int len = (sum + target) >> 1;
        if(len < 0){
            return 0;
        }
        int[][] dp = new int[n + 1][len + 1]; // dp[i][j] 代表 前 i 个位置的和为 j 的子序列的数量
        dp[0][0] = 1;
        for(int i = 0; i < n; i++){
            dp[i + 1][0] = 1;
            for(int j = 0; j <= len; j++){
                dp[i + 1][j] = dp[i][j];
                if(j >= nums[i]){
                    dp[i + 1][j] += dp[i][j - nums[i]];
                }
            }
        }
        return dp[n][len];
        /*
        // 按照 sum(B) 来求解
        int len = dif >> 1;
        int[][] dp = new int[n + 1][len + 1]; // dp[i][j] 代表 前 i 个位置的和为 j 的子序列的数量
        dp[0][0] = 1;
        for(int i = 0; i < n; i++){
            dp[i + 1][0] = 1;
            for(int j = 0; j <= len; j++){
                dp[i + 1][j] = dp[i][j];
                if(j >= nums[i]){
                    dp[i + 1][j] += dp[i][j - nums[i]];
                }
            }
        }
        return dp[n][len];
        */
    }

    /**
     * 空间优化版本
     * @param nums 非负整数数组
     * @param target 运算结果
     * @return 不同表达式的数目
     */
    public static int findTargetSumWays2(int[] nums, int target){
        int sum = 0;
        for(int num: nums){
            sum += num;
        }
        int dif = sum - target;
        if(dif < 0 || dif % 2 != 0){
            return 0;
        }
        int len = dif / 2;
        // 只依赖上一行，优化为使用一维数组存储
        int[] dp = new int[len + 1];
        dp[0] = 1;
        for(int num: nums){
            // 只依赖上方及前一行左边的某位置，因此从右往左更新
            for(int j = len; j >= num; j--){
                dp[j] += dp[j - num];
            }
        }
        return dp[len];
    }

    public static void main(String[] args) {
        int[] nums = {0,0,0,0,0,0,0,0,1};
        int target = 1;
        System.out.println(findTargetSumWays(nums, target));
    }
}
