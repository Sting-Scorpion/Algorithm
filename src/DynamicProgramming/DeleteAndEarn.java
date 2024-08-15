package DynamicProgramming;

/* Leetcode 740 */
public class DeleteAndEarn {
    /**
     * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除所有等于 nums[i] - 1 和 nums[i] + 1 的元素。
     * @param nums 整数数组
     * @return 能通过这些操作获得的最大点数
     */
    public static int deleteAndEarn(int[] nums){
        /*
         * 高级版打家劫舍
         * 房子打乱了顺序，且不一定连续
         * 偷了 nums[i] 后，再偷 nums[i] - 1 和 nums[i] + 1 会报警
         * 但是再偷一次 nums[i] 就触发bug，不会报警
         */
        int max = 0; // 最大的房屋号
        for(int num : nums){
            if(num > max){
                max = num;
            }
        }
        int[] house = new int[max + 1]; // 记录 house[房子序号] = 能偷的次数
        for(int num : nums){
            house[num]++;
        }
        int[] dp = new int[max + 1]; // 记录每个房子号能获得的最大金额
        dp[1] = house[1];
        for(int i = 2; i <= max; i++){
            dp[i] = Math.max(house[i] * i + dp[i - 2], dp[i - 1]);
        }
        return dp[max];
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 3, 3, 3, 4};
        /*
         * 分析：
         * {3, 3, 3}
         * sum = 9
         */
        System.out.println(deleteAndEarn(nums));
    }
}
