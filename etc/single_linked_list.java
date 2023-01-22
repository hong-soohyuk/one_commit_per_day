class Node {
	public int	data;
	public Node	next;

	public Node(int data) {
		this.data = data;
		this.next = null;
	}
}

public class UserSolution {
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
			current.next = getNode(data);;
		}
	}

	public void addNode2Num(int data, int num)
	{
		Node	prev;
		Node	next;
		int		i;

		if (num == 1)
			addNode2Head(data);
		else 
		{
			prev = head;
			i = 0;
			while (++i < num - 1)
				prev = prev.next;
			next = prev.next;
			prev.next = getNode(data);;
			prev.next.next = next;
		}
	}

	public void removeNode(int data)
	{
		Node	current;
		Node	node_removed;

		current = head;
		if (current.data == data)
		{
			head = current.next;
			current.next = null;
			--nodeCnt;
			return ;
		}
		while (current.next != null && current.next.data != data)
			current = current.next;
		if (current.next == null)
			return ;
		node_removed = current.next;
		if (node_removed.next == null)
			current.next = null;
		else
		{
			current.next = current.next.next;
			node_removed.next = null;			
		}
		--nodeCnt;
	}

	public int getList(int[] output)
	{
		Node	current;
		int		i;

		current = head;
		i = 0;
		while (current != null)
		{
			output[i++] = current.data;
			current = current.next;
		}
		return (nodeCnt);
	}
}
