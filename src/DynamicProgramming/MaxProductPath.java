package DynamicProgramming;

/* Leetcode 1594 */
public class MaxProductPath {
    /**
     * 最初，你位于左上角 (0, 0)，每一步你可以在矩阵中向右或向下移动。
     * 从左上角 (0, 0) 开始到右下角 (m - 1, n - 1) 结束的所有路径中，找出具有最大非负积的路径。
     * @param grid m x n 的矩阵
     * @return 沿路径访问的单元格中所有整数的乘积对 1e9 + 7 取余的结果。如果最大积为 负数 ，则返回 -1 。
     */
    public static int maxProductPath(int[][] grid){
        int MOD= (int) 1e9 + 7;
        int m = grid.length;
        int n = grid[0].length;
        long[][] dpMax = new long[m][n];
        dpMax[0][0] = grid[0][0];
        long[][] dpMin = new long[m][n];
        dpMin[0][0] = grid[0][0];

        for(int j = 1; j < n; j++){
            dpMax[0][j] = dpMax[0][j - 1] * grid[0][j];
            dpMin[0][j] = dpMax[0][j];
        }
        for(int i = 1; i < m; i++){
            dpMax[i][0] = dpMax[i - 1][0] * grid[i][0];
            dpMin[i][0] = dpMax[i][0];
            for(int j = 1; j < n; j++){
                if(grid[i][j] >= 0){
                    dpMax[i][j] = Math.max(dpMax[i - 1][j], dpMax[i][j - 1]) * grid[i][j];
                    dpMin[i][j] = Math.min(dpMin[i - 1][j], dpMin[i][j - 1]) * grid[i][j];
                }
                else{
                    dpMax[i][j] = Math.min(dpMin[i - 1][j], dpMin[i][j - 1]) * grid[i][j];
                    dpMin[i][j] = Math.max(dpMax[i - 1][j], dpMax[i][j - 1]) * grid[i][j];
                }
            }
        }
        return Math.max(-1, (int) (dpMax[m - 1][n - 1] % MOD));
    }

    public static void main(String[] args) {
        int[][] grid = {{1, -2, 1}, {1, -2, 1}, {3, -4, 1}};
        /*
         * 分析：
         * {{[1]， -2，    1}，
         *  {[1]， [-2]，  1}，
         *  { 3，  [-4]， [1]}}
         * multi = 1 * 1 * (-2) * (-4) * 1 = 8
         */
//        int[][] grid = {{-1, -2, -3}, {-2, -3, -3}, {-3, -3, -2}};
        System.out.println(maxProductPath(grid));
    }
}
