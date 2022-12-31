/**
 * @param {number[]} prices
 * @return {number}
 */
var maxProfit = function(prices) {
    let min = Number.MAX_SAFE_INTEGER;
    let answer = 0;

    let i = -1;
    while (++i < prices.length) {
        let max = 0;
        if (prices[i] < min) {
            min = prices[i];
            let j = i;
            let currentMin = min;
            while (++j < prices.length) {
                if (j + 1 < prices.length && prices[j] > prices[j + 1]) {
                    max += (prices[j] - currentMin);
                    currentMin = prices[j + 1];
                }
                if (j == prices.length - 1 && prices[j]-currentMin > 0)
                    max += (prices[j] - currentMin);
            }
        }
        answer = Math.max(max, answer);
    }
    return answer;
};

// much faster, simpler, more intuitive
/**
 * @param {number[]} prices
 * @return {number}
 */
var maxProfit = function(prices) {
    let min = prices[0];
    let max = prices[0];
    let answer = 0;
    let i = 0;
    while (i < prices.length - 1) {
        while (i < prices.length - 1 && prices[i] >= prices[i + 1]) ++i;
        min = prices[i];
        while (i < prices.length - 1 && prices[i] <= prices[i + 1]) ++i;
        max = prices[i];
        answer += (max - min);
    }
    return answer;
};
