// time: 8.274ms
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
			let subArray = JSON.parse(JSON.stringify(temp));

			if (sum <= m) answer.push(subArray);
		}
	}
	return answer;
}
const arr = [1, 3, 1, 2, 3];
console.log(solution(arr, 5));
console.timeEnd('time');
