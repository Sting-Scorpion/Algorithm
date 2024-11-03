package Graph;

import java.util.Arrays;

/* Leetcode 787 */
public class FindCheapestPrice {
    /**
     * 找到出一条最多经过 k 站中转的路线，使得价格最低
     * @param n 城市的数量
     * @param flights flights[i] = [from, to, price] ，表示该航班都从城市 from 开始，以价格 price 抵达 to。
     * @param src 出发城市
     * @param dst 目的地
     * @param k 最多中转次数
     * @return 最低的价格
     */
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k){
        int[] price = new int[n];
        Arrays.fill(price, Integer.MAX_VALUE);
        price[src] = 0; // 起始点初始化
        for(int i = 0; i <= k; i++){
            int[] cur = Arrays.copyOf(price, n); // 记录更新完后的距离
            for(int[] edge : flights){
                if(price[edge[0]] != Integer.MAX_VALUE){
                    // 松弛操作
                    cur[edge[1]] = Math.min(price[edge[0]] + edge[2], cur[edge[1]]);
                }
            }
            price = cur;
        }
        return price[dst] == Integer.MAX_VALUE ? -1 : price[dst];
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
        int src = 0;
        int dst = 3;
        int k = 1;
        /*
         * 分析：
         * 只能中转一次，最便宜应为 0 -> 1 -> 3 ，价格为 100 + 600 = 700
         */
        System.out.println(findCheapestPrice(n, flights, src, dst, k));
    }
}
