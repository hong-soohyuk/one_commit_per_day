function solution(n, m, arr){
	const answer = new Set();

	for(let k = 0; k < m; k++){
		test = arr[k];
		for(let i = 0; i < n; i++){
			for(let j = i+1; j < n; j++){
				if(answer.has(`${test[j]}${test[i]}`))
					answer.delete(`${test[j]}${test[i]}`);
				if(k === 0)
					answer.add(`${test[i]}${test[j]}`); 	
			}
		}
	}
  return answer;
}

const tests = [
	[3,4,1,2],
	[4,3,2,1],
	[3,1,4,2]
]
console.log(solution(tests[0].length, tests.length, tests));
