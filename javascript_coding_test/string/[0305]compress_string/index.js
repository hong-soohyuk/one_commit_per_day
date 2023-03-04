function solution(str) {
	str += ' ';
	let ans = '';

	let count = 1;

	for (let i = 0; i < str.length - 1; i++) {
		if (str[i] === str[i + 1]) count++;
		else {
			ans += str[i];
			if (count > 1) ans += String(count);
			count = 1;
		}
	}
	return ans;
}

console.log(solution('KKHSSSSSSSE'));
