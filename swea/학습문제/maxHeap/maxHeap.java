package solution;

import java.io.*;
import java.util.*;

class Solution {
	
	static int[]	tree;
	static int		tree_index;

	public static void main(String[] args) throws Exception {
		int 			TC, Q, cmd;
		String			read_line;
		BufferedReader	br;
		StringTokenizer	st;
		StringBuilder	sb;

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		read_line = br.readLine();
		TC = Integer.parseInt(read_line);

		for (int tc = 1; tc <= TC; tc++) {
			read_line = br.readLine();
			Q = Integer.parseInt(read_line);
			tree = new int[Q + 1];
			tree_index = 0;

			sb.append("#").append(tc);
			for (int i = 0; i < Q; i++)
			{
				read_line = br.readLine();
				st = new StringTokenizer(read_line, " ");
				cmd = Integer.parseInt(st.nextToken());	
				switch(cmd)
				{
				case 1: push(Integer.parseInt(st.nextToken())); break ;
				case 2: sb.append(" ").append(peek()); pop(); break ;
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static int	parent(int i) {return (i >> 1);}
	static int	left(int i) {return (i << 1);}
	static int	right(int i) {return (i << 1 | 1);}
	static void	swap(int tree[], int x, int y) {int	temp = tree[x]; tree[x] = tree[y]; tree[y] = temp;}

	private static int	peek()
	{
		if (tree_index == 0)
			return (-1);
		return (tree[1]);
	}
	private static void pop()
	{
		if (tree_index == 0)
			return ;
		tree[1] = tree[tree_index--];
		for (int i = 1; left(i) <= tree_index;)
		{
			if (left(i) == tree_index || tree[left(i)] > tree[right(i)])
			{
				if (tree[i] < tree[left(i)])
				{
					swap(tree, i, left(i));
					i = left(i);
				}
				else
					break ;
			}
			else
			{
				if (tree[i] < tree[right(i)])
				{
					swap(tree, i, right(i));
					i = right(i);
				}
				else
					break ;
			}
		}
	}

	private static void push(int value)
	{
		tree[++tree_index] = value;
		for (int i = tree_index; parent(i) != 0 && tree[parent(i)] < tree[i]; i >>= 1)
			swap(tree, parent(i), i);
	}
}
