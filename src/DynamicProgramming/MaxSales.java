package DynamicProgramming;

/* Leetcode 53 */
public class MaxSales {
    /**
     * 求最大的连续销售额
     * @param sales 公司每日销售额
     * @return 连续一或多天销售额总和的最大值
     */
    public static int maxSales(int[] sales) {
        /*
         * 就是换皮“最大子数组”
         */
        int dp = 0; // 记录连续天数的最大和
        int max = sales[0]; // 记录目前最大的和
        for (int sale : sales) {
            dp = Math.max(dp, 0) + sale;
            max = Math.max(max, dp);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] prices = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        /*
         * 分析：
         * {-2, 1, -3, [4, -1, 2, 1], -5, 4}
         * max = 6
         */
        System.out.println(maxSales(prices));
    }
}
