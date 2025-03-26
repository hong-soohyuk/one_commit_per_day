/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} head
 * @return {number}
 */

const reverseList = (node) => {
    if (node == null || node.next == null) return node;

    const newHead = reverseList(node.next);
    node.next.next = node;
    node.next = null;

    return newHead;
}

var pairSum = function (head) {
    let length = 0;
    let current = head;
    while (current != null) {
        ++length;
        current = current.next;
    }
    const secondHalfIndex = length / 2;
    current = head;
    let index = 0;
    while (index < secondHalfIndex) {
        ++index;
        current = current.next;
    }
    const secondHalfReversedHead = reverseList(current);

    let max = 0;

    let firstHalfCurrent = head;
    let secondHalfCurrent = secondHalfReversedHead;
    index = 0;
    while (index < secondHalfIndex && firstHalfCurrent != null && secondHalfCurrent != null) {
        max = Math.max(max, firstHalfCurrent.val + secondHalfCurrent.val);
        firstHalfCurrent = firstHalfCurrent.next;
        secondHalfCurrent = secondHalfCurrent.next;
        ++index;
    }
    return max;
};
