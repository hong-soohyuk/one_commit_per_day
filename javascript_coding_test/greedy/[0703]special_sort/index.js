console.time('time');
function solution(arr) {
	/*
	//time: 6.857ms
	let negative = [];
	let positive = [];

	for (let i = 0; i < arr.length; i++) {
		if (arr[i] > 0) positive.push(arr[i]);
		else negative.push(arr[i]);
	}

	return negative.concat(positive);
	*/

	//	sorting
	//	time: 7.187ms

	for (let i = 0; i < arr.length; i++)
		for (let j = 0; j < arr.length - i - 1; j++)
			if (arr[j] > 0 && arr[j + 1] < 0)
				[arr[j], arr[j + 1]] = [arr[j + 1], arr[j]];
	return arr;
}
const arr = [1, 2, 3, -3, -2, 5, 6, -6];
console.log(solution(arr));
console.timeEnd('time');
