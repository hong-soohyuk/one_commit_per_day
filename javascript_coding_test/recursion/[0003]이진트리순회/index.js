function solution(nb) {
	const max = nb;
	let answer = [];

	function preorder_traversal(n, answer) {
		if (n > max) return;
		else {
			answer.push(n);
			preorder_traversal(2 * n, answer);
			preorder_traversal(2 * n + 1, answer);
		}
	}

	function inorder_traversal(n, answer) {
		if (n > max) return;
		else {
			inorder_traversal(2 * n, answer);
			answer.push(n);
			inorder_traversal(2 * n + 1, answer);
		}
	}

	function postorder_traversal(n, answer) {
		if (n > max) return;
		else {
			postorder_traversal(2 * n, answer);
			postorder_traversal(2 * n + 1, answer);
			answer.push(n);
		}
	}

	preorder_traversal(1, answer);
	console.log(answer);
	answer = [];

	inorder_traversal(1, answer);
	console.log(answer);
	answer = [];

	postorder_traversal(1, answer);
	console.log(answer);
}
solution(7);
