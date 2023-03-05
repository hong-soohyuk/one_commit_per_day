function solution(array) {
	let answer = 'NO';
	let find = false;
	let total = array.reduce((acc, curr) => acc + curr, 0);
	const dfs = (depth, summary) => {
		if (find) return;
		if (depth === array.length) {
			if (total - summary === summary) {
				answer = 'YES';
				find = true;
			}
		} else {
			dfs(depth + 1, summary + array[depth]);
			dfs(depth + 1, summary);
		}
	};
	dfs(0, 0);
	return answer;
}
const a = [1, 3, 5, 6, 7, 10];
console.log(solution(a));
