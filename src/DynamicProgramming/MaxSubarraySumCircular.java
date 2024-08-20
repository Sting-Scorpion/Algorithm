package DynamicProgramming;

/* Leetcode 918 */
public class MaxSubarraySumCircular {
    /**
     * 给定一个长度为 n 的环形整数数组 nums，数组的末端将会与开头相连呈环状。返回 nums 的非空子数组的最大可能和。
     * @param nums 环形整数数组
     * @return 非空子数组的最大可能和
     */
    public static int maxSubarraySumCircular(int[] nums) {
        int dpMax = 0;
        int max = Integer.MIN_VALUE; // 最大子数组和（不可为空）
        int dpMin = 0;
        int min = 0; // 最小子数组和（可以为空）
        int sum = 0;
        for(int num : nums){
            // 以 num 结尾的子数组最大的和
            dpMax = Math.max(dpMax, 0) + num;
            max = Math.max(max, dpMax);
            // 以 num 结尾的子数组最小的和
            dpMin = Math.min(dpMin, 0) + num;
            min = Math.min(min, dpMin);
            // 整个数组的和
            sum += num;
        }
        /*
         * 最小子数组和是整个数组
         * 即：空数组和最大 => 不被允许
         * 因此已有的 max 即为最大子数组和（负的中间可以取到的最大的和）
         */
        if(sum == min){
            return max;
        }
        /*
         * 两种情况：
         * - 最大子数组和的区间不存在“越界” => 此时得到的最大子数组和就是答案
         * - 最大子数组和的区间存在“越界” => 数组总和减去最小子数组和即为最大子数组和
         */
        return Math.max(max, sum - min);
    }

    public static void main(String[] args) {
        int[] nums = {5, -3, 5};
        /*
         * {5, -3, [5, (5], -3, 5)}
         * 可以理解为：反向选择，去掉 -3
         * 即：{5, [-3], 5}
         * sum = 10
         */
        System.out.println(maxSubarraySumCircular(nums));
    }
}
