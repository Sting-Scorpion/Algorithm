package DynamicProgramming;

import java.util.Arrays;

/* Leetcode 300 */
public class LengthOfLIS {
    /**
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * @param nums 整数数组
     * @return 最长递增子序列的长度
     */
    public static int lengthOfLIS1(int[] nums){
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int re = 1;
        for(int i = 1; i < n; i++){
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    re = Math.max(re, dp[i]);
                }
            }
        }
        return re;
    }

    /**
     * 优化后的最长递增子序列，
     * 时间复杂度 O(nlogn)
     * @param nums 整数数组
     * @return 最长递增子序列的长度
     */
    public static int lengthOfLIS2(int[] nums){
        int n = nums.length;
        int[] ends = new int[n];
        int re = 0;
        for (int num : nums) {
//            int find = -1;
//            for (int j = 0; j < re; j++) {
//                if (ends[j] >= num) {
//                    find = j;
//                    break;
//                }
//            }
            int find = bs(ends, re, num);
            if (find == -1) {
                ends[re++] = num;
            } else {
                ends[find] = num;
            }
        }
        return re;
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS1(nums));
    }

    /**
     * 二分查找，找到在 ends 中满足 >= tar 的最小值
     * @param ends 目标数组
     * @param len 最大下标位置
     * @param tar 目标值
     * @return 大于等于 tar 的最小值
     */
    static int bs(int[] ends, int len, int tar){
        int l = 0, r = len - 1;
        int re = -1;
        while(l <= r){
            int m = (l + r) >> 1;
            if(ends[m] >= tar){
                re = m;
                r = m - 1;
            }
            else{
                l = m + 1;
            }
        }
        return re;
    }
}
