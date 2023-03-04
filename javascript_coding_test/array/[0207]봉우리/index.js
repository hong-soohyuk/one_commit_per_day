function solution(n, arr){
	let ans = 0;
	arr.unshift(Array.from({length: n}, () => 0));
	arr.push(Array.from({length: n}, () => 0));

	for(const val of arr){
		val.unshift(0);
		val.push(0);
	}
	
	for(let i = 1; i < arr.length - 1; i++){
		for(let j = 1; j < arr.length - 1; j++){
			if(arr[i][j] > (Math.max(arr[i-1][j], arr[i][j-1], arr[i+1][j], arr[i][j+1])))
				ans++;
		}
	}

  return ans;
}

let arr=[
	[5, 3, 7, 2, 3], 
	[4, 7, 1, 6, 1],
	[7, 2, 5, 3, 4],
	[4, 3, 6, 4, 1],
	[8, 7, 3, 5, 2]];
console.log(solution(arr.length, arr));
