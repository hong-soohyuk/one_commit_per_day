/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root1
 * @param {TreeNode} root2
 * @return {boolean}
 */

const dfs = (node, array) => {
    if (node == null) return;
    if (node.left == null && node.right == null) array.push(node.val);
    dfs(node.left, array);
    dfs(node.right, array);
}

var leafSimilar = function (root1, root2) {
    const leaves1 = [], leaves2 = [];
    dfs(root1, leaves1);
    dfs(root2, leaves2);
    if (leaves1.length !== leaves2.length) return false;
    for (let i = 0; i < leaves1.length; i++) {
        if (leaves1[i] == null || leaves2[i] == null) return false;
        if (leaves1[i] !== leaves2[i]) return false;
    }
    return true;
};
