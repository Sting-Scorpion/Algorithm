package DynamicProgramming;

public class NumDecodings {
    /* Leetcode 91 */
    public static int numDecodings1(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        int[] dp = new int[n + 1];
        if(cs[0] == '0'){
            return 0;
        }
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 1; i < n; i++){
            if(cs[i] != '0'){
                dp[i + 1] = dp[i];
                if(cs[i - 1] == '1' || (cs[i - 1] == '2' && cs[i] - '0' <= 6)){
                    dp[i + 1] += dp[i - 1];
                }
            }
            else{
                if(cs[i - 1] == '2' || cs[i - 1] == '1'){
                    dp[i + 1] = dp[i - 1];
                }
                else{
                    return 0;
                }
            }
        }

        return dp[n];
    }

    /* Leetcode 639 */
    public static int numDecodings2(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        long[] dp = new long[n + 1];
        dp[0] = 1;
        if(cs[0] == '*'){
            dp[1] = 9;
        }
        else if(cs[0] == '0'){
            return 0;
        }
        else{
            dp[1] = 1;
        }

        final int MOD = (int) 1e9 + 7;
        for(int i = 1; i < n; i++){
            if(cs[i] != '*'){
                // 当前一位
                if(cs[i] != '0'){
                    dp[i + 1] = (dp[i + 1] + dp[i]) % MOD;
                }
                // 和前一位
                if(cs[i - 1] == '*'){
                    if(cs[i] - '0' <= 6){
                        dp[i + 1] = (dp[i + 1] + 2 * dp[i - 1]) % MOD;
                    }
                    else{
                        dp[i + 1] = (dp[i + 1] + dp[i - 1]) % MOD;
                    }
                }
                else{
                    if(cs[i - 1] != '0' && (cs[i - 1] - '0') * 10 + (cs[i] - '0') <= 26){
                        dp[i + 1] = (dp[i + 1] + dp[i - 1]) % MOD;
                    }
                }
            }
            else{
                // 当前一位
                dp[i + 1] = (dp[i + 1] + 9 * dp[i]) % MOD;
                // 和前一位
                if(cs[i - 1] == '*'){
                    dp[i + 1] = (dp[i + 1] + 15 * dp[i - 1]) % MOD;
                }
                else{
                    if(cs[i - 1] == '1'){
                        dp[i + 1] = (dp[i + 1] + 9 * dp[i - 1]) % MOD;
                    }
                    else if(cs[i - 1] == '2'){
                        dp[i + 1] = (dp[i + 1] + 6 * dp[i - 1]) % MOD;
                    }
                }
            }
        }

        return (int) dp[n];
    }

    public static void main(String[] args) {
        String s = "12";
        System.out.println(numDecodings1(s));
        s = "2*";
        System.out.println(numDecodings2(s));
    }
}
