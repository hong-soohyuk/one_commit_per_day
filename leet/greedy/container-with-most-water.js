/**
 * @param {number[]} height
 * @return {number}
 */

var maxArea = function(height) {
    let left = 0;
    let right = height.length - 1;
    let max = 0;

    while (left < right) {
        max = Math.max(max, (right - left) * Math.min(height[left], height[right]));
        if (height[left] < height[right]) left++;
        else right--;
    }
    return max;
};

//https://leetcode.com/problems/container-with-most-water/?envType=problem-list-v2&envId=greedy
