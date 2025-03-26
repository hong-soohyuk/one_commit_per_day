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

const recursiveWithPrev = (prev, current) => {
    if (current == null) return prev;

    const currentNext = current.next;
    current.next = prev;
    return recursiveWithPrev(current, currentNext);
}

const recursive = (node) => {
    if (node == null || node.next == null) return node;
    const newHead = recursive(node.next);
    node.next.next = node;
    node.next = null;

    return newHead;
}

var reverseList = function (head) {
    // return recursiveWithPrev(null, head);

    /////////////

    // return recursive(head);

    /////////////

    // let prev = null;
    // let current = head;
    // while (current != null) {
    //     const currentNext = current.next;
    //     current.next = prev;
    //     prev = current;
    //     current = currentNext;
    // }
    // return prev;

    /////////////

    if (head == null || head.next == null) return head;

    let next = head.next;
    let newHead = head;
    newHead.next = null;

    while (next != null) {
        const originalNext = next.next;
        next.next = newHead;
        newHead = next;
        next = originalNext;
    }
    return newHead;
};
