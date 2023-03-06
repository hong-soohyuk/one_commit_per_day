let answer = []
let queues;
let front = NaN;
let p;
let _n;

function queue_pop() {
    let i = p % _n;
    
    answer.push(front);
    front = NaN;

    if (queues[i].length > 0)
    {
        front = queues[i].shift();
        p = (i + 1) % _n;
        return ;
    }

    let limit = i;

    p = (i + 1) % _n;
    i = p % _n;

    while (queues[i].length === 0)
    {
        if (limit === i)
            break;
        p = (i + 1) % _n;
        i = p % _n;
    }
    if (limit === i)
        return ;
    front = queues[i].shift();
    p = (i + 1) % _n;
}

function queue_push(query) {
    let i = query[0];
    let value = query[1];
    if (i === -1)
        queue_pop();
    else
    {
        if (isNaN(front))
            front = value;
        else
        queues[i].push(value);
    }
}

function solution(n, queries) {
    queues = Array.from(Array(n), () => Array());
    _n = n;
    p = 0;
    queries.forEach(queue_push);
    return answer;
}
