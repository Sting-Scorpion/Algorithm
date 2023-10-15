package leetcode;

import java.util.*;

public class Solution {

    /**
     * 给定一个整数数组和一个整数k，请找到该数组中和为k的连续子数组的个数。
     * @param nums 整数数组
     * @param k 目标和
     * @return 返回和为k的子数组数量
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0, pre = 0;
        // <前缀和，数量>
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            // 前缀和
            pre += num;
            // 找到pre[j] - pre[i] = k 的i, j对
            if (map.containsKey(pre - k)) {
                count += map.get(pre - k);
            }
            // 记录前缀和为pre的数量
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return count;
    }

    /**
     * 给定一个字符串数组 words，请计算当两个字符串 words[i] 和 words[j] 不包含相同字符时，它们长度的乘积的最大值。
     * 假设字符串中只包含英语的小写字母。如果没有不包含相同字符的一对字符串，返回 0。
     * @param words 字符串数组
     * @return 无相同字符的两字符串最大长度乘积
     */
    public int maxProduct(String[] words) {
        int length = words.length;
        // 用26位二进制数表示位掩码，表示字母是否出现
        int[] masks = new int[length];
        for (int i = 0; i < length; i++) {
            String word = words[i];
            int wordLength = word.length();
            // 第i个字符串的第j个字符
            for (int j = 0; j < wordLength; j++) {
                masks[i] |= 1 << (word.charAt(j) - 'a');
            }
        }

        int maxProd = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                // 按位与，检测是否有相同字符
                if ((masks[i] & masks[j]) == 0) {
                    maxProd = Math.max(maxProd, words[i].length() * words[j].length());
                }
            }
        }
        return maxProd;
    }

    /**
     * 给你一个整数数组 nums ，除某个元素仅出现一次外，其余每个元素都恰出现三次
     * @param nums 整数数组
     * @return 仅出现一次的元素
     */
    public int singleNumber(int[] nums) {
        int re = 0;
        for(int i = 0; i < 32; i++){
            int total = 0;
            for(int num : nums){
                // 取第i个二进制位的和
                total += ((num >> i) & 1);
            }
            // 找到不能被3整除的第i个二进制位
            // 即为所求答案的第i个二进制位上的1
            if(total % 3 != 0){
                re |= (1 << i);
            }
        }
        return re;
    }

    /**
     * 输入分子与分母，返回小数形式，循环节用括号表示
     * @param numerator 分数的分子
     * @param denominator 分数的分母
     * @return 分数化为小数形式
     */
    public String fraction2decimal(int numerator, int denominator){
        StringBuilder re = new StringBuilder();
        // 有限
        if(numerator % denominator == 0 || denominator % 2 == 0 || denominator % 5 ==0){
            re.append(numerator / (double)denominator);
        }
        // 无限循环
        else{
            // 分母化为 10^n-1 的形式，便于计算循环节
            int i = 10;
            while((i - 1) % denominator != 0){
                i *= 10;
            }
            // 整数部分
            int integer = numerator / denominator;
            if(integer != 0){
                re.append(integer);
            }
            // 小数部分
            re.append(".(");
            // 循环节
            int numerator_new = (i - 1) / denominator * numerator;
            re.append(numerator_new);
            re.append(")");
        }
        return re.toString();
    }

    public String customSortString(String order, String s) {
        int l1 = order.length();
        int l2 = s.length();
        Map<Character, Integer> hash = new HashMap<>();
        for(int i = 0; i < l1; i++){
            hash.put(order.charAt(i), i);
        }

        char[] arrs = s.toCharArray();
        for(int i = 1; i < l2; i++){
            char tmp = arrs[i];
            int j = i;
            while(j >= 1 && hash.getOrDefault(tmp, 26) < hash.getOrDefault(arrs[j - 1], 26)){
                arrs[j] = arrs[j - 1];
                j--;
            }
            arrs[j] = tmp;
        }

        return String.copyValueOf(arrs);
    }

    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        if(n == 1) return 0;
        int len = strs[0].length();
        int re = 0;
        for(int i = 0; i < len; i++){
            for(int j = 0; j < n - 1; j++){
                if(strs[j].charAt(i) > strs[j + 1].charAt(i)){
                    re--;
                    break;
                }
            }
            re++;
        }
        return re;
    }

    //
    int i = 0;
    Stack<String> st = new Stack<>();
    public boolean isValid(String s) {
        boolean stag = subTag(s);
        return stag && i >= s.length();
    }
    public boolean tagContent(String s){
        while(i < s.length()){
            if(s.charAt(i) == '<'){
                if(s.charAt(i + 1) == '!'){
                    boolean stag = cdataTag(s);
                    if(stag){
                        continue;
                    }
                    else{
                        return false;
                    }
                }
                else if(s.charAt(i + 1) == '/'){
                    if(!st.isEmpty()){
                        return true;
                    }
                }
                else if(s.charAt(i + 1) >= 'A' && s.charAt(i + 1) <= 'Z'){
                    boolean stag = subTag(s);
                    if(!stag) return false;
                }
                else{
                    return false;
                }
            }
            else{
                i++;
            }
        }
        return false;
    }
    public boolean startTag(String s){
        int tmp = i++;
        int n = 0;
        while(i < s.length() && s.charAt(i) != '>'){
            if(s.charAt(i) < 'A' || s.charAt(i) > 'Z'){
                i = tmp;
                return false;
            }
            n++;
            i++;
        }
        if(i >= s.length()){
            i = tmp;
            return false;
        }
        if(n >= 1 && n <= 9){
            String sub = s.substring(i - n, i);
            st.push(sub);
            i++;
            return true;
        }else{
            i = tmp;
            return false;
        }
    }
    public boolean endTag(String s){
        int tmp = i++;
        int n = 0;
        if(i >= s.length() || s.charAt(i++) != '/'){
            i = tmp;
            return false;
        }
        if(st.isEmpty()){
            i = tmp;
            return false;
        }
        String tag = st.pop();
        while(i < s.length() && s.charAt(i) != '>'){
            n++;
            i++;
        }
        if(i >= s.length()){
            i = tmp;
            return false;
        }
        String sub = s.substring(i - n, i);
        if(sub.equals(tag)){
            i++;
            return true;
        }else{
            i = tmp;
            return false;
        }
    }
    public boolean cdataTag(String s){
        String CDATA1 = "<![CDATA[", CDATA2 = "]]>";
        if(i + 8 < s.length() && s.substring(i, i + 9).equals(CDATA1)){
            if(i == 0) return false;
            int j = i + 9;
            boolean ok = false;
            while (j < s.length() && !ok) {
                if (j + 2 < s.length() && s.substring(j, j + 3).equals(CDATA2)) {
                    j = j + 3;
                    ok = true;
                }else{
                    j++;
                }
            }
            if (!ok) return false;
            i = j;
            return true;
        }
        return false;
    }
    public boolean subTag(String s){
        boolean tag = startTag(s);
        if(tag){
            if(tagContent(s)){
                return endTag(s);
            }
        }
        return false;
    }

    /**
     * @param n 给定的和
     * @return 和为 n 的完全平方数的最少数量
     */
    public int numSquares(int n) {
        int[] sq = new int[101];
        int[] dp = new int[n + 1];
        for(int i = 1; i <= n; i++){
            int s = (int)Math.sqrt(i);
            if(s * s == i){
                dp[i] = 1;
                sq[s] = i;
            }
            else{
                for(int j = s; j > 0; j--){
                    int p = dp[sq[j]] + dp[i - sq[j]];
                    if(dp[i] == 0) dp[i] = p;
                    else{
                        if(dp[i] > p) dp[i] = p;
                    }
                }
            }
        }
        return dp[n];
    }

    /**
     * 给定一个 m x n 的矩阵，如果一个元素为0，则将其所在行和列的所有元素都设为0。使用原地算法
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean flagCol0 = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                flagCol0 = true;
            }
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (flagCol0) {
                matrix[i][0] = 0;
            }
        }
    }

    /**
     * 你最初位于数组的第一个位置。数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     * @param nums
     * @return 最少跳跃次数
     */
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[n - 1] = 0;
        for(int i = n - 2; i >= 0; i--){
            dp[i] = 9999;
            for(int j = 0; j <= nums[i] && i + j < n; j++){
                dp[i] = Math.min(dp[i], 1 + dp[i + j]);
            }
        }
        return dp[0];
    }

    /**
     * 每次操作中，选择任意一个nums[i]，删除它并获得nums[i]的点数。
     * 之后，你必须删除所有等于nums[i] - 1和nums[i] + 1的元素。
     *开始你拥有 0 个点数。
     * @param nums
     * @return 返回你能通过这些操作获得的最大点数。
     */
    public int deleteAndEarn(int[] nums) {
        //乱序打家劫舍
        int maxVal = 0;
        for(int num : nums){
            if(num > maxVal){
                maxVal = num;
            }
        }
        int[] point = new int[maxVal];
        for(int num : nums){
            point[num - 1] += num;
        }
        return rob(point);
    }

    /**
     * 每间房内都藏有一定的现金，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * @param nums 代表每个房屋存放金额的非负整数数组
     * @return 计算不触动警报装置的情况下，一夜之内能够偷窃到的最高金额
     */
    public int rob(int[] nums) {
        int n = nums.length;
        int[] re = new int[n];
        re[0] = nums[0];
        if(n == 1) return re[0];
        re[1] = Math.max(re[0], nums[1]);
        for(int i = 2; i < n; i++){
            re[i] = Math.max(re[i - 1], re[i - 2] + nums[i]);
        }
        return re[n - 1];
        /*
        //优化版，更节约空间
        if(nums == null || nums.length == 0){
            return 0;
        }
        int n = nums.length;
        if(n == 1) return nums[0];
        int pre = nums[0];
        int cur = Math.max(pre, nums[1]);
        for(int i = 2; i < n; i++){
            int tmp = Math.max(pre + nums[i], cur);
            pre = cur;
            cur = tmp;
        }
        return cur;
         */
    }

    /**
     * 单链表 L 表示为：
     * L0 → L1 → … → Ln - 1 → Ln
     * 请将其重新排列后变为：
     * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
     * @param head 单链表头节点
     */
    public void reorderList(ListNode head) {
        if(head.next == null || head.next.next == null) return;
        Stack<ListNode> tmp = new Stack<>();
        ListNode t = head;
        while(t != null){
            tmp.push(t);
            t = t.next;
        }
        t = head;
        ListNode insert;
        while(tmp.peek() != t){
            insert = tmp.pop();
            insert.next = t.next;
            t.next = insert;
            t = insert.next;
            if(t.next == t){
                break;
            }
        }
        t.next = null;
    }

    /**
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回-1 。
     * 你可以认为每种硬币的数量是无限的。
     * @param coins 表示不同面额的硬币
     * @param amount 表示总金额
     * @return 可以凑成总金额所需的 最少的硬币个数
     */
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    int i1 = 0;//字符串下标
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

    /**
     * 二叉树的中序遍历 。
     * @param root 二叉树的根节点
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> tmp = new Stack<>();
        while(root != null || !tmp.isEmpty()){
            while(root != null){
                tmp.push(root);
                root = root.left;
            }
            TreeNode tar = tmp.pop();
            result.add(tar.val);
            root = tar.right;
        }
        return result;
    }

    /**
     * 请按照顺时针螺旋顺序 ，返回矩阵中的所有元素。
     * @param matrix m行n列的矩阵
     * @return
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
            if(direction %2 == 0){
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

    /**
     * 判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0
     * 注意：答案中不可以包含重复的三元组。
     *
     * @param nums 包含 n个整数的数组
     * @return 所有和为 0 且不重复的三元组
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        if(nums.length < 3) return result;
        Arrays.sort(nums);
        int head = 0, tail = nums.length - 1;
        int sum;
        while(tail - head >= 2){
            while(tail - head >= 2){
                sum = nums[head] + nums[tail];
                int i;
                for(i = 1; i < tail - head; i++){
                    if(sum + nums[tail - i] < 0) {
                        //i++;
                        break;
                    }
                    else if(sum + nums[tail - i] == 0){
                        List<Integer> tmp = new LinkedList<>();
                        tmp.add(nums[head]);
                        tmp.add(nums[tail - i]);
                        tmp.add(nums[tail]);
                        if(!result.contains(tmp))
                            result.add(tmp);
                    }
                }
                //if(sum + nums[tail - i + 1] < 0) break;
                tail--;
            }
            head++;
            tail = nums.length - 1;
        }
        return result;
    }


    public int[] platesBetweenCandles(String s, int[][] queries) {
        int[] num = new int[s.length()];
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '*') continue;
            if(s.charAt(i) == '|'){
                int n = 0;
                int t = i + 1;
                while(t < s.length() && s.charAt(t) != '|'){
                    n++;
                    t++;
                }
                if(t < s.length()){
                    num[i] = n;
                    i = --t;
                }
                else{
                    break;
                }
            }
        }

        int[] result = new int[queries.length];
        for(int i = 0; i < result.length; i++){
            int j = queries[i][0];
            int k = queries[i][1];
            int count = 0;
            while(j <= k){
                if(num[j] == 0) j++;
                else{
                    while(j <= k){
                        count += num[j];
                        j += num[j] + 1;
                    }
                }
            }
            result[i] = count;
        }
        return result;
    }


    public static void main(String[] args){
        Solution b = new Solution();
        int[][] test = {{8, 5}, {1, 3}, {11, 13}};
        for(int i = 0; i < test.length; i++){
            String re = b.fraction2decimal(test[i][0], test[i][1]);
            System.out.println(re);
        }

        /*
        String order = "cba";
        String s = "abcd";
        System.out.println(b.customSortString(order, s));
        String[] s = {"cba", "daf", "ghi"};
        b.minDeletionSize(s);
        String s = "<DIV>This is the first line <![CDATA[<div>]]></DIV>";
        System.out.println(b.isValid(s));
        int[][] re = {{0,1,1,0},{1,1,1,2},{1,5,1,1}};
        b.setZeroes(re);
        ListNode a4 = new ListNode(4);
        ListNode a3 = new ListNode(3, a4);
        ListNode a2 = new ListNode(2, a3);
        ListNode a1 = new ListNode(1, a2);
        b.reorderList(a1);
        int[] coin = {1,2,5};
        System.out.println(b.coinChange(coin, 50));
        String s = "3[a2[c]]";
        System.out.println(b.decodeString(s));
        TreeNode root = new TreeNode(1,null, new TreeNode(2,new TreeNode(3),null));
        System.out.println(b.inorderTraversal(root));
        int[][] q = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        System.out.println(b.spiralOrder(q));
        //int[] p = {0,0,0};
        int[] p = {-1,0,1,2,-1,-4,-2,-3,3,0,4};
        b.threeSum(p);
        int[][] k = {{1,17},{4,5},{14,17},{5,11}};
        int[] a = b.platesBetweenCandles("***|**|*****|**||**|*", k);
        System.out.println(Arrays.toString(a));
        //*/
    }
}
