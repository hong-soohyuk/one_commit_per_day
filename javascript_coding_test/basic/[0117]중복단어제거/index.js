function solution(s) {
	const set = new Set();
	for (let word of s) set.add(word);
	return [...set];
}
let str = ['good', 'time', 'good', 'time', 'student'];
console.log(solution(str));
