/*
	 return extra array

	 time complexity : O(n)
	 space complexity : O(1)

	 time: 7.003ms
 */
console.time('time');
function solution(arr1, arr2) {
	let answer = [];

	let i = 0;
	let j = 0;

	while (i < arr1.length && j < arr2.length) {
		if (arr1[i] < arr2[j]) answer.push(arr1[i++]);
		else answer.push(arr2[j++]);
	}
	while (i < arr1.length) answer.push(arr1[i++]);
	while (j < arr2.length) answer.push(arr2[j++]);

	return answer;
}

const arr1 = [1, 3, 5];
const arr2 = [2, 3, 6, 7, 9];
console.log(solution(arr1, arr2));
console.timeEnd('time');
