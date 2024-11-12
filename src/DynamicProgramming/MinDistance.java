package DynamicProgramming;

/* Leetcode 72 */
public class MinDistance {
    /**
     * 可以对一个单词进行如下三种操作：
     * 1. 插入一个字符
     * 2. 删除一个字符
     * 3. 替换一个字符
     * @param word1 原字符串
     * @param word2 目标字符串
     * @return 将 word1 转换成 word2 所使用的最少操作数
     */
    public static int minDistance(String word1, String word2) {
        return editDistance(word1, word2, 1, 1, 1);
    }

    /**
     * 通用版编辑距离
     * @param str1 原字符串
     * @param str2 目标字符串
     * @param insert 插入字符的代价
     * @param delete 删除字符的代价
     * @param replace 替换字符的代价
     * @return 改变字符串的最小代价
     */
    static int editDistance(String str1, String str2, int insert, int delete, int replace){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int m = s1.length;
        int n = s2.length;
        /*
         * dp[i][j] 表示：
         * s1 从 0 到下标为 i 位置的子串
         * 想要变成
         * s2 从 0 到下标为 j 位置的子串
         * 最少的代价
         */
        int[][] dp = new int[m + 1][n + 1];
        // dp[i][0] 代表 长度为 i 的字符串变成长度为 0 的字符串的代价（即全部删除）
        for(int i = 1; i <= m; i++){
            dp[i][0] = i * delete;
        }
        // dp[0][j] 代表 长度为 0 的字符串变成长度为 j 的字符串的代价（即全部添加）
        for(int j = 1; j <= n; j++){
            dp[0][j] = j * insert;
        }
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                // 相等，不用变，代价继承上一个
                if(s1[i - 1] == s2[j - 1]){
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else{
                    dp[i][j] = Math.min(
                            Math.min(dp[i - 1][j] + delete, dp[i][j - 1] + insert),
                            dp[i - 1][j - 1] + replace
                    );
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String word1 = "intention";
        String word2 = "execution";
        /*
         * 分析：
         * 1. intention -> inention （删除 't'）
         * 2. inention -> enention （'i' 变成 'e'）
         * 3. enention -> exention （'n' 变成 'x'）
         * 4. exention -> exection （'n' 变成 'c'）
         * 5. exection -> execution （添加 'u'）
         * 最小步骤为 5
         */
        System.out.println(minDistance(word1, word2));
    }
}
