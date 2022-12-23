const distancing = (place, row, col) => {
    let go_x = [-1, 0, 1, 0];
    let go_y = [0, -1, 0, 1];
    for (let i = 0; i < 4; i++)
    {
        let new_x = row + go_x[i];
        let new_y = col + go_y[i];
        if (new_x < 0 || new_x > 4 || new_y < 0 || new_y > 4) continue ;
        if (place[new_x][new_y] == 'X') continue ;
        if (place[new_x][new_y] == 'P') return false;
        
        for (let j = 0; j < 4; j++)
        {
            let newnew_x = new_x + go_x[j];
            let newnew_y = new_y + go_y[j];
            if (newnew_x < 0 || newnew_x > 4 || newnew_y < 0 || newnew_y > 4)
                continue;
            if (newnew_x === row && newnew_y === col)
                continue ;
            else if (place[newnew_x][newnew_y] == 'P')
                return false;
        }
    }
    return true;
}

const find_p = (place) => {
    for (let i = 0; i < 5; i++)
            for (let j = 0; j < 5; j++)
                    if (place[i][j] === 'P' && !distancing(place, i, j)) return (0);
    return (1);
}

function solution(places) {
    return places.map(find_p);
}
