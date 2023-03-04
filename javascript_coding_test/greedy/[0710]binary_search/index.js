// time: 5.218ms
console.time('time');
function divide_conquer(arr, target, left, right) {
	let middle = parseInt((left + right) / 2);
	if (arr[middle] === target) return middle + 1;
	else if (arr[middle] < target)
		return solution(arr, target, middle + 1, right);
	else return solution(arr, target, left, middle - 1);
}
function solution(arr, target, left, right) {
	arr.sort((a, b) => a - b);
	return divide_conquer(arr, target, left, right);
}
const arr = [23, 87, 65, 12, 57, 32, 99, 81];
console.log(solution(arr, 32, 0, arr.length - 1));
console.timeEnd('time');
