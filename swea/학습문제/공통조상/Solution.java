import java.io.*;
import java.util.*;

class Solution
{
	static BufferedReader	br;
	static StringBuilder	sb;
	static int				test_case;
	static int				tree_size;
	static int				t_case = -1;
	static int				nodes;
	static int				edges;
	static int				left;
	static int				right;
	static Node				common;
	static boolean[]		visited;
	static Node[]			tree;
	
	private static void	info_initialize(StringTokenizer st)
	{
		nodes = Integer.parseInt(st.nextToken());
		edges = Integer.parseInt(st.nextToken());
		left = Integer.parseInt(st.nextToken());
		right = Integer.parseInt(st.nextToken());
	}

	public static void main(String[] argments) throws Exception
	{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		test_case = Integer.parseInt(br.readLine());
		while (++t_case < test_case)
		{
			StringTokenizer info_token = new StringTokenizer(br.readLine(), " ");
			info_initialize(info_token);
			tree_size = 0;
			visited = new boolean[nodes + 1];
			tree = new Node[nodes + 1];
			for (int i = 1; i <= nodes; i++)
				tree[i] = new Node(i);				
			StringTokenizer value_token = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < edges; j++)
			{
				int	parent = Integer.parseInt(value_token.nextToken());
				int	child = Integer.parseInt(value_token.nextToken());
				if (tree[parent].left == 0) tree[parent].left = child;
				else tree[parent].right = child;
				tree[child].parent = parent;
			}
			common = get_common(tree, visited, left, right);
			get_tree_size(tree, common);
			sb.append("#").append(t_case + 1).append(" ").append(common.value).append(" ").append(tree_size).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void get_tree_size(Node[] tree, Node common)
	{
		++tree_size;
		if (common.left != 0) get_tree_size(tree, tree[common.left]);
		if (common.right != 0) get_tree_size(tree, tree[common.right]);
	}

	private static Node get_common(Node[] tree, boolean[] visited, int left, int right)
	{
		int	parent;
		int	common = -1;

		while (left != 1)
		{
			parent = tree[left].parent;
			visited[parent] = true;
			left = parent;
		}
		while (right != 1)
		{
			parent = tree[right].parent;
			if (visited[parent])
			{
				common = parent;
				break ;
			}
			visited[parent] = true;
			right = parent;
		}
		return (tree[common]);
	}

	private static class Node
	{
		int	value;
		int	parent;
		int	left;
		int	right;

		Node(int value)
		{
			this.value = value;
			this.parent = 0;
			this.left = 0;
			this.right = 0;
		}
	}
}

