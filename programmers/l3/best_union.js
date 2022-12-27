function solution(n, s) {
    if (s < n) return [-1];
    let floor = Math.floor(s / n);
    let answer = Array(n).fill(floor);
    let remain = s % n
    while (remain > 0) {
        answer[remain]++;
        remain--;
    }
    return answer.sort();
}
