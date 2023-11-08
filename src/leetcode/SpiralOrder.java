package leetcode;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrder {
    /**
     * 请按照顺时针螺旋顺序 ，返回矩阵中的所有元素。
     * @param matrix m行n列的矩阵
     * @return 顺时针螺旋顺序
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int n = matrix.length;
        int m = matrix[1].length;
        int[][] move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int direction = 0;
        int a = 0, b = -1;
        while(!(m == 0 || n == 0)){
            int step = 0;
            if(direction % 2 == 0){
                while(step < m){
                    a += move[direction][0];
                    b += move[direction][1];
                    result.add(matrix[a][b]);
                    step++;
                }
                direction = (direction + 1) % 4;
                n--;
            }
            else{
                while(step < n){
                    a += move[direction][0];
                    b += move[direction][1];
                    result.add(matrix[a][b]);
                    step++;
                }
                direction = (direction + 1) % 4;
                m--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SpiralOrder solution = new SpiralOrder();
        int[][] q = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        System.out.println(solution.spiralOrder(q));
    }
}
