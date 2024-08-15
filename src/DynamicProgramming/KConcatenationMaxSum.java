package DynamicProgramming;

/* Leetcode 1191 */
public class KConcatenationMaxSum {
    /**
     * 给定一个整数数组 arr 和一个整数 k ，通过重复 k 次来修改数组。
     * 返回修改后的数组中的最大的子数组之和。注意，子数组长度可以是 0，在这种情况下它的总和也是 0。
     * @param arr 整数数组
     * @param k 重复次数
     * @return 修改后的数组中的最大的子数组之和（模 1e9 + 7 后的结果）
     */
    public static int kConcatenationMaxSum(int[] arr, int k) {
        /*
         * 拆分问题：
         * - 连续两个数组串联的最大子数组之和
         * - 中间的 k - 2 个串联数组是否需要拼接在两个数组之间（数组元素和是否大于零）
         */
        int MOD = (int) 1e9 + 7;
        long dp = Math.max(arr[0], 0L);
        long max = dp;
        long sum = arr[0]; // 记录 arr 所有元素之和
        // 计算 2 个数组串联后的最大子数组之和（对 k = 1 进行特别处理）
        for (int i = 1; i < Math.min(k, 2) * arr.length; i++) {
            dp = Math.max(dp, 0) + arr[i % arr.length];
            max = Math.max(dp, max);
            if (i < arr.length) {
                sum += arr[i];
            }
        }
        // 如果 sum 为正，去掉一头一尾，将中间串联的 k - 2 个数组全部加上
        // 再将一头一尾的“2 个数组串联和的最大子数组和”加上
        if (sum > 0 && k > 2) {
            long r = (sum * (k - 2)) % MOD;
            max += r;
        }
        // sum 不为正，则最后结果就等于“2 个数组串联和的最大子数组和”
        return (int) max % MOD;
    }

    public static void main(String[] args) {
        int[] arr = {-5, -2, 0, 0, 3, 9, -2, -5, 4};
        int k = 5;
        /*
         * 分析：
         * {-5, -2, 0, 0, [3, 9, -2, -5, 4, ..., -5, -2, 0, 0, 3, 9], -2, -5, 4}
         * sum = 2, max = 14
         */
        System.out.println(kConcatenationMaxSum(arr, k));
    }
}
