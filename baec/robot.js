const BLANK = 0;
const WALL = 1;
const CLEANED = 2;

const fs = require('fs');
const input = fs.readFileSync('input.txt', 'utf8').trim().split('\n');

class Robot {
  constructor(r, c, d, map) {
    this.r = r;
    this.c = c;
    this.d = d;
    this.map = map;
    this.count = 0;
    this.N = map.length;
    this.M = map[0].length;
  }

  clean() {
    if (this.map[this.r][this.c] === BLANK) {
      this.map[this.r][this.c] = CLEANED;
      this.count++;
    }
  }

  rotate() {
    this.d--;
    if (this.d < 0) this.d = 3;
  }

  forwardable() {
    if (this.d === 0) return this.r > 0 && this.map[this.r - 1][this.c] === BLANK;
    if (this.d === 1) return this.c < this.M - 1 && this.map[this.r][this.c + 1] === BLANK;
    if (this.d === 2) return this.r < this.N - 1 && this.map[this.r + 1][this.c] === BLANK;
    if (this.d === 3) return this.c > 0 && this.map[this.r][this.c - 1] === BLANK;
  }

  forward() {
    if (this.d === 0) this.r--;
    if (this.d === 1) this.c++;
    if (this.d === 2) this.r++;
    if (this.d === 3) this.c--;
  }

  backwardable() {
    if (this.d === 0) return this.r < this.N - 1 && this.map[this.r + 1][this.c] !== WALL;
    if (this.d === 1) return this.c > 0 && this.map[this.r][this.c - 1] !== WALL;
    if (this.d === 2) return this.r > 0 && this.map[this.r - 1][this.c] !== WALL;
    if (this.d === 3) return this.c < this.M - 1 && this.map[this.r][this.c + 1] !== WALL;
  }

  backward() {
    if (this.d === 0) this.r++;
    if (this.d === 1) this.c--;
    if (this.d === 2) this.r--;
    if (this.d === 3) this.c++;
  }

  findCleanable() {
    if (this.r < this.N - 1 && this.map[this.r + 1][this.c] === BLANK) return true;
    if (this.c < this.M - 1 && this.map[this.r][this.c + 1] === BLANK) return true;
    if (this.r > 0 && this.map[this.r - 1][this.c] === BLANK) return true;
    if (this.c > 0 && this.map[this.r][this.c - 1] === BLANK) return true;
    return false;
  }

  startCleaning() {
    this.clean();
    
    let rotateCount = 0;
    while (rotateCount < 4) {
      this.rotate();
      if (this.forwardable()) {
        this.forward();
        this.startCleaning();
        return;
      }
      rotateCount++;
    }
    
    if (this.backwardable()) {
      this.backward();
      this.startCleaning();
    }
  }

  getCleanCount() {
    return this.count;
  }
}

function main() {
  let [N, M] = input[0].split(' ').map(Number);
  let [r, c, d] = input[1].split(' ').map(Number);

  const map = [];
  for (let i = 0; i < N; i++) {
    map.push(input.slice(2)[i].split(' ').map(Number));
  }

  const robot = new Robot(r, c, d, map);
  robot.startCleaning();

  console.log(robot.getCleanCount());
}

main();


/*
11 10
7 4 0
1 1 1 1 1 1 1 1 1 1
1 0 0 0 0 0 0 0 0 1
1 0 0 0 1 1 1 1 0 1
1 0 0 1 1 0 0 0 0 1
1 0 1 1 0 0 0 0 0 1
1 0 0 0 0 0 0 0 0 1
1 0 0 0 0 0 0 1 0 1
1 0 0 0 0 0 1 1 0 1
1 0 0 0 0 0 1 1 0 1
1 0 0 0 0 0 0 0 0 1
1 1 1 1 1 1 1 1 1 1
*/

/*
3 3
1 1 0
1 1 1
1 0 1
1 1 1
*/
