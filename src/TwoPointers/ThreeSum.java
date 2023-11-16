package TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Leetcode 15 */
public class ThreeSum {

    /**
     * 判断 nums 中是否存在三个元素 a，b，c ，使得a + b + c = 0
     * 注意：答案中不可以包含重复的三元组。
     * @param nums 包含 n个整数的数组
     * @return 所有和为 0 且不重复的三元组
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first]; // 三数之和为 0 等价于 两数之和为 -a

            /* Two Sum */
            int second = first + 1;
            while(second < third){
                int sum = nums[second] + nums[third];
                if(sum < target){
                    second++;
                }
                else if(sum > target){
                    third--;
                }
                else{
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                    // 需要和上一次的数不相同
                    do{
                        second++;
                    }while(second < n && nums[second] == nums[second - 1]);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        //int[] p = {0,0,0};
        int[] p = {-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4};
        List<List<Integer>> list = threeSum(p);
        System.out.println(list);
    }
}
