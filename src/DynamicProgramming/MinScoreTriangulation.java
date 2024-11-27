package DynamicProgramming;

/* Leetcode 1039 */
public class MinScoreTriangulation {
    /**
     * 将 n 边形剖分为 n - 2 个三角形，边不相交。
     * 对于每个三角形，该三角形的值是顶点值的乘积，三角剖分的分数是进行三角剖分后所有 n - 2 个三角形的值之和。
     * @param values 每个顶点的值
     * @return 多边形进行三角剖分后可以得到的最低分
     */
    public static int minScoreTriangulation(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n];
        // 小于三个顶点不构成三角形，分数为 0（默认值，跳过）
        for(int l = n - 3; l >= 0; l--){
            for(int r = l + 2; r < n; r++){
                dp[l][r] = Integer.MAX_VALUE;
                for(int i = l + 1; i < r; i++){
                    dp[l][r] = Math.min(dp[l][r], dp[l][i] + dp[i][r] + values[i] * values[l] * values[r]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        int[] values = {3, 7, 4, 5};
        /*
         * 分析：
         * 有两种分法，分别为：
         * 3 * 7 * 5 + 4 * 7 * 5 = 245；
         * 5 * 3 * 4 + 7 * 3 * 4 = 144。
         * 最小分数为 144
         */
        System.out.println(minScoreTriangulation(values));
    }
}
