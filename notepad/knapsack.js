const solution = (M, W, V) => {
	const length = W.length;
	const memo = new Array(M + 1)
		.fill(-1)
		.map(() => new Array(length + 1).fill(-1));
	const knapsack = (max, depth) => {
		if (depth === length || max === 0) return 0;
		if (memo[max][depth] !== -1) return memo[max][depth];
		if (W[depth] > max)
			return (memo[max][depth] = knapsack(max, depth + 1));
		else
			return (memo[max][depth] = Math.max(
				V[depth] + knapsack(max - W[depth], depth + 1),
				knapsack(max, depth + 1)
			));
	};
	let answer = knapsack(M, 0);
	console.log(memo);
	return answer;
};
const weights = [10, 20, 30];
const values = [60, 100, 120];
const max = 50;
console.log(solution(max, weights, values));
