/**
 * @param {number} n
 * @return {number[]}
 */
// var countBits = function (n) {
//     const arr = [];
//     for (let i = 0; i < n + 1; i++) {
//         let num = i;
//         let count = 0;

//         while (num > 0) {
//             count = count + (num & 1);
//             num = num >> 1;
//         }
//         arr.push(count);
//         count = 0;
//     }
//     return arr;
// };

var countBits = function (n) {
    const dp = Array(n + 1).fill(0);
    for (let i = 0; i < n + 1; i++) dp[i] = dp[i >> 1] + (i & 1);
    return dp;
};
