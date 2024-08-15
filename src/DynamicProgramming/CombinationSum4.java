package DynamicProgramming;

import java.util.Arrays;

/* Leetcode 377 */
public class CombinationSum4 {
    /**
     * 从 nums 中找出并返回总和为 target 的元素组合的个数
     * @param nums 不同正整数组成的数组
     * @param target 目标整数
     * @return 组合的个数
     */
    public static int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums); // 排序，方便遍历
        if(target < nums[0]) return 0;
        int[] dp = new int[target + 1]; // 记录总和的数组
        dp[0] = 1; // 默认在第0位
        for(int i = 1; i <= target; i++){
            for(int num : nums){
                // 可以得到的和
                if(i - num >= 0){
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int target = 4;
        /*
         * 分析：
         * - 从 3 到 4
         * {1, 1, 1, 1}, {1, 2, 1}, {2, 1, 1}, {3, 1}
         * - 从 2 到 4
         * {1, 1, 2}, {2, 2}
         * - 从 1 到 4
         * {1, 3}
         * sum = 7
         */
        System.out.println(combinationSum4(nums, target));
    }
}
