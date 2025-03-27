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
 * @param {number} val
 * @return {TreeNode}
 */
// var searchBST = function (root, val) {
//     if (root == null) return null;
//     if (root.val == val) return root;
//     let search = null;
//     if (root.left != null)
//         search = searchBST(root.left, val);
//     if (root.right != null && search == null)
//         search = searchBST(root.right, val);
//     return search;
// };

// var searchBST = function (root, val) {
//     const dfs = (node) => {
//         if (node == null) return null;
//         if (node.val === val) return node;
//         return dfs(node.left) || dfs(node.right);
//     }
//     return dfs(root);
// };

var searchBST = function (root, val) {
    let pointer = root;
    while (pointer != null) {
        if (pointer.val === val) return pointer;
        pointer = val < pointer.val ? pointer.left : pointer.right;
    }
    return null;
};

/*
https://leetcode.com/problems/search-in-a-binary-search-tree/?envType=study-plan-v2&envId=leetcode-75
1. THE PRINCIPLE OF BST!!!!!!
2. Iteration is more efficient than recursion
*/
