package TwoPointers;

/* Leetcode 3 */
public class LengthOfLongestSubstring {
    /**
     * 找出其中不含有重复字符的 最长子串 的长度
     */
    public static int lengthOfLongestSubstring(String s) {
        int[] map = new int[128];
        int re = 0;
        for (int left = 0, right = 0; right < s.length(); right++) {
            char r = s.charAt(right);
            map[r]++;
            // 有了重复，右移左指针
            while (map[r] > 1) {
                char l = s.charAt(left);
                map[l]--;
                left++;
            }
            re = Math.max(re, right - left + 1);
        }
        return re;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
