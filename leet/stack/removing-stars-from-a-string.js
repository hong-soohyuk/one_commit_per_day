/**
 * @param {string} s
 * @return {string}
 */
var removeStars = function (s) {
    const ERASER = '*';
    const stack = [];
    for (let i = 0; i < s.length; i++) {
        if (s[i] === ERASER) stack.pop();
        else stack.push(s[i]);
    }
    return stack.join('');
};

/*
https://leetcode.com/problems/removing-stars-from-a-string/description/?envType=study-plan-v2&envId=leetcode-75
*/
