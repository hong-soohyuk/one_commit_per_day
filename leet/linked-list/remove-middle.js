/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} head
 * @return {ListNode}
 */
var deleteMiddle = function (head) {
	if (head == null || head.next == null) return null;
	let slow = head,
		fast = head,
		prev = null;

	while (fast !== null && fast.next !== null) {
		prev = slow;
		slow = slow.next;
		fast = fast.next.next;
	}
	prev.next = slow.next;
	return head;
};

/*
https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/?envType=study-plan-v2&envId=leetcode-75

1. how to define the middle index while looping linked list.
2. setting prev node null at the start of the logic to remove current node in a singly linked list.

 */
