// time: 5.87ms
console.time('time');
function solution(arr) {
	let end_time;
	let answer;

	answer = new Set();
	arr.sort((a, b) => (a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]));
	end_time = arr[0][0];
	for (let start_time of arr) {
		if (start_time[0] >= end_time) {
			end_time = start_time[1];
			answer.add(start_time);
		}
	}
	return answer;
}
const arr = [
	[1, 4],
	[2, 3],
	[3, 5],
	[4, 6],
	[5, 7],
];
const arr2 = [
	[3, 3],
	[1, 3],
	[2, 3],
];
console.log(solution(arr));
console.log(solution(arr2));
console.timeEnd('time');
