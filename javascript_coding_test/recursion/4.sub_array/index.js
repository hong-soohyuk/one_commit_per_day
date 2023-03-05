function solution(n) {
	let answer = [];
	let is_included = Array.from({ length: n + 1 }, () => false);
	const dfs = (v) => {
		if (v == n + 1) {
			let sub_string = '';
			for (let i = 1; i <= n; i++)
				if (is_included[i]) sub_string += i + ' ';
			if (sub_string.length > 0)
				answer.push(sub_string.trim());
		} else {
			is_included[v] = true;
			dfs(v + 1);
			is_included[v] = false;
			dfs(v + 1);
		}
	};
	dfs(1);
	return answer;
}

console.log(solution(3));
