function solution(n, arr) {
	let answer;
	let max = Number.MIN_SAFE_INTEGER;
	let sum;

	for (const num of arr) {
		let temp = num;
		sum = 0;
		while (temp !== 0) {
			sum += temp % 10;

			temp = Math.floor(temp / 10);
			console.log(temp);
		}
		if (sum > max) {
			max = sum;
			answer = num;
		} else if (sum === max) answer = num > answer ? num : answer;
	}

	return answer;
}
let arr = [128, 460, 603, 40, 521, 137, 123];
console.log(solution(arr.length, arr));
