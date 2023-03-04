console.time('time');
function solution(str1, str2) {
	const map = new Map();

	for (const cha of str1) {
		if (map.has(cha)) map.set(cha, map.get(cha) + 1);
		else map.set(cha, 1);
	}

	for (const cha of str2) {
		if (!map.has(cha) || map.get(cha) == 0) return 'NO';
		map.set(cha, map.get(cha) - 1);
	}

	for (const value of map.values()) {
		if (value !== 0) return 'NO';
	}
	return 'YES';
}
const str1 = 'AAbaAeCe';
const str2 = 'baeeACA';
console.log(solution(str1, str2));

console.timeEnd('time');
