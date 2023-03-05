function solution(n, array) {
	let answer = 0;
	let graph = Array.from(Array(n + 1), () => Array());
	let visited = Array.from({ length: n + 1 }, () => false);
	let path = [];

	for (let [a, b] of array) graph[a].push(b);
	const dfs = (current) => {
		if (current === n) {
			++answer;
			console.log(path);
		} else {
			for (let next of graph[current]) {
				if (visited[next] === false) {
					path.push(next);
					visited[next] = true;
					dfs(next);
					visited[next] = false;
					path.pop();
				}
			}
		}
	};
	visited[1] = true;
	path.push(1);
	dfs(1);
	return answer;
}

const sample_input = [
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
console.log(solution(5, sample_input));
