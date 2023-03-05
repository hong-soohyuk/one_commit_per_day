function solution(number) {
	if (number == 0) return;
	solution(number - 1);
	console.log(number);
}
solution(5);
