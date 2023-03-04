function solution(n, m, arr) {
	arr.sort((a, b) => a[0] + a[1] - (b[0] + b[1]));

	let max = Number.MIN_SAFE_INTEGER;
	let sum;
	for (let i = 0; i < n; i++) {
		let count = 1;
		sum = arr[i][0] / 2 + arr[i][1];
		for (let j = 0; j < n; j++) {
			if (i !== j) {
				sum += arr[j][0] + arr[j][1];
				if (sum > m) break;
				count++;
			}
			max = Math.max(max, count);
		}
	}

	return max;
}
const arr = [
	[6, 6],
	[2, 2],
	[4, 3],
	[4, 5],
	[10, 3],
];
console.log(solution(5, 28, arr));
