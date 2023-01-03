const make_powerset = (str, max_depth) => {
    let flags = new Array({length: order.length}, false);
    const get_power_set = (depth, map, max_dep) => {
        if (depth === max_dep) {
            map.set()
        } else {
            
        }
    }
    dfs(0, map, order.length);
}

const make_course(map, size, orders) => {

    
    for (let order of orders){
        if (order.length < size)
            continue ;
        else if (order.length == size)
            //map.set(order, ++)
        else
            
    }
}

function solution(orders, course) {
    const map = new Map();
    course.forEach((v) => make_course(map, v, orders))
    map = new Map([...map.entries()].sort()); // key sort
    return (Array.from(map.keys()));
}
