/**
 * @param {number[]} g
 * @param {number[]} s
 * @return {number}
 */
var findContentChildren = function(g, s) {
    const sortedChildren = g.sort((a, b) => a-b);
    const sortedCookies = s.sort((a, b) => a-b);

    let i = 0, j = 0;
    while (i < g.length && j < s.length) {
        if (sortedChildren[i] <= sortedCookies[j]) {

            i++;
        }
        j++;
    }
    return i;
};

//https://leetcode.com/problems/assign-cookies/
