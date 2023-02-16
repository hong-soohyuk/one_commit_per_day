const dfs = (bin) =>
{ 
    let return_val = true;

    let middle = (bin.length - 1) / 2;
    let left = bin.substring(0, middle);
    let right = bin.substring(middle + 1, bin.length);
    if (bin.charAt(middle) == '0'
        && ((left.charAt((left.length - 1) / 2)) == '1' || right.charAt((right.length - 1) / 2) == '1'))
        return (false);
    if (bin.length >= 3)
        return (dfs(left) && dfs(right));
    return (return_val);
}

function solution(numbers) {
    return numbers.map((number) => {
        let bin = number.toString(2);
        let j = 0;
        while (Math.pow(2, j) - 1 < bin.length)
            j++;
        while (Math.pow(2, j) - 1 != bin.length)
            bin = '0' + bin;
        return (dfs(bin) ? 1 : 0);
    });
}
