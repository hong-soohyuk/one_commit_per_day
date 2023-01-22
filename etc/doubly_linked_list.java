class Node
{
	public int	data;
	public Node	prev;
	public Node	next;

	public Node(int data)
	{
		this.data = data;
		this.prev = null;
		this.next = null;
	}
}

public class UserSolution
{
	private final static int	MAX_NODE = 10000;
	private Node[]				node = new Node[MAX_NODE];
	private int					nodeCnt = 0;
	private Node				head;

	public Node getNode(int data)
	{
		node[nodeCnt] = new Node(data);
		return (node[nodeCnt++]);
	}

	public void init()
	{
		nodeCnt = 0;
		head = null;
	}
	
	public void addNode2Head(int data)
	{
		Node	new_node;

		new_node = getNode(data);
		if (head != null)
			head.prev = new_node;
		new_node.next = head;
		head = new_node;
	}

	public void addNode2Tail(int data)
	{
		Node	current;

		current = head;
		if (current == null)
			addNode2Head(data);
		else
		{
			while (current.next != null)
				current = current.next;
			current.next = getNode(data);
			current.next.prev = current;
		}
	}

	public void addNode2Num(int data, int num)
	{
		Node	node_added;
		Node	current;
		int		i;

		if (num == 1)
			addNode2Head(data);
		else if (num == nodeCnt + 1)
			addNode2Tail(data);
		else
		{
			current = head;
			i = 0;
			while (++i < num - 1)
				current = current.next;
			
			node_added = getNode(data);
			node_added.next = current.next;
			node_added.prev = current;
			current.next.prev = node_added;
			current.next = node_added;
		}		
	}
	
	public int findNode(int data)
	{
		Node	current;
		int		index;

		current = head;
		index = 1;
		while (current != null && current.data != data)
		{
			current = current.next;
			++index;
		}
		return (index);
	}
	
	public void removeNode(int data)
	{
		Node	current;
		
		current = head;
		if (current.data == data)
		{
			current.next.prev = null;
			head = current.next;
			current.next = null;
		}
		else
		{
			while (current != null && current.data != data)
				current = current.next;
			if (current == null)
				return ;
			if (current.next == null)
			{
				current.prev.next = null;
				current.prev = null;
			}
			else
			{
				current.prev.next = current.next;
				current.next.prev = current.prev;
			}			
		}
		--nodeCnt;
	}

	public int getList(int[] output)
	{
		Node	current;
		int		i;
		
		current = head;
		i = -1;
		while (++i < nodeCnt && current != null)
		{
			output[i] = current.data;
			current = current.next;
		}
		return (nodeCnt);
	}
	
	public int getReversedList(int[] output)
	{
		Node	current;
		int		i;
		
		current = head;
		i = nodeCnt;
		while (--i > -1)
		{
			output[i] = current.data;
			current = current.next;
		}
		return (nodeCnt);
	}
}
