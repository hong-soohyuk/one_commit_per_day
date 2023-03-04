// time: 6.676ms
console.time('time');
function solution(cache_size, process) {
	let cache = [];
	for (const p of process) {
		if (cache.includes(p)) insertion_sort(cache, p);
		else if (cache.length === cache_size) {
			cache.pop();
			cache.unshift(p);
		} else cache.unshift(p);
	}
	return cache;
}

function insertion_sort(cache, p) {
	for (let i = cache.indexOf(p); i > 0; i--) cache[i] = cache[i - 1];
	cache[0] = p;
}

const process = [1, 2, 3, 2, 6, 2, 3, 5, 7];
console.log(solution(5, process));
console.timeEnd('time');
