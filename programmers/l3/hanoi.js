let answer = [];
const hanoi = (n, start, by, to) => 
{
    if (n == 1)
    {
        answer.push([start, to]);
        return ;
    }
    hanoi(n - 1, start, to, by);
    answer.push([start, to]);
    hanoi(n - 1, by, start, to)
}

function solution(n) {
    hanoi(n, 1, 2, 3);
    return answer;
}
