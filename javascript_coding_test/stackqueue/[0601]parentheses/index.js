//time: 5.459ms
console.time('time');
function solution(str) {
	let stack = [];
	for (const cha of str) {
		if (cha === '(') stack.push(cha);
		else {
			if (stack.length === 0) return 'NO';
			else stack.pop();
		}
	}

	return stack.length === 0 ? 'YES' : 'NO';
}
const str = '(()(()))(()';
console.log(solution(str));
console.timeEnd('time');
