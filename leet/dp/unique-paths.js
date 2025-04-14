/**
 * @param {number} m
 * @param {number} n
 * @return {number}
 */
var uniquePaths = function (m, n) {
    if (m === 1 || n === 1) return 1;
    const dp = Array.from({ length: m }, () => Array.from({ length: n }).fill(undefined));

    // for (let x = 0; x < m; x++) {
    //     for (let y = 0; y < n; y++) {
    //         if (x === 0 || y === 0) dp[x][y] = 1;
    //         else dp[x][y] = dp[x - 1][y] + dp[x][y - 1];
    //     }
    // }
    // return dp[m - 1][n - 1]
    const recursive = (x, y) => {
        if (x === 0 && y === 0) return 0;
        if (x === 0 || y === 0) return 1;
        if (dp[x][y] !== undefined) return dp[x][y];

        dp[x][y] = recursive(x - 1, y) + recursive(x, y - 1);
        return dp[x][y];
    }
    return recursive(m - 1, n - 1);
};
/*
const dp = Array.from({length: m}).fill(Array.from({length: n}).fill(0));
뒤에 있는 배열이 참조로 복사되어서 진짜 2차 배열 생성이 안됨. from 안에 callback 으로 넣어주실게요


대칭으로 memo 를 최적화하려는건 자료구조가 완벽히 대칭일때 시도하자.
https://leetcode.com/problems/unique-paths/?envType=study-plan-v2&envId=leetcode-75
 */
