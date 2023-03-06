function solution(prices) {
    // let answer = 0;
    // for (let i = 0; i < prices.length; i++)
    //     for (let j = i + 1; j < prices.length; j++)
    //         answer = Math.max(answer, prices[j] - prices[i]);
    // return answer;
    let answer = 0;
    let left = 0;
    let right = 0;
    while (++right < prices.length)
    {
        if (prices[left] < prices[right])
            answer = Math.max(answer, prices[right] - prices[left])
        else
            left = right;
    }
    return (answer);
}
