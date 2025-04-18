/**
 * @param {number} k
 * @param {number} n
 * @return {number[][]}
 */

const singleDigits = [1, 2, 3, 4, 5, 6, 7, 8, 9];
const getMinimumSum = (count) => {
    let sum = 0;
    for (let i = 1; i <= count; i++) {
        sum += i;
    }
    return sum;
};
const getMaximumSum = (count) => {
   let sum = 0;
        for (let i = 9; i > 9 - count; i--) {
            sum += i;
        }
        return sum;
};

var combinationSum3 = function (k, n) {
    if (n < getMinimumSum(k) || k > getMaximumSum(k)) return [];

    const answer = [];
    
    const backtrack = (start, combination, remainingSum) => {
        if (combination.length === k) {
            if (remainingSum === 0) answer.push(combination);
            return ;
        }
        const remaining = k - combination.length;

        for (let i = start; i <= 9; i++) 
            backtrack(i + 1, [...combination, i], remainingSum - i);
    }

    backtrack(1, [], n);
    return answer;
};

/*
https://leetcode.com/problems/combination-sum-iii/submissions/1610339601/?envType=study-plan-v2&envId=leetcode-75
*/
