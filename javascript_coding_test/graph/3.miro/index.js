const dx = [1, -1, 0, 0];
const dy = [0, 0, 1, -1];
let answer = 0;
function solution(miro) {
	const bound_check = (x, y) => {
		return (
			x >= 0 &&
			y >= 0 &&
			x < miro.length &&
			y < miro.length &&
			miro[x][y] === 0
		);
	};
	const dfs = (x, y, miro) => {
		if (x === miro.length - 1 && y == miro.length - 1) ++answer;
		miro[x][y] = 1;
		for (let i = 0; i < 4; i++)
			if (bound_check(x + dx[i], y + dy[i]))
				dfs(x + dx[i], y + dy[i], miro);
		miro[x][y] = 0;
	};
	dfs(0, 0, miro);
	return answer;
}

const sample_input = [
	[0, 0, 0, 0, 0, 0, 0],
	[0, 1, 1, 1, 1, 1, 0],
	[0, 0, 0, 1, 0, 0, 0],
	[1, 1, 0, 1, 0, 1, 1],
	[1, 1, 0, 0, 0, 0, 1],
	[1, 1, 0, 1, 1, 0, 0],
	[1, 0, 0, 0, 0, 0, 0],
];
console.log(solution(sample_input));
