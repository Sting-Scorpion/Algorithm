package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Leetcode 120 */
public class MinimumTotal {
    /**
     * 每一步只能移动到下一行中相邻的结点上。
     * 相邻的结点在这里指的是下标与上一层结点下标相同，或者等于上一层结点下标 + 1 的两个结点。
     * @param triangle 给定一个三角形
     * @return 自顶向下的最小路径和
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] lens = new int[2][n];
        lens[0][0] = triangle.get(0).get(0); // 头部
        for(int i = 1; i < n; i++){
            int curr = i % 2; // 存放在lens数组的哪一行
            int prev = 1 - curr; // 前一层的结果在哪一行
            lens[curr][0] = lens[prev][0] + triangle.get(i).get(0); // 最左只有一条路
            for(int j = 1; j < i; j++){
                lens[curr][j] = Math.min(lens[prev][j - 1], lens[prev][j]) + triangle.get(i).get(j); // 两条路选短的
            }
            lens[curr][i] = lens[prev][i - 1] + triangle.get(i).get(i); //最右也只有一条路
        }
        int re = lens[(n - 1) % 2][0];
        for(int num : lens[(n - 1) % 2]){
            re = Math.min(num, re);
        }
        return re;
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        Integer[] l = {2};
        List<Integer> layer1 = Arrays.asList(l);
        l = new Integer[]{3, 4};
        List<Integer> layer2 = Arrays.asList(l);
        l = new Integer[]{6, 5, 7};
        List<Integer> layer3 = Arrays.asList(l);
        l = new Integer[]{4, 1, 8, 3};
        List<Integer> layer4 = Arrays.asList(l);
        triangle.add(layer1);
        triangle.add(layer2);
        triangle.add(layer3);
        triangle.add(layer4);
        /*
         * 分析：
         *      2
         *     3 4
         *    6 5 7
         *   4 1 8 3
         * 选择：2 -> 3 -> 5 -> 1
         * sum = 11
         */
        System.out.println("自顶向下最小路径和：" + minimumTotal(triangle));
    }
}
