function solution(t, p) {
    let answer = 0;
    for (let i = 0; i + p.length <= t.length; i++)
            if (parseInt(t.substring(i, i + p.length)) <= parseInt(p))
                answer++;
    return answer;
}
