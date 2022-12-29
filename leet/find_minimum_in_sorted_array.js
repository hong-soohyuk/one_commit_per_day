/**
 * @param {number[]} nums
 * @return {number}
 */

var findMin = function(nums) {
    if (nums.length < 3) return Math.min(...nums);
    let left = 0, right = nums.length - 1;
    let mid = Math.floor((left + right) / 2);
    return nums[mid] > nums[nums.length - 1] ? 
            findMin(nums.slice(mid)) :
            findMin(nums.slice(0, mid + 1));
}
