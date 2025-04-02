/**
 * @param {number[]} nums
 * @return {number}
 */
var pivotIndex = function (nums) {
    // let leftSum = 0, rightSum = 0; pivot = 0;

    // for (pivot = 0; pivot < nums.length; pivot++) {
    //     leftSum = 0; rightSum = 0;
    //     for (let left = 0; left < pivot; left++)
    //         leftSum += nums[left];
    //     for (let right = nums.length - 1; right > pivot; right--)
    //         rightSum += nums[right];
    //     if (leftSum === rightSum) return pivot;
    // }
    // return -1;
    // O(n ^ 2)

    // const length = nums.length;
    // const leftArray = new Array(nums.length);
    // const rightArray = new Array(nums.length);
    // leftArray[0] = nums[0];
    // rightArray[length - 1] = nums[length - 1];

    // for (let i = 1; i < length; i++)
    //     leftArray[i] = leftArray[i - 1] + nums[i];
    // for (let j = length - 2; j > -1; j--)
    //     rightArray[j] = rightArray[j + 1] + nums[j];

    // for (let pivot = 0; pivot < length; pivot++)
    //     if (leftArray[pivot] === rightArray[pivot])
    //         return pivot;

    // return -1;
    // O(n)

    const total = nums.reduce((acc, cur) => (acc + cur), 0);
    let leftSum = 0;

    for (let pivot = 0; pivot < nums.length; pivot++) {
        const rightSum = total - leftSum - nums[pivot];

        if (leftSum === rightSum) return pivot;

        leftSum += nums[pivot];
    }
    return -1;
    // O(n)
};
