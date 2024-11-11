package DynamicProgramming;

/* Leetcode 1143 */
public class LongestCommonSubsequence {
    /**
     * 给定两个字符串，返回这两个字符串的最长公共子序列的长度。如果不存在公共子序列，返回0。
     * @param text1 第一个字符串
     * @param text2 第二个字符串
     * @return 最长公共子序列的长度
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        int n = s1.length;
        int m = s2.length;
        int[][] dp = new int[n + 1][m + 1];
        for(int len1 = 1; len1 <= n; len1++){
            for(int len2 = 1; len2 <= m; len2++){
                if(s1[len1 - 1] == s2[len2 - 1]){
                    dp[len1][len2] = 1 + dp[len1 - 1][len2 - 1];
                }
                else{
                    dp[len1][len2] = Math.max(dp[len1 - 1][len2], dp[len1][len2 - 1]);
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "acef";
        /*
         * 分析：
         * 最长公共子序列为："ace"
         * 长度为 3
         */
        System.out.println(longestCommonSubsequence(text1, text2));
    }
}
