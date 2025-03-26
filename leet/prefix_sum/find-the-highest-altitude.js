/**
 * @param {number[]} gain
 * @return {number}
 */
var largestAltitude = function (gain) {
    let max = 0;
    gain.reduce((acc, cur) => {
        const sum = acc + cur;
        max = Math.max(max, sum);
        return acc + cur;
    }, 0)
    return max;
};
