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
 * @return {number}
 */
var maxLevelSum = function (root) {
    if (!root) return 0;
    const queue = [root];
    let depth = 1;
    let max = root.val;
    let maxDepth = 1;

    while (queue.length > 0) {
        const length = queue.length;
        let sum = 0;
        for (let i = 0; i < length; i++) {
            const node = queue.shift();
            sum += node.val;
            if (node.left) queue.push(node.left)
            if (node.right) queue.push(node.right)
        }
        if (sum > max) {
            max = sum;
            maxDepth = depth;
        }
        depth++;
    }
    return maxDepth;
};

/*
the nodes that are in the queue during a single iteration of the while loop are at the same level in the tree.

https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/description/?envType=study-plan-v2&envId=leetcode-75
*/
