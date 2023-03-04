// time: 5.166ms
console.time('time');
function solution(str) {
	let answer = [];
	let poped;
	for (const char of str) {
		answer.push(char);
		if (char === ')') {
			do {
				poped = answer.pop();
			} while (poped !== '(');
		}
	}

	return answer.join('');
}
const str = '(A(BC)D)EF(G(H)(IJ)K)LM(N)';
console.log(solution(str));
console.timeEnd('time');
