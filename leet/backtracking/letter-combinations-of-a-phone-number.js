/**
 * @param {string} digits
 * @return {string[]}
 */
var letterCombinations = function (digits) {
	if (digits == null || digits.length === 0) return [];

	const map = {
		2: 'abc',
		3: 'def',
		4: 'ghi',
		5: 'jkl',
		6: 'mno',
		7: 'pqrs',
		8: 'tuv',
		9: 'wxyz',
	};

	const result = [];
	const make_combination = (index, string) => {
		if (index === digits.length) {
			result.push(string);
			return;
		}

		for (const char of map[digits[index]])
			make_combination(index + 1, string + char);
	};

	make_combination(0, '');
	return result;
};

/*
https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/?envType=study-plan-v2&envId=leetcode-75
 */
