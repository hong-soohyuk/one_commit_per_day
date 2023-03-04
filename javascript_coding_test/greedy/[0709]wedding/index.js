// time: 5.234ms
console.time('time');
function solution(arr) {
	let answer = 0;
	let count = 0;
	const time_line = [];
	for (const attendee of arr) {
		time_line.push([attendee[0], true]);
		time_line.push([attendee[1], false]);
	}
	time_line.sort((a, b) => {
		if (a[0] === b[0]) return a[1] - b[1];
		else return a[0] - b[0];
	});
	for (const time of time_line) {
		if (time[1]) count++;
		if (!time[1]) count--;
		answer = Math.max(answer, count);
	}
	return answer;
}

const arr = [
	[14, 18],
	[12, 15],
	[15, 20],
	[20, 30],
	[5, 14],
];
console.log(solution(arr));
console.timeEnd('time');
