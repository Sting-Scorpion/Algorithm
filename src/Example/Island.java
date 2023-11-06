package Example;

/*
 * 岛问题
 * 一个矩阵中只有 0 和 1 两种值
 * 每个位置可以和上下左右相连
 * 如果一片 1 连在一起，则称之为岛
 */
public class Island {
    public static int CountIsland(int[][] m){
        if(m == null || m[0] == null){
            return 0;
        }
        int N = m.length;
        int M = m[0].length;
        int res = 0;
        for(int i =0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(m[i][j] == 1){
                    res++;
                    infect(m, i, j, N, M);
                }
            }
        }
        return res;
    }

    /**
     * 时间复杂度 O(N * M)
     * @param m 输入矩阵
     * @param i 当前行
     * @param j 当前列
     * @param N 总行数
     * @param M 总列数
     */
    public static void infect(int[][]m, int i, int j, int N, int M){
        if(i < 0 || i >= N || j < 0 || j >= M || m[i][j] != 1){
            return;
        }
        // i，j没越界且当前位置是1
        m[i][j] = 2;
        infect(m, i + 1, j , N, M);
        infect(m, i - 1, j , N, M);
        infect(m, i, j + 1 , N, M);
        infect(m, i, j - 1 , N, M);
    }

    public static void main(String[] args) {
        int[][] m1 = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 1, 0, 0}};
        int islands = CountIsland(m1);
        System.out.println("The number of islands is: " + islands);
    }
}
