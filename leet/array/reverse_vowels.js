/**
 * @param {string} s
 * @return {string}
 */

var reverseVowels = function (s) {
	const vowels = new Set([
		'a',
		'e',
		'i',
		'o',
		'u',
		'A',
		'E',
		'I',
		'O',
		'U',
	]);
	const array = s.split('');
	let left = 0;
	let right = array.length - 1;

	while (left < right) {
		while (left < right && !vowels.has(array[left])) left++;
		while (left < right && !vowels.has(array[right])) right--;

		if (left < right) {
			[array[left], array[right]] = [
				array[right],
				array[left],
			];
			left++;
			right--;
		}
	}

	return array.join('');
};

/*

https://leetcode.com/problems/reverse-vowels-of-a-string/?envType=study-plan-v2&envId=leetcode-75

1. string in javascript is immutable.
2. remember the fancy way to swap data in an array.

*/
