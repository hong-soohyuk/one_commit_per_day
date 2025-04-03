/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @return {number[]}
 */


var rightSideView = function (root) {
    if (!root) return [];
    const depthMap = new Map();
    const queue = [{ node: root, depth: 0 }];

    while (queue.length > 0) {
        const { node, depth } = queue.shift();
        depthMap.set(depth, node.val);

        if (node.left) queue.push({ node: node.left, depth: depth + 1 })
        if (node.right) queue.push({ node: node.right, depth: depth + 1 })
    }
    return [...depthMap.values()];
};

/*
https://leetcode.com/problems/binary-tree-right-side-view/?envType=study-plan-v2&envId=leetcode-75
how to traverse tree with BFS
*/
