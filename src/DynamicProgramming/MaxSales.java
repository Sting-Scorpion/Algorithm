package DynamicProgramming;

public class MaxSales {
    /**
     * 求最大的连续销售额
     * @param sales 公司每日销售额
     * @return 连续一或多天销售额总和的最大值
     */
    public static int maxSales(int[] sales) {
        int earn = 0;
        int max = sales[0];
        for (int sale : sales) {
            int t = sale + earn;
            earn = Math.max(t, sale);
            max = Math.max(max, earn);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] prices = {-2,1,-3,4,-1,2,1,-5,4};
        int sales = maxSales(prices);
        System.out.println(sales);
    }
}
