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

const dfs = (current, depth) => {
    if (current == null) return depth;
    if (current.left == null && current.right == null) return depth + 1;

    return Math.max(dfs(current.left, depth + 1), dfs(current.right, depth + 1));
}

var maxDepth = function (root) {
    return dfs(root, 0);
};
/*
https://leetcode.com/problems/maximum-depth-of-binary-tree/?envType=study-plan-v2&envId=leetcode-75
*/
