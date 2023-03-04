console.time('time');
/*function solution(arr, n) {
	// nested for loop
	// time: 5.959ms
	let answer = 0;

	for (let i = 0; i < arr.length - n; i++) {
		let sum = 0;
		for (let j = i; j < i + n; j++) sum += arr[j];

		answer = Math.max(answer, sum);
	}

	return answer;
}*/

function solution(arr, n) {
	// time: 5.931ms
	let answer = 0;
	let sum = 0;

	for (let i = 0; i < n; i++) sum += arr[i]; // 1, 2, 3 일차
	answer = sum;

	for (let i = n; i < arr.length; i++) {
		sum += arr[i] - arr[i - n]; // 1, 2, 3 일차 - 4일차 + 1일차
		answer = Math.max(answer, sum);
	}
	return answer;
}
const arr = [12, 15, 11, 20, 25, 10, 20, 19, 13, 15];
console.log(solution(arr, 3));

console.timeEnd('time');
