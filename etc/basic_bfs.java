package solution;

import java.util.*;

class UserSolution {
	Queue<int[]>	queue;
	int[][]			bfs_map;
	int[][]			visited_map;
	int[]			move_x;
	int[]			move_y;
    void bfs_init(int map_size, int map[][])
    {
    	queue = new LinkedList<>();
    	visited_map = new int[map_size][map_size];
    	bfs_map = map;
    	move_x = new int[] {0, 1, 0, -1};
    	move_y = new int[] {1, 0, -1, 0};
    }

    int bfs(int x1, int y1, int x2, int y2)
    {
    	for (int i = 0;  i < bfs_map.length; i++)
    		for (int j = 0; j < bfs_map.length; j++)
    			visited_map[i][j] = bfs_map[i][j];
    	queue.add(new int[] {y1-1, x1-1});
    	while (!queue.isEmpty())
    	{
    		int[] here = queue.poll();
    		if (here[0] == y2-1 && here[1] == x2-1)
    			return visited_map[y2-1][x2-1];
    		for (int i = 0; i < 4; i++)
    		{
    			int new_x = here[1] + move_x[i];
    			int new_y = here[0] + move_y[i];
    			if (new_x != x1-1 && new_y!=y1&& new_x >= 0 && new_x < bfs_map.length &&
    					new_y >= 0 && new_y < bfs_map.length &&
    							visited_map[new_y][new_x] == 0)
    			{    				
    				visited_map[new_y][new_x] = visited_map[here[1]][here[0]] + 1;
    				System.out.println("x: "+new_x+", y: "+new_y+", val: "+visited_map[new_x][new_y]);
    				queue.add(new int[] {new_y, new_x});
    			}
    		}
    	}
    	return -1;
    }	
}

