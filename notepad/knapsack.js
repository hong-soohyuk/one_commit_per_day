const solution = (M, W, V) => {
	const length = W.length;
	const memo = new Array(M + 1)
		.fill(-1)
		.map(() => new Array(length + 1).fill(-1));
	const knapsack_memo = (max, depth) => {
		if (depth === length || max === 0) return 0;
		if (memo[max][depth] !== -1) return memo[max][depth];
		if (W[depth] > max)
			return (memo[max][depth] = knapsack_memo(
				max,
				depth + 1
			));
		else
			return (memo[max][depth] = Math.max(
				V[depth] +
					knapsack_memo(
						max - W[depth],
						depth + 1
					),
				knapsack_memo(max, depth + 1)
			));
	};
	return knapsack_memo(M, 0);
};
const weights = [10, 20, 30];
const values = [60, 100, 120];
const max = 50;
console.log(solution(max, weights, values));
