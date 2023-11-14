package TwoPointers;

import java.util.Arrays;

public class TwoSum {
    /**
     * 从数组中找出满足相加之和等于目标数 target 的两个数
     * @param numbers 下标从 1 开始的整数数组，该数组已按非递减顺序排列
     * @param target 目标数
     * @return 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标
     */
    public static int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        while(i < j){
            int sum = numbers[i] + numbers[j];
            if(sum < target){
                i++;
            }
            else if(sum > target){
                j--;
            }
            else{
                return new int[] {i + 1, j + 1};
            }
        }
        return new int[] {-1, -1};
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }
}
