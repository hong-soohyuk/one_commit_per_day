/**
 * @param {number[]} nums
 * @return {boolean}
 */
var containsDuplicate = function(nums) {
    /*
    const duplicate = new Set();
    for(const value of nums)
        if(duplicate.has(value)) return true;
        else duplicate.add(value);
    return false;
    */
    
    const duplicate = new Set(nums);
    return nums.length !== duplicate.size;
};

console.log(containsDuplicate([1,2,3,4,]));
console.log(containsDuplicate([1,1,3,4,]));
console.log(containsDuplicate([2,2,3,4,]));
