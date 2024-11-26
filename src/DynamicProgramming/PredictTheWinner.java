package DynamicProgramming;

/* Leetcode 486 */
public class PredictTheWinner {
    /**
     * 两个玩家轮流进行自己的回合，玩家 1 先手。开始时，两个玩家的初始分值都是 0 。
     * 每一回合，玩家从数组的任意一端取一个数字，取到的数字将会从数组中移除。
     * 玩家选中的数字将会加到他的得分上。当数组中没有剩余数字可取时，游戏结束。
     * 假设每个玩家的玩法都会使他的分数最大化。
     * @param nums 整数数组
     * @return 玩家 1 最终得分是否大于等于玩家 2
     */
    public static boolean predictTheWinner(int[] nums){
        int sum = 0;
        for(int n : nums){
            sum += n;
        }
        int n = nums.length;
        int[][] dp = new int[n][n]; // 玩家 1 能获得的最大的分数
        for(int i = 0; i < n ; i++){
            dp[i][i] = nums[i]; // 只有一个数字的时候，就被玩家 1 获取
            // 两个数字时，玩家 1 获取较大的那一个
            if(i < n - 1){
                dp[i][i + 1] = Math.max(nums[i], nums[i + 1]);
            }
        }
        for(int l = n - 3; l >= 0; l--){
            for(int r = l + 2; r < n; r++){
                // 玩家 2 也会选取两种情况里的最优解，因此玩家 1 先取完以后会获得两种情况里分数小的一种情况
                dp[l][r] = Math.max(nums[l] + Math.min(dp[l + 2][r], dp[l + 1][r - 1]),
                        nums[r] + Math.min(dp[l][r - 2], dp[l + 1][r - 1]));
            }
        }
        int sum1 = dp[0][n - 1];
        int sum2 = sum - sum1;
        return sum1 >= sum2;
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 233, 7};
        /*
         * 分析：
         * 玩家 1 一开始选择 "1"，玩家 2 无论选哪个玩家 1 都会选 "233"
         * 玩家 1 : {1, 233}
         * 玩家 2 : {5, 7}
         * 玩家 1 能获胜
         */
        System.out.println(predictTheWinner(nums));
    }
}
