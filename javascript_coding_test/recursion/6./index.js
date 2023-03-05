function solution(limits, weights) {
	let answer;
	const knapsack = (L, W, depth) => {
		if (depth == W.length || L <= 0) return 0;
		if (W[depth] > L) return knapsack(L, W, depth + 1);
		else
			return Math.max(
				weights[depth] +
					knapsack(L - W[depth], W, depth + 1),
				knapsack(L, W, depth + 1)
			);
	};
	answer = knapsack(limits, weights, 0);
	return answer;
}
const weights = [81, 58, 42, 33, 61];
console.log(solution(259, weights));
