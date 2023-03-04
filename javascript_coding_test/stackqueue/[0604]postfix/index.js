// time: 5.798ms
console.time('time');
function solution(input) {
	let stack = [];
	for (const char of input) {
		if (!isNaN(char)) stack.push(Number(char));
		else {
			let right = stack.pop();
			let left = stack.pop();
			switch (char) {
				case '+':
					stack.push(left + right);
					break;
				case '-':
					stack.push(left - right);
					break;
				case '*':
					stack.push(left * right);
					break;
				case '/':
					stack.push(left / right);
					break;
				default:
					break;
			}
		}
	}

	return stack.pop();
}
const input = '352+*9-';
console.log(solution(input));
console.timeEnd('time');
