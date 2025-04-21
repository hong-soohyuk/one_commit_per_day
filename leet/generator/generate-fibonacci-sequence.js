/**
 * @return {Generator<number>}
 */
var fibGenerator = function* () {
    let x = 0, y = 1;
    while (true) {
        yield x;
        [x, y] = [y, x + y];
    }
};

/**
 * const gen = fibGenerator();
 * gen.next().value; // 0
 * gen.next().value; // 1
 */

/*
https://leetcode.com/problems/generate-fibonacci-sequence/description/

javascript Generator, yield
	Generator
- Generator는 내부상태 관리를 할 수 있고, 비동기처리를 할 수 있는 함수이다.
- Generator 함수는 Generator 를 반환한다.
- next() 메소드는 {value, done: boolean}
- yield 를 기준으로 코드가 잘려서 실행된다.

return 을 통해 iteration 을 종료시킬 수 있음.
return 되었을때 finally 블럭 안에 있으면 종료되지 않고, 남은 yield 를 실행한다.
	finally를 빠져나오면 남은 yield 를 실행하지 않고 return 된다.

throw 가 호출되면, catch 블럭에 인자가 전달된다.
catch에서 yield 하면 다음 next 메소드 호출까지 끝나지 않는다.

yield* 를 통해 다른 generator 를 실행할 수 있다.

Generator는 반복 가능한 객체 Iterable 이다.

for .. of, spread 연산자도 사용 가능하다.

 */
