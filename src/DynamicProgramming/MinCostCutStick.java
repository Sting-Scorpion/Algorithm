package DynamicProgramming;

import java.util.Arrays;

/* Leetcode 1547 */
public class MinCostCutStick {
    /**
     * 每次切割的成本都是当前要切割的棍子的长度，切棍子的总成本是历次切割成本的总和。
     * @param n 棍子的长度
     * @param cuts 需要将棍子切开的位置
     * @return 切棍子的最小总成本
     */
    public static int minCost(int n, int[] cuts){
        Arrays.sort(cuts);
        int m = cuts.length;
        int[] arr = new int[m + 2]; // {0, cuts[0], cuts[1], ... , cuts[m - 1], n}
        for(int i = 1; i <= m; i++){
            arr[i] = cuts[i - 1];
        }
        arr[m + 1] = n;
        int[][] dp = new int[m + 2][m + 2]; // 从第 i 到第 j 个切点都切完的最小代价
        for(int i = 1; i <= m; i++){
            // 只有一个切点时，左右两端点一定分别是前、后一个切点
            dp[i][i] = arr[i + 1] - arr[i - 1];
        }
        // 棍子头尾分别是第 l - 1 和 r + 1 个切点
        for(int l = m - 1; l >= 1; l--){
            for(int r = l + 1; r <= m; r++){
                int next = Integer.MAX_VALUE;
                // 所有分割点的枚举， 得出代价最小的
                for(int k = l; k <= r; k++){
                    next = Math.min(next, dp[l][k - 1] + dp[k + 1][r]);
                }
                dp[l][r] = next + (arr[r + 1] - arr[l - 1]);
            }
        }
        return dp[1][m];
    }

    public static void main(String[] args) {
        int n = 7;
        int[] cuts = {1, 3, 4, 5};
        /*
         * 分析：
         * 切割顺序改为：{3, 5, 1, 4}
         * 代价为：7 + 4 + 3 + 2 = 16
         */
        System.out.println(minCost(n, cuts));
    }
}
