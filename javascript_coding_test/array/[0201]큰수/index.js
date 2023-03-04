function solution(arr){
  let answer = [];

	let larger = arr[0];
	answer.push(arr[0]);
	
	for(let i = 1; i < arr.length; i++){
		if(arr[i] > larger)
			answer.push(arr[i]);
		
		larger = arr[i];
	}

               
  return answer;
}
let arr=[7, 3, 9, 5, 6, 12];
console.log(solution(arr));
