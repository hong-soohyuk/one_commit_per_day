const update = (array, ti, total, cap) => {
    let i = ti;
    let truck = 0;
    if (total == 0)
        return (0);
    while (i > -1 && truck !== cap) {
        if (array[i] == 0) {
            --i;
            continue ;
        }
        truck += array[i];
        if (truck > cap) {
            array[i] = (truck - cap);
            truck = cap; 
        }
        else
            array[i] = 0;
        --i;
    }
    return (total - truck);
}

function solution(cap, n, del, pick) {
    let answer = 0;
    let dTotal = del.reduce((acc, e) => acc + e, 0);
    let pTotal = pick.reduce((acc, e) => acc + e, 0);
    let ti = n - 1;
    while (dTotal !== 0 || pTotal !== 0)
    {
        while (del[ti] === 0 && pick[ti] === 0)
            --ti;
        if (dTotal != 0) 
            dTotal = update(del, ti, dTotal, cap);
        if (pTotal !== 0) 
            pTotal = update(pick, ti, pTotal, cap);
        answer += ((ti + 1) << 1);
    }

    return answer;
}
//
function solution(cap, n, deliveries, pickups) {
    let answer = 0;
    
    let d = 0;
    let p = 0;
    for (let i = n - 1; i > -1; i--) {
        d -= deliveries[i];
        p -= pickups[i];
        
        let count = 0;
        while (d < 0 || p < 0) {
            d += cap;
            p += cap;
            count++;
        }
        answer += (i + 1) * 2 * count;
    }
    return answer;
}
