function solution(s){
	let answer=0;
	for(const letter of s){
		let upper = letter.toUpperCase();

		if(upper === letter)
			answer++;
	}
	return answer;
}

let str="KoreaTimeGood";
console.log(solution(str));
