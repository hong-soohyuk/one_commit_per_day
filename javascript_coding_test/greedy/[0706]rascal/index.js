// time: 6.623ms
console.time('time');
function solution(arr) {
	let h = -1;
	let partner = -1;
	for (let i = arr.length - 1; i >= 0; i--) {
		if (arr[i - 1] > arr[i]) {
			partner = i;
			break;
		}
	}
	for (let i = 0; i < arr.length; i++) {
		if (arr[i] > arr[partner]) {
			h = i;
			break;
		}
	}

	return [h + 1, partner + 1];
}
const arr1 = [120, 125, 152, 130, 135, 135, 143, 127, 160];
const arr2 = [120, 130, 150, 150, 130, 150];
console.log(solution(arr1));
console.log(solution(arr2));
console.timeEnd('time');
