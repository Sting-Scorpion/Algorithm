package leetcode;

public class DecodeString {
    int i = 0; // 字符串下标
    /**
     * 给定一个经过编码的字符串，返回它解码后的字符串。
     * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
     * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
     * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像3a或2[4]的输入。
     * @param s 经过编码的字符串
     * @return 解码后的字符串
     */
    public String decodeString(String s) {
        //编译思想
        StringBuilder result = new StringBuilder();
        /*
        S -> N[S]S
           | CS
           | Epsilon
        N -> 1 | 2 | 3 | 4 | ...
        C -> a | b | c | d | ...
        */

        // S -> Epsilon
        if(s.length() == i || s.charAt(i) == ']'){
            return "";
        }

        // S -> N[S]S
        else if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
            int times = 0;
            while(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                times = times * 10 + (s.charAt(i) - '0');
                i++;
            }
            i++;//过滤[
            String re = decodeString(s);
            while(times-- > 0){
                result.append(re);
            }
            i++;//过滤]
            result.append(decodeString(s));
        }

        // S -> CS
        else{
            result.append(s.charAt(i++));
            result.append(decodeString(s));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        DecodeString solution = new DecodeString();
        String s = "3[a2[c]]";
        System.out.println(solution.decodeString(s));
    }
}
