/**
 * @param {string} str1
 * @param {string} str2
 * @return {string}
 */

const isDivisible = (string1, string2, index) => {
	if (string1.length % index !== 0 || string2.length % index !== 0)
		return false;

	const subString = string1.substring(0, index);
	if (
		string1.replaceAll(subString, '').length === 0 &&
		string2.replaceAll(subString, '').length === 0
	)
		return true;
	else return false;
};

var gcdOfStrings = function (str1, str2) {
	const length1 = str1.length;
	const length2 = str2.length;

	for (let i = Math.min(length1, length2); i > 0; i--) {
		if (isDivisible(str1, str2, i)) return str1.substring(0, i);
	}
	return '';
};

// https://leetcode.com/problems/greatest-common-divisor-of-strings/?envType=study-plan-v2&envId=leetcode-75
