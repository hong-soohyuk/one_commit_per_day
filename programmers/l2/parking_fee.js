const minutify = (time) => {
    let hm = time.split(':');
    return parseInt(hm[0]) * 60 + parseInt(hm[1]);
}

const make_fee = (total_log, fees) => {
    return total_log <= fees[0] 
        ? fees[1] : fees[1] + Math.ceil((total_log - fees[0]) / fees[2]) * fees[3];
}

function solution(fees, records) {
    const map = new Map();
    records.forEach((val) => {
        const record = val.split(' ');
        const prev = map.get(record[1]);
        if (record[2] === 'IN')
            prev ? map.set(record[1], {...prev, log: minutify(record[0])})
            : map.set(record[1], {log: minutify(record[0]), total: 0});
        else //out
            map.set(record[1], {log: -1, total: prev.total + (minutify(record[0]) - prev.log)});
    });

    map.forEach((value, key, m) => {
        let total_log;
        if (value.log !== -1) 
            total_log = value.total + (minutify('23:59') - value.log);
        else
            total_log = value.total;
        m.set(key, {log: -1, total: make_fee(total_log, fees)});
    })
 
    return [...map.entries()].sort().map(value => value[1].total);
}
