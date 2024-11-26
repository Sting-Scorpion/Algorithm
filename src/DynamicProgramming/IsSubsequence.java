package DynamicProgramming;

/* Leetcode 392 */
public class IsSubsequence {
    /*
     * 【动态规划】
     * 我们注意到，该解法中对 t 的处理与 s 无关，
     * 且预处理完成后，可以利用预处理数组的信息，线性地算出任意一个字符串 s 是否为 t 的子串。
     * 这样我们就可以解决「后续挑战」
     */
    /**
     * 判断 s 是否为 t 的子序列
     * 进阶：
     *   如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
     */
    public static boolean isSubsequence(String s, String t) {
        int n = s.length(), m = t.length();
        //数组f[i][j]记录第i个位置之后第j个字母最早出现的位置
        int[][] location = new int[m + 1][26];
        for (int i = 0; i < 26; i++) {
            location[m][i] = m;
        }//都不出现记最早出现位置为m(超出范围)

        // 从后往前更新位置数组
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                if (t.charAt(i) == j + 'a')
                    location[i][j] = i;
                else
                    location[i][j] = location[i + 1][j];
            }
        }

        int front = 0;
        for (int i = 0; i < n; i++) {
            if (location[front][s.charAt(i) - 'a'] == m) {
                return false;
            }
            //第i个位置出现目标字母，则下一次从i+1的位置继续找
            front = location[front][s.charAt(i) - 'a'] + 1;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "abc";
        String t = "anbgdc";
        System.out.println(isSubsequence(s, t));
    }
}
