/**
 * @param {number[]} nums
 * @return {number}
 */
var jump = function(nums) {
    let i = 0;
    let jump = 0;
    while (i < nums.length - 1) {
        let next = 0;
        let max = 0;
        for (let j = 1; j <= nums[i]; j++) {
            if (i + j >= nums.length - 1) return jump + 1;
            
            if (i+j+nums[i+j] > max) {
                max = i+j+nums[i+j];
                next = j;
            }
        }
        i = i + next;
        jump++;
    }
    return jump;
};

//https://leetcode.com/problems/jump-game-ii/?envType=problem-list-v2&envId=greedy
