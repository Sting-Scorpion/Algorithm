package TwoPointers;

/* Leetcode 42 */
public class Trap {
    /**
     * 计算按此排列的柱子，下雨之后能接多少雨水
     * @param height 宽为 1 的柱子的高度
     * @return 接几单位的水
     */
    public static int trap(int[] height) {
        int re = 0;
        int left = 0, right = height.length - 1;
        int lmax = 0, rmax = 0; // 左右最高的界限
        while(left < right){
            lmax = Math.max(height[left], lmax);
            rmax = Math.max(height[right], rmax);
            // 哪边低就以哪边为基准
            if(height[left] < height[right]){
                re += lmax - height[left];
                left++;
            }
            else{
                re += rmax - height[right];
                right--;
            }
        }
        return re;
    }

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        /*
         * # -- 水 ； * -- 柱子
         *
         * |
         * |                     *
         * |         *  #  #  #  *  *  #  *
         * |   *  #  *  *  #  *  *  *  *  *  *
         * |--------------------------------------
         */
        System.out.println(trap(height));
    }
}
