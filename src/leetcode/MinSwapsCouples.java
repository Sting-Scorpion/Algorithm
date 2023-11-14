package leetcode;

public class MinSwapsCouples {
    int[] p = new int[70];
    void union(int a, int b) {
        p[find(a)] = p[find(b)];
    }
    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    /**
     * 并查集
     */
    public int minSwapsCouples1(int[] row) {
        int n = row.length, m = n / 2; // m 对情侣
        for (int i = 0; i < m; i++) p[i] = i;
        for (int i = 0; i < n; i += 2) union(row[i] / 2, row[i + 1] / 2);
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            if (i == find(i)) cnt++;
        }
        return m - cnt;
    }

    /**
     * 贪心
     */
    public int minSwapsCouples2(int[] row) {
        int n = row.length;
        int ans = 0;
        int[] cache = new int[n];
        // 数值 -> 下标
        for (int i = 0; i < n; i++) cache[row[i]] = i;
        // 两个一组检查是否成对
        for (int i = 0; i < n - 1; i += 2) {
            // 位置 1 的人 a 与应该与其配对的人 b
            int a = row[i], b = a ^ 1;
            // 把b换过来
            if (row[i + 1] != b) {
                int src = i + 1, tar = cache[b];
                cache[row[tar]] = src;
                cache[row[src]] = tar;
                swap(row, src, tar);
                ans++;
            }
        }
        return ans;
    }
    void swap(int[] nums, int a, int b) {
        int c = nums[a];
        nums[a] = nums[b];
        nums[b] = c;
    }

    public static void main(String[] args) {
        MinSwapsCouples solution = new MinSwapsCouples();
        int[] row = {0, 7, 1, 3, 4, 2, 9, 8, 5, 6};
        System.out.println("s1: " + solution.minSwapsCouples1(row));
        System.out.println("s2: " + solution.minSwapsCouples2(row));
    }
}
