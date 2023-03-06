let answer = []
let stacks;
let center = -1;
let _n;

function stack_pop(i) {

    if (stacks[i].length > 0)
    {
        answer.push(stacks[i].pop());
        return ;
    }
 
    answer.push(center);
    center = -1;
    let limit = (i % _n);
    while (stacks[++i % _n].length === 0)
        if (i % _n == limit)
            break;
    if (i % _n !== limit)
        center = stacks[i % _n].shift();
}

function process_queries(queries) {
    let i = queries[0];
    let value = queries[1];
    if (value === -1)
        stack_pop(i);
    else
    {
        if (center === -1)
            center = value;
        else
            stacks[i].push(value);
    }
};

function solution(n, queries) {
    stacks = Array.from(Array(n + 1), () => Array());
    _n = n + 1;
    queries.forEach(process_queries);
    return answer;
}
