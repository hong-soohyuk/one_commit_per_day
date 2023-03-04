// time: 6.587ms
console.time('time');
function solution(arr) {
	for (let i = 1; i < arr.length; i++) {
		let insert = arr[i];
		let j;
		for (j = i - 1; j >= 0; j--) {
			if (insert < arr[j]) arr[j + 1] = arr[j];
			else break;
		}
		arr[j + 1] = insert;
	}

	return arr;
}
const arr = [11, 7, 5, 6, 10, 9];
console.log(solution(arr));
console.timeEnd('time');
