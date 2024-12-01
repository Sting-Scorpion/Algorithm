package DynamicProgramming;

/* Leetcode 1000 */
public class MergeStones {
    /**
     * 有 n 堆石头排成一排，每次移动需要将连续的 k 堆石头合并为一堆，而这次移动的成本为这 k 堆中石头的总数。
     * @param stones 每堆石头中的石头总数
     * @param k 连续合并的石头堆数
     * @return 把所有石头合并成一堆的最低成本，若无法合并成一堆返回 -1
     */
    public static int mergeStones(int[] stones, int k){
        int n = stones.length;
        if((n - 1) % (k - 1) != 0){
            return -1;
        }
        int[] presum = new int[n + 1];
        for(int i = 0; i < n; i++){
            presum[i + 1] = stones[i] + presum[i];
        }
        int[][] dp = new int[n][n]; // dp[l][r] 代表 l 到 r 范围上的石头合并到不能再合并的最小代价
        // 特例：从 i 到 i 合并成一堆的代价：0（已默认初始化为0）
        for(int l = n - 2; l >= 0; l--){
            for(int r = l + 1; r < n; r++){
                int cost = Integer.MAX_VALUE;
                /*
                 * l 到 r 范围上最终合并成 p 堆（p 由范围大小和 k 的大小决定）
                 * 要保证从 l 到 m 范围上能合并成一堆，且 m + 1 到 r 范围上合并成 p - 1 堆
                 * 则必须让 m - l 能被 k - 1 整除（每次右移 k - 1 个单位）
                 */
                for(int m = l; m < r; m += k - 1){
                    cost = Math.min(cost, dp[l][m] + dp[m + 1][r]);
                }
                // 这个范围上可以合并成一份，则再加上最终合并代价
                if((r - l) % (k - 1) == 0){
                    cost += presum[r + 1] - presum[l];
                }
                dp[l][r] = cost;
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        int[] stones = {3, 2, 4, 1};
        int k = 2;
        /*
         * 分析：
         * 先合并 (3, 2)，剩下 {5, 4, 1}，代价是 2 + 3 = 5
         * 再合并 (4, 1)，剩下 {5, 5}，代价是 5 + (4 + 1) = 10
         * 最后合并 (5, 5)，剩下 {10}，代价是 10 + (5 + 5) = 20
         * 最小代价 20
         */
        System.out.println(mergeStones(stones, k));
    }
}
