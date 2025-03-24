/**
 * @param {number[]} arr
 * @return {boolean}
 */
var uniqueOccurrences = function (arr) {
	const map = new Map();
	arr.forEach((num) => map.set(num, (map.get(num) ?? 1) + 1));

	return new Set(map.values()).size === map.size;
};

/*
https://leetcode.com/problems/unique-number-of-occurrences/description/?envType=study-plan-v2&envId=leetcode-75
*/
