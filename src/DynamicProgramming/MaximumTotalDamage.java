package DynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* Leetcode 3186 */
public class MaximumTotalDamage {
    /**
     * 已知使用伤害值为 power[i] 的咒语时，他们就 不能 使用伤害为 power[i] - 2 ，power[i] - 1 ，power[i] + 1 或者 power[i] + 2 的咒语。
     * 每个咒语最多只能被使用 一次 。
     * @param power 表示每个咒语的伤害值的数组
     * @return 可以达到的伤害值之和的最大值
     */
    public static long maximumTotalDamage(int[] power) {
        /*
         * 值域上的打家劫舍
         */
        Map<Integer, Integer> map = new HashMap<>(); // 记录相同伤害的咒语出现次数
        for(int p : power){
            map.put(p, map.getOrDefault(p, 0) + 1);
        }
        int n = map.size();
        int[] pow = new int[n]; // 记录不同的伤害
        int k = 0;
        for(int p : map.keySet()){
            pow[k++] = p;
        }
        Arrays.sort(pow);

        long[] dp = new long[n + 1];
        int j = 0;
        for(int i = 0; i < n; i++){
            int x = pow[i];
            // 找到伤害大于 x - 2 的最小伤害（位置 j）
            while(pow[j] < x - 2){
                j++;
            }
            dp[i + 1] = Math.max(dp[i], dp[j] + (long) x * map.get(x));
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int[] power = {1, 1, 3, 4};
        /*
         * 分析：
         * {1, 1, 4}
         * sum = 6
         */
        System.out.println(maximumTotalDamage(power));
    }
}
