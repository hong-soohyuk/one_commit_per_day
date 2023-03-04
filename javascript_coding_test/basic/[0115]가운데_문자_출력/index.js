function solution(s){  
	console.log('input: ', s);
	let answer;
	const middle_index = Math.floor(s.length / 2);
	if(s.length % 2 === 0)
		answer = s.substring(middle_index - 1, middle_index + 1);
	else
		answer = s.substring(middle_index, middle_index + 1);
	//	answer = s.charAt(middle_index);
	return answer;
}
console.log(solution("study"));
console.log(solution("good"));
