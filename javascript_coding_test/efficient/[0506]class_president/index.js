// time: 7.292ms
console.time('time');
function solution() {
	let answer = new Map();

	for (let i = 0; i < str.length; i++) {
		let c = str[i];
		if (answer.has(c)) answer.set(c, answer.get(c) + 1);
		else answer.set(c, 1);
	}
	let sortedArr = Array.from(answer).sort((a, b) => b[1] - a[1]);
	console.log(sortedArr);
	return sortedArr[0];
}
const str = 'BACBACCACCBDEDE';

console.log(solution(str));
console.timeEnd('time');
