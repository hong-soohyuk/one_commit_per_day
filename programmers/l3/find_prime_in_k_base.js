const isPrime = (n) => {
    if (isNaN(n)) return false;
    if (n === 1 || n === 0 || n < 0) return false;
    for (let i = 2; i * i <= n; i++)
        if (n % i === 0) return false;
    return true;
}

function solution(n, k) {
    let answer = 0;
    let divide_array = [];
    while (n !== 0)
    {
        divide_array.push(n % k);
        n = Math.floor(n / k);
    }
    let not_zeroes = divide_array.reverse().join('').split('0');
    for (let num of not_zeroes)
        if(isPrime(parseInt(num)))
            ++answer;
    return answer;
}
