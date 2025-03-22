/**
 * @param {number} n
 * @return {number[]}
 */
var countBits = function (n) {
	const dp = Array.from({ length: n + 1 }).fill(0);

	for (let i = 1; i <= n; i++) dp[i] = dp[i >> 1] + (i & 1);

	return dp;
};

/*
   https://leetcode.com/problems/counting-bits/description/?envType=study-plan-v2&envId=leetcode-75
1. this is more like a dp problem.
2. bitwise operation to determine if a number is odd or even.
 */
