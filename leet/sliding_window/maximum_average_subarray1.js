/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var findMaxAverage = function (nums, k) {
	let windowSum = nums.slice(0, k).reduce((acc, curr) => acc + curr, 0);
	let max = windowSum;
	for (let i = k; i < nums.length; i++) {
		windowSum += nums[i] - nums[i - k];
		max = Math.max(max, windowSum);
	}
	return max / k;
};

/*
https://leetcode.com/problems/maximum-average-subarray-i/description/?envType=study-plan-v2&envId=leetcode-75
*/
