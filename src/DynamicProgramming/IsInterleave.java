package DynamicProgramming;

/* Leetcode 97 */
public class IsInterleave {
    /**
     * 验证 s3 是否是由 s1 和 s2 交错 组成的
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        char[] c3 = s3.toCharArray();
        int m = c1.length;
        int n = c2.length;
        if(m + n != c3.length){
            return false;
        }
        /*
         * dp[i][j] 代表：
         * s1[前缀长度为i] 和 s2[前缀长度为j] 能否交错组成 s3[前缀长度为i + j]
         */
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        // 只有 s1 参与组成 s3
        for(int i = 1; i <= m; i++){
            if(c1[i - 1] != c3[i - 1]){
                break;
            }
            dp[i][0] = true;
        }
        // 只有 s2 参与组成 s3
        for(int j = 1; j <= m; j++){
            if(c2[j - 1] != c3[j - 1]){
                break;
            }
            dp[0][j] = true;
        }

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                // c3[i + j - 1] 要么等于 c1[i - 1]，要么等于 c2[j - 1]，且上一个状态也能组成。否则不能组成
                dp[i][j] = (c1[i - 1] == c3[i + j - 1] && dp[i - 1][j]) || (c2[j - 1] == c3[i + j - 1] && dp[i][j - 1]);
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        /*
         * 分析：
         * s3 可以是
         * a a         b c   c
         *     d b b c     a
         */
        System.out.println(isInterleave(s1, s2, s3));
    }
}
