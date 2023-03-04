/**
 * @param {number[]} nums
 * @return {number[]}
 */
var productExceptSelf = function(nums) {
    let answer = [];

		for(let i = 0, multiply = 1; i < nums.length; i++){
			answer[i] = multiply;
			multiply *= nums[i];
		}
	  //1, a, ab, abc, abcd ...
		
		for(let i = nums.length - 1, multiply = 1; i >= 0; i--){
			answer[i] *= multiply;
			multiply *= nums[i];
		}
		//bcd..., acd..., ... abcde...
		return answer;
};

console.log('[1, 2, 3, 4]: ',productExceptSelf([1, 2, 3, 4]));
