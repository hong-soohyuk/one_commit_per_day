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
var deleteDuplicates = function (head) {
	let current = head;
	let skipped;
	while (current) {
		skipped = current;
		while (skipped != null && current.val === skipped.val)
			skipped = skipped.next;
		if (skipped == null) current.next = null;
		else current.next = skipped;

		current = current.next;
	}
	return head;
};
/*
https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 */
