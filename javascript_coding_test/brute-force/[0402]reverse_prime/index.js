const isPrime = (number) => {
	if (number === 1) return false;

	for (let i = 2; i <= parseInt(Math.sqrt(number)); i++) {
		if (number % i === 0) return false;
	}
	return true;
};

function solution(n, arr) {
	let answer = [];
	for (let num of arr) {
		let swap = 0;
		while (num) {
			let temp = num % 10;
			swap = swap * 10 + temp;

			num = parseInt(num / 10);
		}
		if (isPrime(swap)) answer.push(swap);
	}
	return answer;
}
const arr = [32, 55, 62, 20, 250, 370, 200, 30, 100];
console.log(solution(arr.length, arr));
