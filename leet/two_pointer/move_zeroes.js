/**
 * @param {number[]} nums
 * @return {void} Do not return anything, modify nums in-place instead.
 */
/**
 * @param {number[]} nums
 * @return {void} Do not return anything, modify nums in-place instead.
 */
/**
 * @param {number[]} nums
 * @return {void} Do not return anything, modify nums in-place instead.
 */
var moveZeroes = function(nums) {
    if (nums.length === 0 || nums.length === 1) return;

    let left = 0, right = 0;
    while (right < nums.length) {

        if (nums[right] === 0) {
            [nums[left], nums[right]] = [nums[right], nums[left]];
            left++;
        }
        right++;
    }
};

/*
https://leetcode.com/problems/move-zeroes/description/?envType=study-plan-v2&envId=leetcode-75
*/
