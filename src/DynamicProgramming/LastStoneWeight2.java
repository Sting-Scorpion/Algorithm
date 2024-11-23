package DynamicProgramming;

/* Leetcode 1049 */
public class LastStoneWeight2 {
    /**
     * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y
     * 如果 x == y，那么两块石头都会被完全粉碎；
     * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
     * @param stones 石头的重量
     * @return 返回剩下一块石头最小的可能重量
     */
    public static int lastStoneWeightII(int[] stones){
        int sum = 0;
        for(int stone : stones){
            sum += stone;
        }
        /*
         * 转化为：
         * 把石头分成两堆，让着两堆石头重量总和的差最小
         * 即，其中一堆石头的累加和尽量接近 sum / 2
         */
        int smallSum = near(stones, sum / 2);
        return (sum - smallSum) - smallSum;
    }

    public static void main(String[] args) {
        int[] stones = {2, 7, 4, 1, 8, 1};
        /*
         * 分析：
         * 小：{1, 2, 8}
         * 大：{1, 4, 7}
         * 结果为 1
         */
        System.out.println(lastStoneWeightII(stones));
    }

    /**
     * 子序列累加和不超过 tar 但尽量接近（0-1背包问题）
     * @param nums 目标序列
     * @param tar 目标值
     * @return 不超过 tar 的最大的子序列和
     */
    static int near(int[] nums, int tar){
        int[] dp = new int[tar + 1];
        for(int num : nums){
            for(int j = tar; j >= num; j--){
                // dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - num] + num);
                dp[j] = Math.max(dp[j], dp[j - num] + num);
            }
        }
        return dp[tar];
    }
}
