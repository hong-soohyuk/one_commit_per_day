var maxProfit = function(prices) {
	let profit = 0;
	let minBuy = Number.MAX_SAFE_INTEGER;
	/*for(let i = 0; i < prices.length; i++){
		for(let j = i + 1; j < prices.length; j++){
			if(prices[j] - prices[i] > profit)
				profit = prices[j] - prices[i];
		}
	}
	*/
	for(let day = 0; day < prices.length; day++){
		// 구매값이 낮을때만 구매.
		if(prices[day] < minBuy) minBuy = prices[day];
		
		// 최소변수로부터 최대 이득을 얻을때 profit에 할당
		else if(prices[day] - minBuy > profit) profit = prices[day] - minBuy;
	}

  return profit;
};
const prices = [7,1,5,3,6,4];
console.log(maxProfit(prices));

/*
nested for loop exceed time limit.

구매값이 가장 낮을때만 구매변수에 할당, forLoop변수 - 최소구매값 > profit 일때 profit에 할당.
 */
