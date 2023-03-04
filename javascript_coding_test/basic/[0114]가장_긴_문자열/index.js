function solution(s){  
	let answer="", max=Number.MIN_SAFE_INTEGER;
	let index;
	for(const i in s){
		if(s[i].length > max){
			max = s[i].length;
			index=i;
		}
	}
	return s[index];
}
let str=["teacher", "time", "student", "beautiful", "good"];
console.log(solution(str));
