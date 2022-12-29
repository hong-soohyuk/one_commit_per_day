function solution(info, edges) {
    let answer = 0;
    let connected_path = Array.from({length: info.length}, () => []);
    for (let edge of edges)
        connected_path[edge[0]].push(edge[1]);
    
    const dfs = (c_node, sheep, wolf, possible_path) => {
        let new_possible_path = [...possible_path];
        info[c_node] ? ++wolf : ++sheep;
        if (sheep <= wolf)
            return ;
        answer = Math.max(answer, sheep);
        new_possible_path.push(...connected_path[c_node]);
        new_possible_path.splice(new_possible_path.indexOf(c_node), 1);
        for (let newRoute of new_possible_path)
            dfs(newRoute, sheep, wolf, new_possible_path);
    }
    dfs(0, 0, 0, [0]);
    return answer;
}
