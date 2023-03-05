function solution(n, array) {
	let answer = 0;
	let graph = Array.from(Array(n + 1), () => Array(n + 1).fill(0));
	let visited = Array.from({ length: n + 1 }, () => false);
	let path = [];
	for (let [a, b] of array) graph[a][b] = 1;
	const dfs = (curr) => {
		if (curr === n) {
			++answer;
			console.log(path);
		} else {
			for (let i = 1; i < n + 1; i++) {
				if (graph[curr][i] === 1 && !visited[i]) {
					visited[i] = true;
					path.push(i);
					dfs(i);
					visited[i] = false;
					path.pop();
				}
			}
		}
	};
	path.push(1);
	visited[1] = true;
	dfs(1);
	return answer;
}

const sample_input = [
	[5, 9],
	[1, 2],
	[1, 3],
	[1, 4],
	[2, 1],
	[2, 3],
	[2, 5],
	[3, 4],
	[4, 2],
	[4, 5],
];
console.log(solution(sample_input[0][0], sample_input));
