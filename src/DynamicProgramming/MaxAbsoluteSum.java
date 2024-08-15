package DynamicProgramming;

/* Leetcode 1749 */
public class MaxAbsoluteSum {
    /**
     * 找出 nums 中和的绝对值最大的任意子数组（可能为空）
     * @param nums 整数数组
     * @return 和的绝对值的最大值
     */
    public static int maxAbsoluteSum(int[] nums) {
        int dp1 = 0, dp2 = 0;
        int max1 = 0, max2 = 0;
        for(int num : nums){
            // 记录正的最大
            if(dp1 + num < 0){
                dp1 = 0;
            }
            else{
                dp1 += num;
            }
            max1 = Math.max(max1, dp1);
            // 记录负的“最大”
            if(dp2 + num > 0){
                dp2 = 0;
            }
            else{
                dp2 += num;
            }
            max2 = Math.min(max2, dp2);
        }
        return Math.max(max1, -max2);
    }

    public static void main(String[] args) {
        int[] nums = {2, -5, 1, -4, 3, -2};
        /*
         * 分析：
         * {2, [-5, 1, -4], 3, -2}
         * sum = -8
         */
        System.out.println(maxAbsoluteSum(nums));
    }
}
