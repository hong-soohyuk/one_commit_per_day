// time: 5.268ms
console.time('time');
function solution(str) {
	let answer = 0;
	let stack = [];
	for (let i = 0; i < str.length; i++) {
		if (str[i] === '(') stack.push(str[i]);
		else {
			stack.pop();
			if (str[i - 1] === '(') answer += stack.length;
			else answer++;
		}
	}
	return answer;
}

const str1 = '()(((()())(())()))(())';
const str2 = '(((()(()()))(())()))(()())';

console.log('ex1: ', solution(str1));
console.timeEnd('time');
