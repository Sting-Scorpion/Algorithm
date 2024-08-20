package DynamicProgramming;

/* Leetcode 2684 */
public class MaxMoves {
    /**
     * 从单元格 (row, col)
     * 移动到 (row - 1, col + 1)、(row, col + 1) 和 (row + 1, col + 1)
     * 三个单元格中任一满足值严格大于当前单元格的单元格
     * @param grid m x n 大小的正整数矩阵
     * @return 移动的最大次数
     */
    public static int maxMoves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int [m][2];
        int re = 0;
        for(int j = 1; j < n; j++){
            int max = 0; // 记录每一列最大的步数
            for(int i = 0; i < m; i++){
                int pre = -1; // 记录前一列可通过的格子的最大的步数
                // 比较左边一格
                if(grid[i][j] > grid[i][j - 1] && (j == 1 || dp[i][0] > 0)){
                    pre = Math.max(pre, dp[i][0]);
                }
                // 不为最上一行时，可以与左上的格子比较
                if(i > 0){
                    if(grid[i][j] > grid[i - 1][j - 1] && (j == 1 || dp[i - 1][0] > 0)){
                        pre = Math.max(pre, dp[i - 1][0]);
                    }
                }
                // 不为最下一行时，可以与左下的格子比较
                if(i < m - 1){
                    if(grid[i][j] > grid[i + 1][j - 1] && (j == 1 || dp[i + 1][0] > 0)){
                        pre = Math.max(pre, dp[i + 1][0]);
                    }
                }
                dp[i][1] = pre + 1;
                max = Math.max(max, dp[i][1]);
            }
            // 当这一列最大的步数为 0 时，说明此处已经不可达，中断循环
            if(max == 0){
                break;
            }
            re = Math.max(re, max); // 更新目前为止最大的步数
            // 迭代dp数组
            for(int i = 0; i < m; i++){
                dp[i][0] = dp[i][1];
            }
        }
        return re;
    }

    public static void main(String[] args) {
        int[][] grid = {{3, 2, 4}, {2, 1, 9}, {1, 1, 7}};
        /*
         * 分析：
         * 从第一列的任一单元格开始都无法移动，结果为 0
         */
//        int[][] grid = {
//                {187, 167, 209, 251, 152, 236, 263, 128, 135},
//                {267, 249, 251, 285, 73, 204, 70, 207, 74},
//                {189, 159, 235, 66, 84, 89, 153, 111, 189},
//                {120, 81, 210, 7, 2, 231, 92, 128, 218},
//                {193, 131, 244, 293, 284, 175, 226, 205, 245}};
        System.out.println(maxMoves(grid));
    }
}
