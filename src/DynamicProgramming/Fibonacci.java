package DynamicProgramming;

public class Fibonacci {
    /**
     * 求斐波那契数
     * 答案需要取模 1e9+7(1000000007)
     * @param n 第 n 个数
     * @return 第 n 个 Fibonacci 数的值
     */
    public static int fib(int n) {
        final int MOD = 1000000007;
        if (n < 2) {
            return n;
        }
        int f1 = 0, f2 = 1;
        for (int i = 2; i <= n; i++) {
            int t = f1 + f2;
            f1 = f2;
            f2 = t % MOD;
        }
        return f2;
    }

    public static void main(String[] args) {
        int n = 6;
        System.out.println(fib(n));
    }
}
