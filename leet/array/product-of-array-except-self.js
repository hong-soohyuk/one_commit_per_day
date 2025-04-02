/**
 * @param {number[]} nums
 * @return {number[]}
 */
var productExceptSelf = function (nums) {
    const answer = new Array(nums.length).fill(1);
    let left = 1; right = 1;
    for (let i = 0; i < nums.length; i++) {
        answer[i] *= left;
        left *= nums[i];
    }
    for (let j = nums.length - 1; j > -1; j--) {
        answer[j] *= right;
        right *= nums[j];
    }
    return answer;
};

/*
https://leetcode.com/problems/product-of-array-except-self/description/?envType=study-plan-v2&envId=leetcode-75
*/
