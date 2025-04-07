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
 * @param {number} key
 * @return {TreeNode}
 */
var deleteNode = function (root, key) {
    if (root == null) return null;

    if (root.val === key) {
        if (root.left == null && root.right == null) return null;
        if (root.left == null) return root.right;
        if (root.right == null) return root.left;
        if (root.left != null && root.right != null) {
            let pointer = root.right;
            while (pointer.left) pointer = pointer.left;
            pointer.left = root.left;
            return root.right;
        }
    }
    if (key < root.val) root.left = deleteNode(root.left, key);
    else root.right = deleteNode(root.right, key);
    return root;

};

/*
https://leetcode.com/problems/delete-node-in-a-bst/?envType=study-plan-v2&envId=leetcode-75

 */
