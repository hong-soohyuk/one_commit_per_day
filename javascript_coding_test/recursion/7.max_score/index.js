function solution(MAX_TIME, problems) {
	let answer;
	const knapsack = (M, problems, depth) => {
		if (depth === problems.length || M === 0) return 0;
		if (M < problems[depth][1])
			return knapsack(M, problems, depth + 1);
		else
			return Math.max(
				problems[depth][0] +
					knapsack(
						M - problems[depth][1],
						problems,
						depth + 1
					),
				knapsack(M, problems, depth + 1)
			);
	};
	return knapsack(MAX_TIME, problems, 0);
}
const MAX_TIME = 20;
const problems = [
	[10, 5],
	[25, 12],
	[15, 8],
	[6, 3],
	[7, 4],
];
console.log(solution(MAX_TIME, problems));
