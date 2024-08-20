package DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/* Leetcode 3117 */
public class MinimumValueSum {
    /**
     * 将 nums 划分为 m 个不相交的连续子数组，第 i 个子数组元素的按位 AND 运算结果等于 andValues[i]。
     * 数组的值等于该数组的最后一个元素
     * @param nums 原数组
     * @param andValues 目标数组
     * @return 将 nums 划分为 m 个子数组所能得到的可能的 最小 子数组 值 之和
     */
    public static int minimumValueSum(int[] nums, int[] andValues) {
        Map<Long, Integer> map = new HashMap<>();
        int ans = dfs(0, 0, -1, nums, andValues, map);
        return ans < Integer.MAX_VALUE / 2 ? ans : -1;
    }

    /**
     * 返回拆分后数组的最后一个元素的和
     * @param i 原数组遍历到的位置
     * @param j 目标数组遍历到的位置
     * @param and 目前的与运算结果
     * @param nums 原数组
     * @param andValues 目标数组
     * @param map 记忆结果
     * @return 拆分后每个数组最后一个元素之和
     */
    static int dfs(int i, int j, int and, int[] nums, int[] andValues, Map<Long, Integer> map){
        int n = nums.length;
        int m = andValues.length;
        // 剩余元素不足，无法划分
        if(n - i < m - j){
            return Integer.MAX_VALUE / 2;
        }
        // andValues 已划分完
        // 若 nums 还有元素未划分，则代表划分失败
        if(j == m){
            return i == n ? 0 : Integer.MAX_VALUE / 2;
        }
        and &= nums[i];
        long mask = (long) i << 36 | (long) j << 32 | and;
        if(map.containsKey(mask)){
            return map.get(mask);
        }
        // 不划分，i 继续前进但 j 不动
        int res = dfs(i + 1, j, and, nums, andValues, map);
        // 可以划分的状态：不断递归加上后续划分的数组的值
        if(and == andValues[j]){
            res = Math.min(res, dfs(i + 1, j + 1, -1, nums, andValues, map) + nums[i]);
        }
        // 记忆当前状态
        map.put(mask, res);
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 4, 3, 3, 2};
        int[] andValues = {0, 3, 3, 2};
        /*
         * 分析：
         * 划分结果：{1, 4}, {3}, {3}, {2}
         * sum = 4 + 3 + 3 + 2 = 12
         */
        System.out.println(minimumValueSum(nums, andValues));
    }
}
