function solution(str){
	str=str.replace(/[^0-9]/g, '');
	console.log(str)
	return Number(str);
}

let str="tge0a1h205er";
console.log(solution(str));
