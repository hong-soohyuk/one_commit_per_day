/**
 * @param {number} n
 * @return {number}
 */
var tribonacci = function (n) {
    const memo = {
        0: 0,
        1: 1,
        2: 1
    };

    const trib = (n) => {
        if (n in memo) return memo[n];

        memo[n] = trib(n - 1) + trib(n - 2) + trib(n - 3);
        return memo[n];
    };

    return trib(n);
};
