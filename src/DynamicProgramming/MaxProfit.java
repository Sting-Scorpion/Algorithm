package DynamicProgramming;

import java.util.Arrays;

public class MaxProfit {
    /**
     * Leetcode 121
     * 你只能选择某一天买入这只股票，并选择在未来的某一个不同的日子卖出该股票。
     * @param prices prices[i] 表示一支给定股票第 i 天的价格
     * @return 返回你可以从这笔交易中获取的最大利润
     */
    public static int maxProfit1(int[] prices) {
        int n = prices.length;
        int earn = 0;
        int low = 0;
        for(int i = 0; i < n; i++){
            if(prices[low] < prices[i]){
                int tmpearn = prices[i] - prices[low];
                if(tmpearn > earn){
                    earn = tmpearn;
                }
            }
            else{
                low = i;
            }
        }
        return earn;
    }

    /**
     * Leetcode 122
     * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候最多只能持有一股股票。你也可以先购买，然后在同一天出售。
     * @param prices prices[i] 表示一支给定股票第 i 天的价格
     * @return 返回你可以从这笔交易中获取的最大利润
     */
    public static int maxProfit2(int[] prices) {
        int n = prices.length;
        int earnyes = 0;
        int low = 0;
        for(int i = 1; i < n; i++){
            if(prices[i] > prices[low]){
                earnyes += prices[i] - prices[low];
            }
            low = i;
        }
        return earnyes;
    }

    /**
     * Leetcode 123
     * 你最多可以完成两笔交易。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * @param prices prices[i] 表示一支给定股票第 i 天的价格
     * @return 返回你可以从这笔交易中获取的最大利润
     */
    public static int maxProfit3(int[] prices) {
        int n = prices.length;
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for (int i = 1; i < n; ++i) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }

    public static void main(String[] args) {
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        int[] prices2 = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println("Prices: " + Arrays.toString(prices1));
        System.out.println("  Solution1 : " + maxProfit1(prices1));
        System.out.println("  Solution2 : " + maxProfit2(prices1));
        System.out.println("Prices: " + Arrays.toString(prices2));
        System.out.println("  Solution3 : " + maxProfit3(prices2));
    }
}
