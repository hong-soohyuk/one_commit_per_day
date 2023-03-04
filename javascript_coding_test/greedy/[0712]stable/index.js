//time: 6.311ms
console.time('time');
function count(dist, arr) {
	let count = 1;
	let point = arr[0];
	for (let i = 1; i < arr.length; i++) {
		if (arr[i] - point >= dist) {
			count++;
			point = arr[i];
		}
	}
	return count;
}
function solution(n, arr) {
	let answer;
	arr.sort((a, b) => a - b);
	let left = 1;
	let right = arr[arr.length - 1];
	while (left <= right) {
		let mid = parseInt((left + right) / 2);
		if (count(mid, arr) >= n) {
			answer = mid;
			left = mid + 1;
		} else right = mid - 1;
	}
	return answer;
}
const arr = [1, 2, 8, 4, 9];
console.log(solution(3, arr));
console.timeEnd('time');
