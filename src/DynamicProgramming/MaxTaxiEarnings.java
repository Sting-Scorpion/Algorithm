package DynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Leetcode 2008 */
public class MaxTaxiEarnings {
    /**
     * 你行驶在一条有 n 个地点的路上，你想要从 1 开到 n ，通过接乘客订单盈利。你只能沿着编号递增的方向前进，不能改变方向。
     * 同时最多只能接一个订单。
     * @param n 地点的数量
     * @param rides rides[i] = [starti, endi, tipi] 表示第 i 位乘客需要从地点 starti 前往 endi ，愿意支付 tipi 元的小费
     * @return 返回在最优接单方案下，你能盈利最多多少元
     */
    public static long maxTaxiEarnings(int n, int[][] rides) {
        // 记录在地点 i 能获得的最大利润
        long[] dp = new long[n + 1];
        // 记录终点为 i 的乘客的信息
        Map<Integer, List<int[]>> rideMap = new HashMap<>();
        for (int[] ride : rides) {
            rideMap.putIfAbsent(ride[1], new ArrayList<>());
            rideMap.get(ride[1]).add(ride);
        }
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1]; // 以防地点 i 没人下车
            // 找到在地点 i 下车的乘客
            for (int[] ride : rideMap.getOrDefault(i, new ArrayList<>())) {
                dp[i] = Math.max(dp[i], dp[ride[0]] + ride[1] - ride[0] + ride[2]);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 20;
        int[][] rides = {{1, 6, 1}, {3, 10, 2}, {10, 12, 3}, {11, 12, 2}, {12, 15, 2}, {13, 18, 1}};
        /*
         * 分析：
         * {3, 10} -> {10, 12} -> {13, 18}
         * sum = (7 + 2) + (2 + 3) + (5 + 1) = 20
         */
        System.out.println(maxTaxiEarnings(n, rides));
    }
}
