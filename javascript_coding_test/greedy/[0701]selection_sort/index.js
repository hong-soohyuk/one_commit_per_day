//time: 6.962ms
console.time('time');
function solution(arr) {
	for (let i = 0; i < arr.length; i++) {
		minIndex = i;
		for (let j = i + 1; j < arr.length; j++)
			minIndex = arr[j] < arr[minIndex] ? j : minIndex;
		[arr[i], arr[minIndex]] = [arr[minIndex], arr[i]];
	}
	return arr;
}
const arr = [13, 5, 11, 7, 23, 15];
console.log(solution(arr));
console.timeEnd('time');
