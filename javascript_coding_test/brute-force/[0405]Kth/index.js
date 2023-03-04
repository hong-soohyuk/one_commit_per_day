console.time('time');
function solution(k, arr) {
	const set = new Set();

	for (let i = 0; i < arr.length; i++) {
		for (let j = i + 1; j < arr.length; j++) {
			for (let k = j + 1; k < arr.length; k++) {
				set.add(arr[i] + arr[j] + arr[k]);
			}
		}
	}

	setToArr = Array.from(set).sort((a, b) => b - a);
	return setToArr[k - 1];
}

const arr = [13, 15, 34, 23, 45, 65, 33, 11, 26, 42];
console.log(solution(3, arr));
console.timeEnd('time');
