function solution(s) {
    if (s == '') return '';
    return s.split(' ').map(v =>
        v !== '' ? v[0].toUpperCase() + v.slice(1).toLowerCase() : null
    ).join(' ');
}
