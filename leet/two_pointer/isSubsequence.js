/**
 * @param {string} s
 * @param {string} t
 * @return {boolean}
 */
var isSubsequence = function (s, t) {
	let si = 0,
		ti = 0;
	while (si < s.length && ti < t.length) {
		if (s.charAt(si) === t.charAt(ti)) si++;
		ti++;
	}
	return si === s.length;
};

/*
 https://leetcode.com/problems/is-subsequence/?envType=study-plan-v2&envId=leetcode-75
 */
