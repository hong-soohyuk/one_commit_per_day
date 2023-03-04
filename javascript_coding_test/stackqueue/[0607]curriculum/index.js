// time: 6.818ms
console.time('time');
function solution(time_table, required) {
	const t = time_table.split('');
	const r = required.split('');

	console.log(r);
	for (const lecture of t) if (lecture === r[0]) r.shift();
	console.log(r);
	return r.length === 0 ? 'YES' : 'NO';
}
const time_table = 'CBDAGE';
const required = 'CBA';

console.log(solution(time_table, required));
console.timeEnd('time');
