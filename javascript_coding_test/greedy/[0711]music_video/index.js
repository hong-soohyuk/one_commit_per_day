// time: 5.603ms
console.time('time');
function song_count(songs, capacity) {
	let count = 1;
	let sum = 0;
	for (const song of songs) {
		if (sum + song > capacity) {
			count++;
			sum = song;
		} else sum += song;
	}
	return count;
}
function solution(m, songs) {
	let answer = 0;

	let left = Math.max(...songs);
	let right = songs.reduce((prev, curr) => prev + curr, 0);
	while (left <= right) {
		let mid = parseInt((left + right) / 2);
		if (song_count(songs, mid) <= m) {
			answer = mid;
			right = mid - 1;
		} else left = mid + 1;
	}
	return answer;
}
const arr = [1, 2, 3, 4, 5, 6, 7, 8, 9];
console.log(solution(3, arr));
console.timeEnd('time');
