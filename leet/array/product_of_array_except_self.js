/**
 * @param {number[]} nums
 * @return {number[]}
 */
var productExceptSelf = function(nums) {
    let one_way = Array.from({length: nums.length});
    let to_right = 1
    for (let i = 0; i < nums.length; i++){
        one_way[i] = to_right;
        to_right *= nums[i];
    }
    let round_way = Array.from({length: nums.length});
    let to_left = 1;
    for (let i = nums.length-1; i >= 0; i--){
        round_way[i] = to_left;
        to_left *= nums[i];
        round_way[i] *=  one_way[i];
    }
    return round_way;
};
