package DynamicProgramming;

/* Leetcode 2787 */
public class NumberOfWays {
    /**
     * 返回互不相同整数 [n1, n2, ..., nk] 的集合数目，满足 n = n1^x + n2^x + ... + nk^x
     * @param n 目标正整数
     * @param x 目标幂
     * @return 将 n 表示成一些互不相同正整数的 x 次幂之和的方案数
     */
    public static int numberOfWays(int n, int x) {
        int MOD = (int) 1e9 + 7;
        int maxNum = (int) Math.pow(n + 0.5, 1.0 / x); // 最大的底数
        int[][] dp = new int[maxNum + 1][n + 1];
        dp[0][0] = 1; // 初始状态，总和为 0 且 可选的数为 0 个，有一种方案
        for (int i = 1; i <= maxNum; i++) {
            int power = (int) Math.pow(i, x); // i^x 的值
            for (int j = 0; j <= n; j++) {
                // 超出容量不能选
                if (j < power) {
                    dp[i][j] = dp[i - 1][j];
                }
                // 在容量内，可选可不选
                else {
                    /*
                     * 最终结果为：选 + 不选 的总方案数
                     * 选取 i^x : 有 dp[i - 1][j - power] 种方案（前 i - 1 个数选出和为 j - power 的方案数）
                     * 不选 i^x : 有 dp[i - 1][j] 种方案（前 i - 1 个数选出和为 j 的方案数）
                     */
                    dp[i][j] = (dp[i - 1][j] + dp[i - 1][j - power]) % MOD;
                }
            }
        }
        return dp[maxNum][n];
    }

    public static void main(String[] args) {
        int n = 4;
        int x = 1;
        /*
         * 分析：
         * n = 4^1 = 3^1 + 1^1 = 4
         * sum = 2
         */
        System.out.println(numberOfWays(n, x));
    }
}
