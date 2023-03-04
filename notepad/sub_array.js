const solution = (n) => {
	let answer = [];
	let is_included = Array.from({ length: n + 1 }, () => false);

	const dfs = (depth) => {
		if (depth === n + 1) {
			let sub_array = '';
			for (let i = 1; i <= depth; i++)
				if (is_included[i]) sub_array += i + ' ';
			if (sub_array.length > 0) answer.push(sub_array.trim());
		} else {
			is_included[depth] = true;
			dfs(depth + 1);
			is_included[depth] = false;
			dfs(depth + 1);
		}
	};
	dfs(1);
	return answer;
};

console.log(solution(3));
