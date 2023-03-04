/**
 * @param {number[]} nums
 * @return {number}
 */
var maxSubArray = function(nums) {
    let sub = nums[0];
    let max = nums[0];
    
    for(let i = 1; i < nums.length; i++){
        sub = Math.max(nums[i], sub + nums[i]);
        max = Math.max(sub, max);
    }
    
    return max
};

/*
	sub = 여태까지의 합, 현재 인덱스 중 큰 값.
		-> 인덱스 값이 음수일지라도, 여태 까지의 합이 크기 때문에 서브어레이에 누적시킬 수 있음, (그 다음 인덱스 값이 더 클 수 있기 때문)
		-> 인덱스 값이 더 크다면, 서브어레이를 지우고 제일 큰 인덱스 값 저장.

	max = 누적 최고 값과 sub 중 큰 값.
		-> 루프에서 max가 갱신되었을때 그 값이 최고 값을 가진 서브어레이 임을 보장할 수 있음.(음수가 차례대로 누적되어 sub가 갱신되어도 max는 최고 값을 기록함.)
 */
