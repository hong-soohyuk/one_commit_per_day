/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number[][]}
 */
var findDifference = function (nums1, nums2) {
	const first = new Set(nums1);
	const second = new Set(nums2);

	for (const num of first) {
		if (second.has(num)) {
			first.delete(num);
			second.delete(num);
		}
	}

	return [Array.from(first), Array.from(second)];

	//	return [
	//		Array.from(first.values().filter((item) => !second.has(item))),
	//		Array.from(second.values().filter((item) => !first.has(item))),
	//	];
};

/*
https://leetcode.com/problems/find-the-difference-of-two-arrays/description/?envType=study-plan-v2&envId=leetcode-75
1.how to make set from array.
2.how to make array from iterable.
3.faster and better approach with one iteration.
 */
