// time: 7.129ms
console.time('time');
function solution(arr, m) {
	let answer = [];

	for (let i = 0; i < arr.length; i++) {
		let j = i;
		let sum = 0;
		let temp = [];
		while (j < arr.length && sum < m) {
			sum += arr[j];
			temp.push(arr[j++]);
			if (sum === m) answer.push(temp);
		}
	}

	return answer;
}
const arr = [1, 2, 1, 3, 1, 1, 1, 2];
console.log(solution(arr, 6));
console.timeEnd('time');
