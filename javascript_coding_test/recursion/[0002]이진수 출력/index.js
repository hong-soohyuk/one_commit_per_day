function solution(n) {
	let result = '';
	function recursion(n) {
		if (n === 0) return;
		else {
			recursion(parseInt(n / 2));
			result += String(n % 2);
		}
	}
	recursion(n);
	return result;
}
console.log(solution(11));
