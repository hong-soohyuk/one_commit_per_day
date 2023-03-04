function solution(n, grades){
	let answer = Array.from({length: n}, () => 1);

	for(let i = 0; i < n; i++){
		for(let j = 0; j < n; j++){
			if(grades[i] < grades[j]) answer[i]++;
		}
	}
  return answer;
}

const grades = [87, 89, 92, 100, 76];
console.log(solution(grades.length, grades));

