/**
 * @param {number[][]} isConnected
 * @return {number}
 */

/*
var findCircleNum = function (isConnected) {
    const cityNum = isConnected.length;
    const visited = Array.from({ length: cityNum }, () => false);
    let province = 0;
    for (let i = 0; i < cityNum; i++) {
        if (visited[i]) continue;

        province++;
        const queue = [i];
        visited[i] = true;
        while (queue.length > 0) {
            const current = queue.shift();
            for (let j = 0; j < cityNum; j++) {
                if (isConnected[current][j] && !visited[j]) {
                    queue.push(j);
                    visited[j] = true;
                }
            }
        }

    }
    return province;
};
 */
var findCircleNum = function (isConnected) {
    const cityNum = isConnected.length;
    const visited = Array.from({ length: cityNum }, () => false);
    let province = 0;

    const visitCity = (index) => {
        visited[index] = true;
        for (let i = 0; i < cityNum; i++) {
            if (isConnected[index][i] && !visited[i])
                visitCity(i);
        }
    }
    for (let i = 0; i < cityNum; i++) {
        if (!visited[i]) {
            province++;
            visitCity(i);
        }
    }
    return province;
};
/*
https://leetcode.com/problems/number-of-provinces/?envType=study-plan-v2&envId=leetcode-75
bfs queue로 푸는 법
*/
