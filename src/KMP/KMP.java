package KMP;

public class KMP {
    // N >= M
    public static int getIndexOf(String s, String m){
        if(s == null || m == null || m.length() < 1 || s.length() < m.length()){
            return -1;
        }
        char[] s1 = s.toCharArray();
        char[] s2 = m.toCharArray();
        int i1 = 0, i2 = 0;
        int[] next = getNextArray(s2); // O(M)
        // O(N)
        while(i1 < s1.length && i2 < s2.length){
            // 匹配上
            if(s1[i1] == s2[i2]){
                i1++;
                i2++;
            }
            // 没匹配上，且 i2 已经回到开头位置（再没有能继续匹配的）
            // i1 移向下一个位置重新开始匹配
            else if(next[i2] == -1){ // 等同于 i2 == 0
                i1++;
            }
            // 没匹配上，但是 i2 还没回到开头（还有子串能匹配）
            else{
                i2 = next[i2];
            }
        }
        // i1 越界（匹配失败）或 i2 越界（匹配成功）
        return i2 == s2.length ? i1 - i2 : -1;
    }

    public static int[] getNextArray(char[] ms){
        if(ms.length == 1){
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1; // 人为规定 0 位置值为 -1 ， 1 位置值为 0
        int i = 2; // next数组开始的位置
        int cn = 0; // 匹配过的子串长度 & 与当前新加入的字符比较的字符位置
        while(i < next.length){
            // 与已有串接上
            if(ms[i - 1] == ms[cn]){
                next[i++] = ++cn;
            }
            // 没能与已有串接上，回跳到前一个子串（cn位置）
            else if(cn > 0){
                cn = next[cn];
            }
            else{
                next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String s = "abbsabbtcabbsabbabbsabbtcabbsabbeq";
        String m = "abbsabbtcabbsabbe";
        int M = m.length();
        int index = getIndexOf(s, m);
        System.out.println("index: " + index + ", String: " + s.substring(index, index + M));
    }
}
