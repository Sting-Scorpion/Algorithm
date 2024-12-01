package DynamicProgramming;

/* Leetcode 664 */
public class StrangePrinter {
    /**
     * 有台奇怪的打印机有以下两个特殊要求：
     * 1. 打印机每次只能打印由同一个字符组成的序列。
     * 2. 每次可以在从起始到结束的任意位置打印新字符，并且会覆盖掉原来已有的字符。
     * @param s 目标字符串
     * @return 打印 s 需要的最少打印次数
     */
    public static int strangePrinter(String s){
        char[] cs = s.toCharArray();
        int n = cs.length;
        int[][] dp = new int[n][n]; // 从 i 到 j 最少的打印次数
        for(int i = 0; i < n; i++){
            // 1 个格子，只用打印一次
            dp[i][i] = 1;
            if(i < n - 1){
                // 两个格子时，颜色相同则只需 1 次，颜色不同则需 2 次
                dp[i][i + 1] = cs[i] == cs[i + 1] ? 1 : 2;
            }
        }
        for(int l = n - 3; l >= 0; l--){
            for(int r = l + 2; r < n; r++){
                if(cs[l] == cs[r]){
                    // 端点颜色相同，可以视为在另一端的时候顺便给这一端上色了
                    dp[l][r] = dp[l][r - 1]; // 也可以是 dp[l + 1][r]
                }
                else{
                    int times = Integer.MAX_VALUE;
                    // 每个划分点的探讨
                    for(int m = l; m < r; m++){
                        times = Math.min(times, dp[l][m] + dp[m + 1][r]);
                    }
                    dp[l][r] = times;
                }
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        String s = "abcba";
        /*
         * 分析
         * 先打印： a a a a a
         * 覆盖：     b b b
         * 覆盖：       c
         * 共需 3 次
         */
        System.out.println(strangePrinter(s));
    }
}
