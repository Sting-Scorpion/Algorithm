package DynamicProgramming;

/* Leetcode 115 */
public class NumDistinct {
    /**
     * 计算在 s 的子序列中 t 出现的个数。
     */
    public static int numDistinct(String s, String t) {
        char[] ss = s.toCharArray();
        char[] ts = t.toCharArray();
        int n = ss.length;
        int m = ts.length;
        int[][] dp = new int[n + 1][m + 1];
        for(int i = 0; i <= n; i++){
            dp[i][0] = 1;
        }
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                dp[i][j] = dp[i - 1][j];
                if(ss[i - 1] == ts[j - 1]){
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        String s = "rabbbit";
        String t = "rabbit";
        /*
         * 分析：
         * 分别选择下标为 {2, 3}, {2, 4}, {3, 4} 的字母 'b' 组成
         * 共有 3 种
         */
        System.out.println(numDistinct(s, t));
    }
}
