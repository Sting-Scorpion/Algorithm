package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ThreeSum {

    /**
     * 判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0
     * 注意：答案中不可以包含重复的三元组。
     * @param nums 包含 n个整数的数组
     * @return 所有和为 0 且不重复的三元组
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        if(nums.length < 3) return result;
        Arrays.sort(nums);
        int head = 0, tail = nums.length - 1;
        int sum;
        while(tail - head >= 2){
            while(tail - head >= 2){
                sum = nums[head] + nums[tail];
                int i;
                for(i = 1; i < tail - head; i++){
                    if(sum + nums[tail - i] < 0) {
                        //i++;
                        break;
                    }
                    else if(sum + nums[tail - i] == 0){
                        List<Integer> tmp = new LinkedList<>();
                        tmp.add(nums[head]);
                        tmp.add(nums[tail - i]);
                        tmp.add(nums[tail]);
                        if(!result.contains(tmp))
                            result.add(tmp);
                    }
                }
                //if(sum + nums[tail - i + 1] < 0) break;
                tail--;
            }
            head++;
            tail = nums.length - 1;
        }
        return result;
    }

    public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();
        //int[] p = {0,0,0};
        int[] p = {-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4};
        List<List<Integer>> list = solution.threeSum(p);
        System.out.println(list);
    }
}
