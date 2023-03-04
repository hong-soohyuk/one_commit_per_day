// time: 5.875ms
console.time('time');
const pushCrain = (stack, value) => {
	if (stack.length !== 0 && stack[stack.length - 1] === value) {
		stack.pop();
		return 2;
	} else {
		stack.push(value);
		return 0;
	}
};

function solution(board, moves) {
	let answer = 0;
	let stack = [];
	for (const index of moves) {
		for (let i = 0; i < board.length; i++) {
			const crainPick = board[i][index - 1];
			if (crainPick) {
				answer += pushCrain(stack, crainPick);
				board[i][index - 1] = 0;
				break;
			}
		}
	}

	return answer;
}

const board = [
	[0, 0, 0, 0, 0],
	[0, 0, 1, 0, 3],
	[0, 2, 5, 0, 1],
	[4, 2, 4, 4, 2],
	[3, 5, 1, 3, 1],
];
const moves = [1, 5, 3, 5, 1, 2, 1, 4];

console.log(solution(board, moves));
console.timeEnd('time');
