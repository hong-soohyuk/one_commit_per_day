function canVisitAllRooms(rooms: number[][]): boolean {
    const visited = rooms.map(() => false);

    const visit = (roomNum: number) => {
        if (visited[roomNum] === true) return;
        visited[roomNum] = true;

        for (const next of rooms[roomNum])
            visit(next);
    }
    visit(0);
    return visited.every((x) => x === true);
};

/*
https://leetcode.com/problems/keys-and-rooms/submissions/1608490814/?envType=study-plan-v2&envId=leetcode-75
*/
