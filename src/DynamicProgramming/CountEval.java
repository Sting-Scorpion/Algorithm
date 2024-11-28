package DynamicProgramming;

public class CountEval {
    /**
     * 在布尔表达式中添加括号，可以得到不同的布尔结果。
     * @param s 一个布尔表达式
     * @param result 期望的布尔结果
     * @return 可使该表达式得出 result 值的括号方法
     */
    public static int countEval(String s, int result) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        int[][][] dp = new int[n][n][2];
        // 只有一个字符时，唯一可能性
        for(int i = 0; i < n; i += 2){
            dp[i][i][cs[i] - '0'] = 1;
        }
        for(int l = n - 3; l >= 0; l -= 2){
            for(int r = l + 2; r < n; r += 2){
                // 中间的符号
                for(int k = l + 1; k < r; k += 2){
                    if(cs[k] == '&'){
                        dp[l][r][0] += dp[l][k - 1][0] * dp[k + 1][r][0] // 0 & 0
                                + dp[l][k - 1][0] * dp[k + 1][r][1] // 0 & 1
                                + dp[l][k - 1][1] * dp[k + 1][r][0]; // 1 & 0
                        dp[l][r][1] += dp[l][k - 1][1] * dp[k + 1][r][1]; // 1 & 1
                    }
                    else if(cs[k] == '|'){
                        dp[l][r][0] += dp[l][k - 1][0] * dp[k + 1][r][0]; // 0 | 0
                        dp[l][r][1] += dp[l][k - 1][0] * dp[k + 1][r][1] // 0 | 1
                                + dp[l][k - 1][1] * dp[k + 1][r][0] // 1 | 0
                                + dp[l][k - 1][1] * dp[k + 1][r][1]; // 1 | 1
                    }
                    else{
                        dp[l][r][0] += dp[l][k - 1][0] * dp[k + 1][r][0] // 0 ^ 0
                                + dp[l][k - 1][1] * dp[k + 1][r][1]; // 1 ^ 1
                        dp[l][r][1] += dp[l][k - 1][0] * dp[k + 1][r][1] // 0 ^ 1
                                + dp[l][k - 1][1] * dp[k + 1][r][0]; // 1 ^ 0
                    }
                }
            }
        }
        return dp[0][n - 1][result];
    }

    public static void main(String[] args) {
        String s = "1^0|0|1";
        int result = 0;
        /*
         * 分析：
         * 可能的方式：
         * 1 ^ (0 | (0 | 1))
         * 1 ^ ((0 | 0) | 1)
         * 2 种方式
         */
        System.out.println(countEval(s, result));
    }
}
