package leetcode;

import java.util.Arrays;

public class SuccessfulPairs {
    /**
     * 一个咒语和药水的能量强度相乘如果大于等于 success ，那么它们视为一对成功的组合
     * @param spells 表示咒语的能量强度
     * @param potions 表示药水的能量强度
     * @param success 咒语和药水的能量强度相乘的阈值
     * @return 一个数组pairs， 其中 pairs[i] 是能跟第 i 个咒语成功组合的药水数目
     */
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;
        int m = potions.length;
        int[] ans = new int[n];
        // 等价于问数组 potions 中值大于等于 t / spells[i] 的个数
        Arrays.sort(potions); // 对数组potions排序
        // 通过二分查找找到满足条件的最小下标
        for(int i = 0; i < n; i++){
            double div = success * 1.0 / spells[i];
            int l = 0, r = m - 1;
            while(l < r){
                int mid = l + r >> 1;
                if(potions[mid] >= div){
                    r = mid;
                }
                else{
                    l = mid + 1;
                }
            }
            if(potions[r] * 1L * spells[i] >= success){
                ans[i] = m - r;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SuccessfulPairs solution = new SuccessfulPairs();
        int[] spells = {5, 1, 3};
        int[] potions = {1, 2, 3, 4, 5};
        int success = 7;
        int[] re = solution.successfulPairs(spells, potions, success);
        for (int n: re) {
            System.out.print(n + " ");
        }
    }
}
