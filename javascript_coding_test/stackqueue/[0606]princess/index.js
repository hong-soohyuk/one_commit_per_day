// time: 6.606ms
console.time('time');
function solution(n, k) {
	let queue = [];
	for (let i = 1; i <= n; i++) queue.push(i);

	while (queue.length > 1) {
		let counter = 0;
		while (counter < k) {
			counter++;
			if (counter === k) queue.shift();
			else queue.push(queue.shift());
		}
	}

	return queue;
}
console.log(solution(8, 3));
console.timeEnd('time');
