function solution(arr1, arr2) {
    var answer = [];
    let tempArr = [];
    let tempNum = 0;
    for (let i = 0; i < arr1.length; i++) {
        for (let j = 0; j < arr2[0].length; j++) {
            for (let k = 0; k < arr2.length; k++) 
                tempNum += (arr1[i][k] * arr2[k][j]);
            tempArr.push(tempNum);
            tempNum = 0;
        }
        answer.push(tempArr);
        tempArr = [];
    }
    return answer;
}
