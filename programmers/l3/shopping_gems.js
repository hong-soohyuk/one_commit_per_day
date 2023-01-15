const get_types = (array) => {
    const set = new Set;
    for (let element of array)
        set.add(element);
    return (set.size);
}

function solution(gems) {
    let     index = 0;
    let     min = 200000;
    const   map = new Map();
    let     answer = [];
    const   types = get_types(gems);

    while (index < gems.length)
    {
        while (map.size < types && index < gems.length)
        {
            map.set(gems[index], index);
            index++;
        }
        if (map.size == types)
        {
            let values = [...map.entries()].sort((a, b) => a[1] - b[1]);
            let start = values[0][1];
            let end = values[map.size - 1][1];
            if (end - start < min)
            {
                answer.push([start + 1, end + 1]);
                min = end - start;
            }
            index = start + 1;
            map.clear();
        }        
    }
    return (answer.reverse()[0]);
}
