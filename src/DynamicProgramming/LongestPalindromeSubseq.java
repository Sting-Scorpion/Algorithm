package DynamicProgramming;

/* Leetcode 516 */
public class LongestPalindromeSubseq {
    /**
     * 给你一个字符串，找出其中最长的回文子序列，并返回该序列的长度。
     * @param s 字符串
     * @return 最长的回文子序列长度
     */
    public static int longestPalindromeSubseq1(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        int[][] dp = new int[n][n];
        for(int l = n - 1; l >= 0; l--){
            dp[l][l] = 1;
            if(l + 1 < n){
                dp[l][l + 1] = cs[l] == cs[l + 1] ? 2 : 1;
            }
            for(int r = l + 2; r < n; r++){
                if(cs[l] == cs[r]){
                    dp[l][r] = 2 + dp[l + 1][r - 1];
                }
                else{
                    dp[l][r] = Math.max(dp[l + 1][r], dp[l][r - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    /**
     * 空间压缩版本
     * @param s 字符串
     * @return 最长的回文子序列长度
     */
    public static int longestPalindromeSubseq2(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        int[] dp = new int[n];
        for(int l = n - 1, leftdown = 0, backup = 0; l >= 0; l--){
            // dp[l][l]
            dp[l] = 1;
            if(l + 1 < n){
                leftdown = dp[l + 1];
                // dp[l][l + 1]
                dp[l + 1] = cs[l] == cs[l + 1] ? 2 : 1;
            }
            for(int r = l + 2; r < n; r++){
                backup = dp[r];
                if(cs[l] == cs[r]){
                    dp[r] = 2 + leftdown;
                }
                else{
                    dp[r] = Math.max(dp[r], dp[r - 1]);
                }
                leftdown = backup;
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        String s = "ab12cd34ef3g2h1i";
        /*
         * 分析：
         * 最长回文子序列：1234321
         * 长度为 7
         */
        System.out.println(longestPalindromeSubseq1(s));
    }
}
