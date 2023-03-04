function solution(str){
	str=str.toLowerCase().replace(/[^a-z]/g, '');
	return str.split('').reverse().join('') === str ? 'YES' : 'NO';
}

let str1="found7, time:a study; Yduts; emit, 7Dnuof";
let str2="found7, time: study; Yduts; emit, 7Dnuof";
console.log(solution(str1));
console.log(solution(str2));
