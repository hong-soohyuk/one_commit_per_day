import java.io.*;
import java.util.*;

class Node
{
	Node		parent;
	int			depth;
	int			value;
	List<Node>	children = new ArrayList<>();

	Node(Node parent, int depth, int value)
	{
		this.depth = depth;
		this.parent = parent;
		this.value = value;
	}
}

class Solution
{

	static BufferedReader	br;
	static StringBuilder	sb;
	static int				c;
	static int				test_case;
	static int				target;
	static Node[]			tree;
	static Queue<Node>		queue;
	static Map<Long, Integer> cache;

	public static void main(String[] argments) throws Exception
	{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		c = -1;
		test_case = Integer.parseInt(br.readLine());
		queue = new LinkedList<>();
		cache = new HashMap<Long, Integer>();
		while (++c < test_case)
		{
			target = Integer.parseInt(br.readLine());
			
			StringTokenizer node_parents = new StringTokenizer(br.readLine(), " ");
			tree = new Node[node_parents.countTokens() + 2];
			queue.clear();
			cache.clear();
			tree[1] = new Node(null, 0, 1);
			int	value = 2;
			while (node_parents.hasMoreTokens()) 
			{
				int parent = Integer.parseInt(node_parents.nextToken());
				tree[value] = new Node(tree[parent], tree[parent].depth + 1, value);
				tree[parent].children.add(tree[value]);
				tree[value].parent = tree[parent];
				value++;
			}
			
			sb.append("#").append(c+1).append(" ").append(traverse(tree, 1)).append("\n");
		}
		System.out.println(sb);
	}
	
	static int	lowest_common_ancestor(Node source, Node destination)
	{	
		int		upper_depth;
		int		deeper_depth;
		Node	upper_node;
		Node	deeper_node;

		if (source == destination)
			return (source.depth);
		if (source.depth < destination.depth)
		{
			upper_depth = source.depth;
			deeper_depth = destination.depth;
			upper_node = source;
			deeper_node = destination;
		}
		else
		{
			upper_depth = destination.depth;
			deeper_depth = source.depth;
			upper_node = destination;
			deeper_node = source;
		}
		
		while (upper_depth < deeper_depth)
		{
			deeper_node = deeper_node.parent;
			--deeper_depth;
		}
		return (find_from_cache(upper_node, deeper_node));
	}
	
	private static int find_from_cache(Node a, Node b) {
		if (a == b)
			return (a.depth);
		long key = (long)(a.value) * 100000 + (long)(b.value);
		if (cache.containsKey(key))
			return (cache.get(key));
		int	result = find_from_cache(a.parent, b.parent);
		cache.put(key, result);
		return (result);
	}

	static long	traverse(Node tree[], int index)
	{
		long	sum;
		Node	src;
		Node	dest;

		sum = 0;
		queue.add(tree[index]);
		src = tree[1];
		dest = tree[1];
		while (!queue.isEmpty())
		{
			Node	current = queue.poll();
			int		size = current.children.size();
			int		lca;

			dest = current;
			lca = lowest_common_ancestor(src, dest);
			sum += (src.depth - lca);
			sum += (dest.depth - lca);
			src = dest;
			for (int i = 0; i < size; i++)
				queue.add(current.children.get(i));
		}
		return (sum);
	}

}

