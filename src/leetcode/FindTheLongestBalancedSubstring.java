package leetcode;

public class FindTheLongestBalancedSubstring {

    /**
     * 如果子字符串中所有的 0 都在 1 之前，且其中 0 的数量等于 1 的数量，
     * 则认为 s 的这个子字符串是平衡子字符串。
     * 注：空子字符串也视作平衡子字符串
     * @param s 仅由 0 和 1 组成的二进制字符串
     * @return s 中最长的平衡子字符串长度
     */
    public int findTheLongestBalancedSubstring(String s) {
        int n = s.length();
        char[] c = s.toCharArray();
        int re = 0;
        int i = 0;

        while (i < n) {
            int a = 0, b = 0;
            while (i < n && c[i] == '0' && ++a >= 0) i++;
            while (i < n && c[i] == '1' && ++b >= 0) i++;
            re = Math.max(re, Math.min(a, b) * 2);
        }
        /*
        while(i < n){
            if(c[i] == '0'){
                int t = 1;
                int sum = -1;
                boolean isOne = false;
                i++;
                while(sum != 0){
                    if(c[i] == '0'){
                        if(isOne){
                            break;
                        }
                        sum--;
                        t++;
                    }
                    else{
                        isOne = true;
                        sum++;
                        t++;
                    }
                    i++;
                }
                int len = t + sum;
                re = Math.max(re, len);
            }
            else i++;
        }
         */
        return re;
    }

    public static void main(String[] args) {
        FindTheLongestBalancedSubstring solution = new FindTheLongestBalancedSubstring();
        String s = "01000111";
        int l = solution.findTheLongestBalancedSubstring(s);
        System.out.println(l);
    }
}
