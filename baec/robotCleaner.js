const BLANK = 0;
const WALL = 1;
const CLEANED = 2;

const fs = require('fs');
const input = fs.readFileSync('dev/stdin', 'utf8').trim().split('\n');

let [N, M] = input[0].split(' ').map(Number);

let [r, c, d] = input[1].split(' ').map(Number);

let count = 0;

const cleaned = [];

for (let i = 0; i < N; i++)
    cleaned.push(input.slice(2)[i].split(' ').map(Number))

const rotate = () => {
    d--;
    if (d < 0) d = 3;
}

const clean = (x, y) => {
    cleaned[x][y] = CLEANED;
    count++;
}

const forwardable = () => {
    if (d === 0) return r > 0 && cleaned[r - 1][c] === BLANK;
    if (d === 1) return c < M - 1 && cleaned[r][c + 1] === BLANK;
    if (d === 2) return r < N - 1 && cleaned[r + 1][c] === BLANK;
    if (d === 3) return c > 0 && cleaned[r][c - 1] === BLANK;
}

const forward = () => {
    if (d === 0) r--;
    if (d === 1) c++;
    if (d === 2) r++;
    if (d === 3) c--;
}

const backwardable = () => {
    if (d === 0) return r < N - 1 && cleaned[r + 1][c] !== WALL;
    if (d === 1) return c > 0 && cleaned[r][c - 1] !== WALL;
    if (d === 2) return r > 0 && cleaned[r - 1][c] !== WALL;
    if (d === 3) return c < M - 1 && cleaned[r][c + 1] !== WALL;
}

const backward = () => {
    if (d === 0) r++;
    if (d === 1) c--;
    if (d === 2) r--;
    if (d === 3) c++;
}

const robotClean = () => {
    if (cleaned[r][c] === BLANK) clean(r, c);
    
    let rotateCount = 0;
    while (rotateCount < 4) {
        rotate();
        if (forwardable()) {
            forward();
            robotClean();
            return;
        }
        rotateCount++;
    }
    
    if (backwardable()) {
        backward();
        robotClean();
    }
}

robotClean();

console.log(count);
