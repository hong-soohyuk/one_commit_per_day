// time: 7.028ms
console.time('time');
const isAnagram = (str1, str2) => {
	const map = new Map();
	for (const char of str1) {
		if (map.has(char)) map.set(char, map.get(char) + 1);
		else map.set(char, 1);
	}

	for (const char of str2) {
		if (!map.has(char) || map.get(char) === 0) return false;
		else map.set(char, map.get(char) - 1);
	}

	for (const value of map.values()) if (value !== 0) return false;

	return true;
};

function solution(str, ana) {
	let answer = [];
	let length = ana.length;
	let substr = '';

	for (let i = 0; i + length <= str.length; i++) {
		substr = str.slice(i, i + length);
		if (isAnagram(ana, substr)) answer.push(substr);
	}
	console.log(answer);
	return answer.length;
}
const str = 'bacaAacba';
const ana = 'abc';

console.log(solution(str, ana));
console.timeEnd('time');
