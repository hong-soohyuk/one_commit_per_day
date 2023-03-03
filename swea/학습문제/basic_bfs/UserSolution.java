class Queue<T>
{
	int	size;
	class Node<T>
	{
		T		value;
		Node<T>	next;
		Node(T value)
		{
			this.value = value;
			this.next = null;
		}
	}
	Node<T> head;
	Node<T> tail;
	
	public void clear()
	{
		Node<T> current;
		Node<T>	next;
		current = head;
		while (current != null)
		{
			next = current.next;
			current.next = null;
			current.value = null;
			current = next;
		}
		size = 0;
		head = null;
		tail = null;
	}
	
	public void	enqueue(T val)
	{
		Node<T> newNode = new Node<T>(val);
		 
		if(size == 0)
			head = newNode;
		else
			tail.next = newNode;
		tail = newNode;
		size++;
	}
	
	public T	dequeue()
	{
		if(size == 0)
			return null;
		 
		Node<T>	return_val = head;

		head = return_val.next;
		size--;
		if (head == null)
			tail = null;
		return (return_val.value);
	}
	
	public boolean empty()
	{
		return (this.size == 0);
	}
	
	Queue()
	{
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
}

class UserSolution {
	Queue<int[]>	queue;
	int[][]			bfs_map;
	int[][]			visited_map;
	int[]			move_x;
	int[]			move_y;
    void bfs_init(int map_size, int map[][])
    {
    	queue = new Queue<>();
    	visited_map = new int[map_size][map_size];
    	bfs_map = map;
    	move_x = new int[] {0, 1, 0, -1};
    	move_y = new int[] {1, 0, -1, 0};
    }

    int bfs(int y1, int x1, int y2, int x2)
    {
    	for (int i = 0;  i < bfs_map.length; i++)
    		for (int j = 0; j < bfs_map.length; j++)
    			visited_map[i][j] = bfs_map[i][j];
    	queue.clear();
    	queue.enqueue(new int[] {x1-1, y1-1});
    	while (!queue.empty())
    	{
    		int[] here = queue.dequeue();
    		if (here[0] == x2-1 && here[1] == y2-1)
    			return visited_map[x2-1][y2-1];
    		for (int i = 0; i < 4; i++)
    		{
    			int new_x = here[0] + move_x[i];
    			int new_y = here[1] + move_y[i];
    			if (!(new_x == x1-1 && new_y == y1-1) &&
    					new_x >= 0 && new_x < bfs_map.length && 
    					new_y >= 0 && new_y < bfs_map.length &&
    							visited_map[new_x][new_y] == 0)
    			{
    				visited_map[new_x][new_y] = visited_map[here[0]][here[1]] + 1;
    				queue.enqueue(new int[] {new_x, new_y});
    			}
    		}
    	}
    	return -1;
    }	
}

