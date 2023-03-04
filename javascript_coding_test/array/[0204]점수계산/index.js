function solution(N, A, B){
  let answer=[];
  
	for(let i = 0; i < N; i++){
		if(A[i] - B[i] === 1) answer[i] = 'A';
		else if(A[i] - B[i] === 2) answer[i] = 'B';
		else if(A[i] - B[i] === 0) answer[i] = 'D';
		else if(A[i] - B[i] === -1) answer[i] = 'B';
		else if(A[i] - B[i] === -2) answer[i] = 'A';
	}

  return answer;
}

const A = [2, 3, 3, 1, 3];
const B = [1, 1, 2, 2, 3];
console.log(solution(5, A, B));
