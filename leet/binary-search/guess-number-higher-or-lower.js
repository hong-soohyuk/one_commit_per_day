/** 
 * Forward declaration of guess API.
 * @param {number} num   your guess
 * @return 	     -1 if num is higher than the picked number
 *			      1 if num is lower than the picked number
 *               otherwise return 0
 * var guess = function(num) {}
 */

/**
 * @param {number} n
 * @return {number}
 */
var guessNumber = function (n) {
    // const divideandconquer = (left, right) => {
    //     if (left > right) return left;

    //     const mid = left + Math.floor((right - left) / 2);
    //     const guessed = guess(mid);
    //     if (guessed === 0) return mid;
    //     if (guessed === 1) return divideandconquer(mid + 1, right);
    //     return divideandconquer(left, mid - 1);
    // }

    // return divideandconquer(1, n);
    let left = 1; right = n;
    let mid;
    while (left <= right) {
        mid = left + Math.floor((right - left) / 2);
        const result = guess(mid);

        if (result === 0) return mid;
        if (result === 1) left = mid + 1;
        else right = mid - 1;
    }
    return mid;
};

/*
1. MIDDLE VALUE IS ALWAYS left + Math.floor((right - left) / 2);!!!!
2. mid + 1 or mid - 1 to narrow down the range
3. base condition of while is usually left <= right
*/
