package DynamicProgramming;

/* Leetcode 1312 */
public class MinInsertions {
    /**
     * 给你一个字符串，每一次操作你都可以在字符串的任意位置插入任意字符。
     * @param s 字符串
     * @return 让 s 成为回文串的 最少操作次数 。
     */
    public static int minInsertions(String s){
        char[] cs = s.toCharArray();
        int n = cs.length;
        int[][] dp = new int[n][n];
        // dp[i][i] = 0 因为单个字符天然是回文串（数组初始化默认值为0，可省略赋值步骤）
        for(int i = 0; i < n - 1; i++){
            // 长度为 2 的字符串的情况
            dp[i][i + 1] = cs[i] == cs[i + 1] ? 0 : 1;
        }
        // 最底下两行已经填完了，直接跳过
        for(int l = n - 3; l >= 0; l--){
            for(int r = l + 2; r < n; r++){
                if(cs[l] == cs[r]){
                    dp[l][r] = dp[l + 1][r - 1];
                }
                else{
                    dp[l][r] = Math.min(dp[l + 1][r], dp[l][r - 1]) + 1;
                }
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        String s = "mbadm";
        /*
         * 分析：
         * 可变为 "mbadabm"（不止一种）
         * 最小插入 2
         */
        System.out.println(minInsertions(s));
    }
}
