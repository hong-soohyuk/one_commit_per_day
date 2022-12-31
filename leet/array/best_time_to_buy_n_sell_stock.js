/**
 * @param {number[]} prices
 * @return {number}
 */
var maxProfit = function (prices) {
	let min = Number.MAX_SAFE_INTEGER;
	let max = 0;
	prices.forEach((v, i) => {
		if (v < min) {
			min = v;
			for (let j = i + 1; j < prices.length; j++) {
				max = Math.max(max, prices[j] - v);
			}
		}
	});
	return max;
};
