function solution(arr){
  let answer = 1;
	let max = arr[0];

	for(const height of arr){
		if(height > max){
			answer++;
			max = height;
		}
	}
           
  return answer;
}

let arr=[130, 135, 148, 140, 145, 150, 150, 153];
console.log(solution(arr));
