/**
 * @param {number[]} nums
 * @return {number}
 */
var singleNumber = function (nums) {
    return nums.reduce((acc, cur) =>
        acc === 0 ? cur : acc ^ cur
    );
};
