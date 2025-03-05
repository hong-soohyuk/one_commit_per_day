var mergeAlternately = function (word1, word2) {
	const length = Math.max(word1.length, word2.length);
	const totalLength = word1.length + word2.length;
	const array = new Array({ length: totalLength });
	for (let i = 0; i < length; i++) {
		if (i < word1.length) array[i * 2] = word1[i];
		else array[i * 2] = null;

		if (i < word2.length) array[i * 2 + 1] = word2[i];
		else array[i * 2 + 1] = null;
	}
	return array.filter(Boolean).join('');
};

console.log(mergeAlternately('abc', 'pqr'));

// https://leetcode.com/problems/merge-strings-alternately/description/?envType=study-plan-v2&envId=leetcode-75
