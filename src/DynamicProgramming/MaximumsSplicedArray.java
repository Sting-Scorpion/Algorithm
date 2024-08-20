package DynamicProgramming;

/* Leetcode 2321 */
public class MaximumsSplicedArray {
    /**
     * 可以选择两个整数 left 和 right，交换 两个子数组 nums1[left...right] 和 nums2[left...right]。
     * 分数取 sum(nums1) 和 sum(nums2) 中的最大值
     * @param nums1 数组 1
     * @param nums2 数组 2
     * @return 可能的最大分数
     */
    public static int maximumsSplicedArray(int[] nums1, int[] nums2) {
        /*
         * 对于 nums1，交换后的数组和为：
         * sum(nums1) - (nums1[left] + ... + nums1[right]) + (nums2[left] + ... + nums2[right])
         * = sum(nums1) + (nums2[left] - nums1[left]) + ... + (nums2[right] - nums1[right])
         * 令 dif[i] = nums2[i] - nums1[i]
         * 为了最大化该结果，即求 dif 数组的“最大子数组和”
         * 对于 nums2 同理，最后二者中的最大值即为最终答案
         */
        return Math.max(maxNums1(nums1, nums2), maxNums1(nums2, nums1));
    }

    /**
     * 使得交换后的 nums1 的元素和最大化
     * @param nums1 目标数组
     * @param nums2 被交换的数组
     * @return 最大的和
     */
    static int maxNums1(int[] nums1, int[] nums2){
        int dp = 0;
        int max = 0; // 交换的元素的差的最大值
        int sum = 0; // 原本 nums1 的元素和
        for(int i = 0; i < nums1.length; i++){
            sum += nums1[i];
            int dif = nums2[i] - nums1[i]; // dif[i] = nums2[i] - nums1[i]
            dp = Math.max(dp + dif, 0); // 连续的差的最大和
            max = Math.max(dp, max);
        }
        return sum + max; // 原本的和 + 差值的和
    }

    public static void main(String[] args) {
        int[] nums1 = {20, 40, 20, 70, 30};
        int[] nums2 = {50, 20, 50, 40, 20};
        /*
         * 分析：
         * {20, 40, 20, [70, 30]}, {50, 20, 50, [40, 20]}
         * {20, 40, 20, 40, 20}, [{50, 20, 50, 70, 30}]
         * sum = max(140, 220) = 220
         */
        System.out.println(maximumsSplicedArray(nums1, nums2));
    }
}
