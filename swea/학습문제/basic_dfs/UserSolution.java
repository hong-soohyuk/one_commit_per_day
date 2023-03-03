class Stack<T>
{
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
	Node<T>	head;
	int		size;

	T	pop()
	{
		T	return_val;

		if (head == null)
			return null;
		return_val = head.value;
		head = head.next;
		--this.size;
		return (return_val);
	}
	
	void push(T value)
	{
		Node<T> new_node;

		if (this.size == 0)
			head = new Node<T>(value);
		else
		{
			new_node = new Node<T>(value);
			new_node.next = head;
			this.head = new_node;
		}
		++this.size;
	}
	boolean is_empty() {return (this.size == 0);}
	int		size() {return (this.size);}
	void	clear() {
		this.head = null;
		this.size = 0;
	}
	
	Stack()
	{
		this.head = null;
		this.size = 0;
	}
}

class	List
{
	int[]	list;
	int		size;
	List(int size)
	{
		this.size = 0;
		this.list = new int[size];
	}	
	int		get(int index) {return (this.list[index]);}
	void	add(int value) {this.list[size] = value; ++this.size;}
	int		size() {return(this.size);}
	boolean isempty() {return (this.size == 0);}
}

public class UserSolution
{
	int[]				childCnt;
	int					size;
	List[]				childList;
	int					return_val;
	int					current;
	Stack<Integer>		stack;
	public void dfs_init(int N, int[][] path)
	{
		childList = new List[100];
		return_val = -1;
		stack = new Stack<>();
		childList[0] = new List(40);
		for (int i = 1; i < 100; i++)
			childList[i] = new List(5);
		for (int[] relation : path)
			childList[relation[0]].add(relation[1]);
	}

	public int dfs(int N)
	{
		if (childList[N].isempty())
			return (-1);
		return_val = -1;
		stack.clear();
		stack.push(N);
		while (!stack.is_empty() && return_val == -1)
		{
			int poped = stack.pop();
			if (poped > N)
				return_val = poped;
			List dfs_loop = childList[poped]; 
			for (int i = dfs_loop.size() - 1; i >= 0; i--)
				stack.push(dfs_loop.get(i));
		}
		return (return_val);
	}
}
