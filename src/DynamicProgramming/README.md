# 动态规划

> 动态规划将小问题的解汇集成大问题的答案，一步步引领我们走向解决问题的彼岸。

能用动态规划解决的问题，需要满足三个条件：最优子结构，无后效性和子问题重叠。

一般有两种思考方式，即“选不选”和“选哪个”。

比起递归需要反复求解相同的子问题。动态规划可以安排求解顺序，对每个子问题只求解一次，并保存此结果。
如果之后还需要使用到该子问题的解，只需要查找保存下来的结果，从而避免重复计算。
即，空间换时间。

## 入门

记忆化搜索

### 爬楼梯

1. [Leetcode 70 爬楼梯](https://leetcode.cn/problems/climbing-stairs/description/)

2. [Leetcode 377 组合总和IV](https://leetcode.cn/problems/combination-sum-iv/description/) ==> [代码](./CombinationSum4.java)

3. [Leetcode 746 最小代价爬楼梯](https://leetcode.cn/problems/min-cost-climbing-stairs/description/)

4. [Leetcode 2266 统计打字方案数](https://leetcode.cn/problems/count-number-of-texts/description/)

5. [Leetcode 2466 统计构造好字符串的方案数](https://leetcode.cn/problems/count-ways-to-build-good-strings/description/) ==> [代码](./CountGoodStrings.java)

### 打家劫舍

1. [Leetcode 198 打家劫舍](https://leetcode.cn/problems/house-robber/description/) ==> [代码](./Rob.java)

2. [Leetcode 213 打家劫舍II](https://leetcode.cn/problems/house-robber-ii/description/) ==> [代码](./Rob.java)

3. [Leetcode 740 删除并获得点数](https://leetcode.cn/problems/delete-and-earn/) ==> [代码](./DeleteAndEarn.java)

4. [Leetcode 2320 统计放置房子的方式数](https://leetcode.cn/problems/count-number-of-ways-to-place-houses/description/)

5. [Leetcode 3186 施咒的最大总伤害](https://leetcode.cn/problems/maximum-total-damage-with-spell-casting/description/) ==> [代码](./MaximumTotalDamage.java)

### 最大子数组和

1. [Leetcode 53 最大子数组和](https://leetcode.cn/problems/maximum-subarray/description/) ==> [代码](./MaxSales.java)

2. [Leetcode 918 环形子数组的最大和](https://leetcode.cn/problems/maximum-sum-circular-subarray/description/) ==> [代码](./MaxSubarraySumCircular.java)

3. [Leetcode 1191 K次串联后最大子数组之和](https://leetcode.cn/problems/k-concatenation-maximum-sum/description/) ==> [代码](./KConcatenationMaxSum.java)

4. [Leetcode 1749 任意子数组和的绝对值的最大值](https://leetcode.cn/problems/maximum-absolute-sum-of-any-subarray/description/) ==> [代码](./MaxAbsoluteSum.java)

5. [Leetcode 2321 拼接数组的最大分数](https://leetcode.cn/problems/maximum-score-of-spliced-array/description/) ==> [代码](./MaximumsSplicedArray.java)

6. [Leetcode 2606 找到最大开销的子字符串](https://leetcode.cn/problems/find-the-substring-with-maximum-cost/description/)

## 网格图DP

对于一些二维 DP（例如背包、最长公共子序列），如果把 DP 矩阵画出来，其实状态转移可以视作在网格图上的移动。  
网格图DP可以视为入门练习，帮助后续二维DP的解题。

### 基础

1. [LCR 166 珠宝的最高价值](https://leetcode.cn/problems/li-wu-de-zui-da-jie-zhi-lcof/description/)

2. [Leetcode 62 不同路径](https://leetcode.cn/problems/unique-paths/description/)

3. [Leetcode 63 不同路径II](https://leetcode.cn/problems/unique-paths-ii/description/)

4. [Leetcode 64 最小路径和](https://leetcode.cn/problems/minimum-path-sum/description/)

5. [Leetcode 120 三角形最小路径和](https://leetcode.cn/problems/triangle/description/) ==> [代码](./MinimumTotal.java)

6. [Leetcode 931 下降路径最小和](https://leetcode.cn/problems/minimum-falling-path-sum/description/)

7. [Leetcode 1289 下降路径最小和II](https://leetcode.cn/problems/minimum-falling-path-sum-ii/description/)

8. [Leetcode 2304 网格中的最小路径代价](https://leetcode.cn/problems/minimum-path-cost-in-a-grid/description/)

9. [Leetcode 2684 矩阵中移动的最大次数](https://leetcode.cn/problems/maximum-number-of-moves-in-a-grid/description/) ==> [代码](./MaxMoves.java)


### 进阶

1. [Leetcode 1594 矩阵的最大非负积](https://leetcode.cn/problems/maximum-non-negative-product-in-a-matrix/description/) ==> [代码](./MaxProductPath.java)

2. [Leetcode 329 矩阵中的最长递增路径](https://leetcode.cn/problems/longest-increasing-path-in-a-matrix/description/)

## 线性DP

### 一维DP

1. [Leetcode 32 最长有效括号](https://leetcode.cn/problems/longest-valid-parentheses/description/)

2. [Leetcode 467 环绕字符串中唯一的子字符串](https://leetcode.cn/problems/unique-substrings-in-wraparound-string/description/)

3. [Leetcode 983 最低票价](https://leetcode.cn/problems/minimum-cost-for-tickets/description/)

### 二维DP

1. [Leetcode 72 编辑距离](https://leetcode.cn/problems/edit-distance/description/) ==> [代码](./MinDistance.java)

2. [Leetcode 97 交错字符串](https://leetcode.cn/problems/interleaving-string/description/) ==> [代码](./IsInterleave.java)

### 最长公共子序列（LCS）

1. [Leetcode 115 不同的子序列](https://leetcode.cn/problems/distinct-subsequences/description/)

2. [Leetcode 516 最长回文子序列](https://leetcode.cn/problems/longest-palindromic-subsequence/description/)

3. [Leetcode 1143 最长公共子序列](https://leetcode.cn/problems/longest-common-subsequence/description/) ==> [代码](./LongestCommonSubsequence.java)

   转化：原始字符串与逆序字符串的最长公共子序列

4. 

### 最长递增子序列（LIS）

1. [Leetcode 300 最长递增子序列](https://leetcode.cn/problems/longest-increasing-subsequence/description/) ==> [代码](./LengthOfLIS.java)

2. [Leetcode 354 俄罗斯套娃信封问题](https://leetcode.cn/problems/russian-doll-envelopes/description/)

   二维的最长递增子序列，有技巧

3. [Leetcode 646 最长数对链](https://leetcode.cn/problems/maximum-length-of-pair-chain/description/)

4. [Leetcode 2111 使数组K递增的最少操作次数](https://leetcode.cn/problems/minimum-operations-to-make-the-array-k-increasing/description/)

5.

## 区间DP

讨论区间的首尾。大范围的问题拆分成若干小范围的问题来求解。

可能性展开的两种常见方式：
1. 基于两侧端点讨论的展开
2. 基于范围上划分点的展开

合并小区间上的最优解进而得出大区间上的最优解。

1. [Leetcode 312 戳气球](https://leetcode.cn/problems/burst-balloons/description/) ==> [代码](./BurstBalloons.java)

2. [Leetcode 486 预测赢家](https://leetcode.cn/problems/predict-the-winner/description/) ==> [代码](./PredictTheWinner.java)

3. [Leetcode 516 最长回文子序列](https://leetcode.cn/problems/longest-palindromic-subsequence/description/) ==> [代码](./LongestPalindromeSubseq.java)

4. [Leetcode 1039 多边形三角剖分的最低得分](https://leetcode.cn/problems/minimum-score-triangulation-of-polygon/description/) ==> [代码](./MinScoreTriangulation.java)

5. [Leetcode 1312 让字符串成为回文串的最少插入次数](https://leetcode.cn/problems/minimum-insertion-steps-to-make-a-string-palindrome/description/) ==> [代码](./MinInsertions.java)

6. [Leetcode 1547 切棍子的最小成本](https://leetcode.cn/problems/minimum-cost-to-cut-a-stick/description/) ==> [代码](./MinCostCutStick.java)

7. [布尔运算](https://leetcode.cn/problems/boolean-evaluation-lcci/description/) ==> [代码](./CountEval.java)

## 背包

### 0-1 背包

可以将 0‑1 背包问题看作一个由$n$轮决策组成的过程，对于每个物体都有不放入和放入两种决策。  
该问题的目标是求解“在限定背包容量下能放入物品的最大价值”。

**第一步：思考每轮的决策，定义状态，从而得到状态转移表**

对于每个物品来说，不放入背包，背包容量不变；放入背包，背包容量减小。对于当前物品编号 $i$ 和背包容量 $c$，前 $i$ 个物品在容量为 $c$ 的背包中最大价值记作 $dp[i][c]$。

**第二步：找出最优子结构，进而推导出状态转移方程**

状态变化的情况：

- **放入物品 $i$** ：背包容量减少 $weight[i]$，物品总价值增加 $value[i]$，状态变化为 $dp[i - 1][c - weight[i]]$
- **不放入物品 $i$** ：背包容量不变，状态保持为 $dp[i - 1][c]$

由此可推导出状态转移方程：
$$
dp[i][c] = max(dp[i - 1][c], dp[i - 1][c - weight[i]] + value[i])
$$
**注**：前提是物品重量不超出背包剩余容量，否则不放入。

**第三步：确定边界条件和状态转移顺序**

当无物品或背包容量为 $0$ 时最大价值为 $0$，即首列 $dp[i][0]$ 和首行 $dp[0][c]$ 都等于 $0$。

<br>

1. [Leetcode 416 分割等和子集](https://leetcode.cn/problems/partition-equal-subset-sum/description/)

2. [Leetcode 494 目标和](https://leetcode.cn/problems/target-sum/) ==> [代码](./FindTargetSumWays.java)

3. [Leetcode 1049 最后一块石头的重量II](https://leetcode.cn/problems/last-stone-weight-ii/description/) ==> [代码]()

4. [Leetcode 2787 将一个数字表示成幂的和的方案数](https://leetcode.cn/problems/ways-to-express-an-integer-as-sum-of-powers/description/) ==> [代码](./NumberOfWays.java)

5. [Leetcode 2915 和为目标值的最长子序列长度](https://leetcode.cn/problems/length-of-the-longest-subsequence-that-sums-to-target/description/)

6. [Leetcode 3180 执行操作可获得的最大总奖励](https://leetcode.cn/problems/maximum-total-reward-using-operations-i/description/) ==> [代码](./MaxTotalReward.java)

7. [夏季特惠](https://leetcode.cn/problems/tJau2o/description/)

### 完全背包

完全背包问题和 0‑1 背包问题非常相似，区别仅在于**不限制**物品的选择次数。因此状态的变化情况如下：

- **放入物品 $i$** ：与 0-1 背包情况不同，状态变化为 $dp[i][c - weight[i]]$
- **不放入物品 $i$** ：与 0-1 背包情况相同，状态保持为 $dp[i - 1][c]$

状态转移方程变为：
$$
dp[i][c] = max(dp[i - 1][c], dp[i][c - weight[i]] + value[i])
$$


## 划分型DP

### 判定能否划分

### 计算划分最优值

1. [Leetcode 91 解码方法](https://leetcode.cn/problems/decode-ways/description/) ==> [代码](./NumDecodings.java)

2. [Leetcode 639 解码方法II](https://leetcode.cn/problems/decode-ways-ii/description/) ==> [代码](./NumDecodings.java)

### 约束划分个数

1. []
2. []
3. []
4. []
5. []
6. []
7. []
8. []
9. []
10. []
11. []
12. []
13. [Leetcode 3117](https://leetcode.cn/problems/minimum-sum-of-values-by-dividing-array/description/) ==> [代码](./MinimumValueSum.java)
