function solution(str){
	str = str.toLowerCase();
	s = str.split('').reverse().join('');
	return str === s;
}

console.log(solution('gooG'));
console.log(solution('nope'));
