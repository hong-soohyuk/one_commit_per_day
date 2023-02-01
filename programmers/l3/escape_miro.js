//d l r u order
let answer = 'impossible';

const abs = (x) => {return (x < 0 ? -x : x);}

// const find_straight = (x, y, r, c, k, route) => {
//     let vertical = x - r;
//     let horizontal = y - c;
//     console.log("route", route);
//     if (vertical < 0)
//         while (vertical < 0) {
//             route += 'd';
//             vertical++;
//         }
            
//     if (horizontal < 0)
//         while (horizontal < 0) {
//             route += 'l'
//             horizontal++;
//         }
//     if (horizontal > 0)
//         while (horizontal > 0) {
//             route += 'r'
//             --horizontal
//         }
            
//     if (vertical > 0)
//         while (vertical >= 0) {
//             route += 'u'
//             --vertical;
//         }
//     answer = route;
//     console.log("after, route", route);
// }

const fill_route = (n, m, x, y, k, route) =>
{
    console.log("before", route, n, m, x, y);
    if (x < n)
        for (let i = 0; i < k / 2; i++)
            route += 'du';
    else if (y > 1)
        for (let i = 0; i < k / 2; i++)
            route += 'lr';
    else if (y < m)
        for (let i = 0; i < k / 2; i++)
            route += 'rl';
    else
        for (let i = 0; i < k / 2; i++)
            route += 'ud';
    answer = route;
}

const find = (n, m, x, y, r, c, k, route) =>
{
    const mx = [1, 0, 0, -1];
    const my = [0, -1, 1, 0];
    if (abs(x-r) + abs(y-c) > k || k % 2 !== (abs(x-r) + abs(y-c)) % 2)
        return ;
    if (k === 0)
    {
        if (x === r && y === c)
            answer = route;
        return ;
    }
    // if (k % 2 === 0 && x === r && y === c)
    // {
    //     fill_route(n, m, x, y, k, route);
    //     return ;
    // }
    // if (abs(x-r) + abs(y-c) === k)
    // {
    //     find_straight(x, y, r, c, k, route);
    //     return ;
    // }
   
    if (x < 1 || y < 1 || x > n || y > m)
        return ;
    if (answer === 'impossible')
    {
        for (let i = 0; i < 4; i++)
        {
            let move;
            if (i === 0) move = 'd'
            else if (i === 1) move = 'l'
            else if (i === 2) move = 'r'
            else if (i === 3) move = 'u'
            find(n, m, x + mx[i], y + my[i], r, c, k - 1, route + move);
        }
    }
}
//n m 미로 길이, x y 출발좌표 r c 탈출 좌표, k 이동해야하는 거리
function solution(n, m, x, y, r, c, k)
{
    find(n, m, x, y, r, c, k, "");
    return answer;
}
