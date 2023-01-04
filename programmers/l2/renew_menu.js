const make_course = (map, order) => {
    let strArr = order.split("");
    let result = [''];
    for (let i = 0; i < strArr.length; i++) {
        let len = result.length;
        for (let x = 0; x < len; x++)
            result.push(result[x] + strArr[i]);
    }
    result.filter(v => v.length != 0).forEach((val) => {
        val = val.split("").sort().join("");
        map.has(val) ? map.set(val, map.get(val) + 1) : map.set(val, 1);
    });
}

function solution(orders, course) {
    let map = new Map();
    let answer = [];
    for (let order of orders)
        make_course(map, order);

    for (let num of course) {
        if (map.size == 0)
            break;
        let only_num = new Map([...map].filter(([key, value]) => key.length == num));
        let course_menu =[...only_num.entries()].sort((a, b) => b[1] - a[1]);
        let max = Math.max(...only_num.values());
        course_menu = course_menu.filter((v) => v[1] == max && v[1] > 1);
        course_menu.forEach(v => answer.push(v[0]));
    }
    return answer.sort();
}

