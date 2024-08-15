package DynamicProgramming;

/* Leetcode 152 */
public class MaxProduct {
    /**
     * 找出数组中乘积最大的非空连续子数组
     * （该子数组中至少包含一个数字）
     * @param nums 整数数组
     * @return 子数组对应的乘积
     */
    public static int maxProduct(int[] nums) {
        //担心正负交替，因此建立两个值来保存最大（正）与最小（负）
        int maxF = nums[0], minF = nums[0], ans = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; ++i) {
            int mx = maxF, mn = minF;
            maxF = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            minF = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
            ans = Math.max(maxF, ans);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, -2, 4};
        int re = maxProduct(nums);
        System.out.println(re);
    }
}
