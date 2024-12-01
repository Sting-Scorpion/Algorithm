package DynamicProgramming;

import java.util.Arrays;

/* Leetcode 730 */
public class CountPalindromicSubsequences {
    /**
     * @param s 给定字符串
     * @return s 中不同的非空回文子序列个数
     */
    public static int countPalindromicSubsequences(String s){
        int MOD = (int) 1e9 + 7;
        char[] cs = s.toCharArray();
        int n = cs.length;
        int[] last = new int[256];
        int[] left = new int[n]; // left[i] 代表 i 位置的左边和 cs[i] 字符相等的最近的字符的位置，不存在则为 -1
        Arrays.fill(last, -1);
        for(int i = 0; i < n; i++){
            left[i] = last[cs[i]];
            last[cs[i]] = i;
        }
        int[] right = new int[n];
        Arrays.fill(last, n);
        for(int i = n - 1; i >= 0; i--){
            right[i] = last[cs[i]];
            last[cs[i]] = i;
        }
        long[][] dp = new long[n][n]; // dp[i][j] 代表 i 到 j 范围上有多少不同的回文子序列（无效范围为 0）
        // 一个字符时，回文子序列只有一个
        for(int i = 0; i < n; i++){
            dp[i][i] = 1;
        }
        for(int i = n - 1; i >= 0; i--){
            for(int j = i + 1; j < n; j++){
                // 两头位置字符不同
                if(cs[i] != cs[j]){
                    dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1] + MOD;
                }
                // 两头位置字符相同
                else{
                    int iNearest = right[i]; // i 右边与 i 位置字符相同且最近的字符位置
                    int jNearest = left[j]; // j 左边与 j 位置字符相同且最近的字符位置
                    // 大于的情况：中间没有该字符，找到的最近的相同字符是对方
                    if(iNearest > jNearest){
                        // 中间的回文序列 + 两端加上新字符的新回文序列（数量与前者相同） + 单独一个的新字符 + 一对的新字符（中间的空串两端加上新字符）
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 2;
                    }
                    // 等于的情况：中间只有一个该字符，找到的是同一个位置
                    else if(iNearest == jNearest){
                        // 中间的回文序列 + 两端加上新字符的新回文序列（数量与前者相同） + 一对的新字符（中间的空串两端加上新字符）
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 1;
                    }
                    // 其余（小于）的情况：中间不止一个该字符
                    else{
                        // 中间的回文序列 + 两端加上新字符的新回文序列（数量与前者相同） - iNearest 到 jNearest 之间已经在两端加过该字符的回文序列（与 i j 重复）
                        dp[i][j] = dp[i + 1][j - 1] * 2 - dp[iNearest + 1][jNearest - 1] + MOD;
                    }
                }
                dp[i][j] %= MOD;
            }
        }
        return (int) dp[0][n - 1];
    }

    public static void main(String[] args) {
        String s = "bccb";
        /*
         * 分析：
         * 分别为：{"b", "c", "bb", "cc", "bcb", "bccb"}
         * 共 6 个
         */
        System.out.println(countPalindromicSubsequences(s));
    }
}
