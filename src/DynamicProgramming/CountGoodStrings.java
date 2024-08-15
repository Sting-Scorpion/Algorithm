package DynamicProgramming;

/* Leetcode 2466 */
public class CountGoodStrings {
    /**
     * 从空字符串开始构造一个字符串，每一步执行下面操作中的一种：
     * - 将 '0' 在字符串末尾添加 zero 次；
     * - 将 '1' 在字符串末尾添加 one 次；
     * 如果通过以上过程得到一个长度在 low 和 high 之间（包含上下边界）的字符串，那么这个字符串我们称为“好字符串”。
     * @param low 下界
     * @param high 上界
     * @param zero 每次操作添加 0 的次数
     * @param one 每次操作添加 1 的次数
     * @return 不同“好字符串”数目，结果对 1e9 + 7 取余
     */
    public static int countGoodStrings(int low, int high, int zero, int one) {
        /*
         * 等于爬楼梯的变种
         * 理解为：爬到 low 和 high 级楼梯的区间，
         * 每次可以以方式 a 爬 zero 级，
         * 或者以方式 b 爬 one 级；
         * 求总共的方式数量
         */
        final int MOD = 1000000007;
        int[] dp = new int[high + 1];
        dp[0] = 1;
        for(int i = 1; i <= high; i++){
            // 方式 a，要爬 zero 级，因此方法数量等于在 i - zero 位置的方法数
            if(i - zero >= 0){
                dp[i] += dp[i - zero];
                dp[i] %= MOD;
            }
            // 方式 b，要爬 one 级，因此方法数量等于在 i - one 位置的方法数
            if(i - one >= 0){
                dp[i] += dp[i - one];
                dp[i] %= MOD;
            }
        }
        // 求在区间内的总方法数
        int re = 0;
        for(int i = low; i <= high; i++){
            re += dp[i];
            re %= MOD;
        }
        return re;
    }

    public static void main(String[] args) {
        /*
         * 分析：
         * {000, 001, 010, 011, 100, 101, 110, 111}
         * sum = 8
         */
        System.out.println(countGoodStrings(3, 3, 1, 1));
    }
}
