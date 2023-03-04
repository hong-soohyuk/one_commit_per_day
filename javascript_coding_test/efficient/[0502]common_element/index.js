//time: 5.766ms
console.time('time');
function solution(arr1, arr2) {
	for (const element of arr2) arr1.push(element);

	arr1.sort((a, b) => a - b);

	for (let i = 1; i < arr1.length; i++)
		if (arr1[i - 1] === arr1[i]) console.log(arr1[i]);
}

const arr1 = [1, 3, 9, 5, 2];
const arr2 = [3, 2, 5, 7, 8];

solution(arr1, arr2);
console.timeEnd('time');
