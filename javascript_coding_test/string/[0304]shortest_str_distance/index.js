function solution(str, c) {
	let answer = [];

	let distance = str.length;
	for (let i = 0; i < str.length; i++) {
		if (str[i] === c) distance = 0;
		else distance++;

		answer.push(distance);
	}
	distance = str.length;
	for (let i = str.length - 1; i >= 0; i--) {
		if (str[i] === c) distance = 0;
		else distance++;

		answer[i] = Math.min(answer[i], distance);
	}
	return answer;
}
const str = 'teachermode';
console.log(solution(str, 'e'));
