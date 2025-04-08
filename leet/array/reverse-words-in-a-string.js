/**
 * @param {string} s
 * @return {string}
 */
var reverseWords = function (s) {
    const words = [];
    let i = 0;
    while (i < s.length) {
        while (i < s.length && s[i] === ' ') i++;
        let start = i;

        while (i < s.length && s[i] !== ' ') i++
        if (start < i)
            words.push(s.slice(start, i));
    }

    return words.reverse().join(' ');
};
/*
https://leetcode.com/problems/reverse-words-in-a-string/?envType=study-plan-v2&envId=leetcode-75
 */
