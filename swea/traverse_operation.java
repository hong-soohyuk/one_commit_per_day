package solution;

import java.io.*;
import java.util.*;

class Node 
{
	int	left;
	int	right;
	int	value;

	Node(int value)
	{
		this.left = -1;
		this.right = -1;
		this.value = value;
	}
}

class Solution
{
	static BufferedReader br;
	static StringBuilder sb;
	static int		TC;
	static int		node_num;
	static Node[]	tree;

	public static void main(String[] args) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		for (int tc = 1; tc < 11; tc++)
		{
			node_num = Integer.parseInt(br.readLine());
			tree = new Node[node_num + 1];
			for (int i = 0; i < node_num; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int index = Integer.parseInt(st.nextToken());
				String value = st.nextToken();
				if (value.equals("+") || value.equals("-") || value.equals("*")|| value.equals("/"))
				{
					tree[index] = new Node(value.charAt(0) * -1);
					tree[index].left = Integer.parseInt(st.nextToken());
					tree[index].right = Integer.parseInt(st.nextToken());					
				}
				else
					tree[index] = new Node(Integer.parseInt(value));
			}
			int result = post_traverse(tree, 1);
			sb.append("#" + tc + " " + result + "\n");
		}
		System.out.println(sb);
		br.close();
	}

	private static int post_traverse(Node[] tree, int index)
	{
		if (tree[index].left == -1 || tree[index].right == -1)
			return (tree[index].value);
		int	left = post_traverse(tree, tree[index].left);
		int right = post_traverse(tree, tree[index].right);
		if (tree[index].value * -1 == '+')
			return (left + right);
		else if (tree[index].value * -1 == '-')
			return (left - right);
		else if (tree[index].value * -1 == '*')
			return (left * right);
		else if (tree[index].value * -1 == '/')
			return (left / right);
		else
			return (-1);
	}
}
