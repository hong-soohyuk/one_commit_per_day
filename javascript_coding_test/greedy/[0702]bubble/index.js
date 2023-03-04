// time: 6.181ms
console.time('time');
function solution(arr) {
	for (let i = 0; i < arr.length; i++) {
		for (let j = i; j < arr.length - 1; j++) {
			if (arr[j] > arr[j + 1]) [arr[j], arr[j + 1]] = [arr[j + 1], arr[j]];
		}
	}
	return arr;
}

const arr = [13, 5, 11, 7, 23, 15];
console.log(solution(arr));
console.timeEnd('time');
