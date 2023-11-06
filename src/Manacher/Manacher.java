package Manacher;

public class Manacher {
    /**
     * 加特殊字符 '#'
     * @param str 原字符串
     * @return 加完特殊字符后的字符串
     */
    public static char[] manacherString(String str){
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for(int i = 0; i != res.length; i++){
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    /**
     * 求最长回文子串的长度
     */
    public static int maxLcpsLength(String s){
        if(s == null || s.length() == 0){
            return 0;
        }
        char[] str = manacherString(s); // 处理串
        int[] pArr = new int[str.length]; // 回文半径数组
        int C = -1; // 回文串中心
        int R = -1; // 回文串右边界再往右一个位置，有效区域的最右是 R - 1 位置
        int max = Integer.MIN_VALUE; //扩出来的最大值

        // 每个位置都求回文半径
        for(int i = 0; i != str.length; i++){
            // i 至少的回文区域
            /*
             * 三种情况：
             * 1. i 在 R 外
             * * 半径为 1
             * 2. i 在 R 内，且对应位置 i` 的范围不超过大回文范围
             * * 半径为 pArr[2 * C - i]
             * 3. i 在 R 内，且对应位置 i` 的范围超过了大回文边界
             * * 半径为 R - i
             */
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            // 往外扩张变得更大
            while(i + pArr[i] < str.length && i - pArr[i] > -1){
                if(str[i + pArr[i]] == str[i - pArr[i]]){
                    pArr[i]++;
                }
                else{
                    break;
                }
            }
            // 更新最右的位置
            if(i + pArr[i] > R){
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max, pArr[i]);
        }
        return max - 1;
    }

    public static void main(String[] args) {
        String str1 = "abc1234321ab";
        System.out.println(maxLcpsLength(str1));
    }
}
