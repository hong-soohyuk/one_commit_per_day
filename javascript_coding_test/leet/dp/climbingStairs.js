/**
 * @param {number} n
 * @return {number}
 */
var climbStairs = function(n) {
    const memo = [];
    return memoStair(n, memo);
};

const memoStair = (n, memo) => {
    if(memo[n]) return memo[n];
    
    else if(n === 1) return 1;
    else if(n === 2) return 2;
    else if(n === 0) return 0;
    else {
        memo[n] = memoStair(n - 1, memo) + memoStair(n - 2, memo);
        return memo[n];
    }
}

console.log('3: ', climbStairs(3));
console.log('44: ', climbStairs(44));
