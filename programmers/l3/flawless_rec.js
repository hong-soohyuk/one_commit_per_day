const euclidian = (a, b) => {
    let r;
    while (b != 0) {
        r = a % b;
        a = b;
        b = r;
    }
    return a;
}

function solution(w, h) {
    let answer = w * h;
    let big_common = euclidian(w, h);
    w /= big_common;
    h /= big_common;
    return answer - (w + h - 1) * big_common;
}

