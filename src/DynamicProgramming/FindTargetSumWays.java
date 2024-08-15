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
        int neg = dif / 2;
        int[] dp = new int[neg + 1];
        dp[0] = 1;
        for(int num: nums){
            for(int j = neg; j >= num; j--){
                dp[j] += dp[j - num];
            }
        }
        return dp[neg];
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1};
        int target = 3;
        System.out.println(findTargetSumWays(nums, target));
    }
}
