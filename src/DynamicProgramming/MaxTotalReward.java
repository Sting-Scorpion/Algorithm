package DynamicProgramming;

import java.util.Arrays;

/* Leetcode 3180 */
public class MaxTotalReward {
    /**
     * 最初，你的总奖励 x 为 0，所有下标都是未标记的。你可以执行以下操作任意次：
     * 1. 从区间 [0, n - 1] 中选择一个未标记的下标 i。
     * 2. 如果 rewardValues[i] 大于你当前的总奖励 x，则将 rewardValues[i] 加到 x 上，并标记下标 i。
     * @param rewardValues 整数数组，长度为 n，代表奖励的值
     * @return 执行最优操作能够获得的 最大 总奖励
     */
    public static int maxTotalReward(int[] rewardValues) {
        Arrays.sort(rewardValues);
        int n = rewardValues.length;
        int maxValue = rewardValues[n - 1]; // 最大的奖励一定选取
        int[] dp = new int[maxValue]; // 背包最大容量为 maxValue - 1
        for (int rewardValue : rewardValues) {
            for (int j = maxValue - 1; j >= rewardValue; j--) {
                int x = rewardValue > j - rewardValue ? j - rewardValue : rewardValue - 1;
                dp[j] = Math.max(dp[j], rewardValue + dp[x]);
            }
        }
        return dp[maxValue - 1] + maxValue;
    }

    public static void main(String[] args) {
        int[] rewardValues = {1, 6, 4, 3, 2};
        /*
         * 分析：
         * 6 必选，在 {1, 4, 3, 2} 中选最大和为 5 的
         * 最终：{1, 4, 6} re = 11
         */
        System.out.println(maxTotalReward(rewardValues));
    }
}
