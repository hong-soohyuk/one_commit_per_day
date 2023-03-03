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
	static boolean	parsing_ok;	

	public static void main(String[] args) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		for (int tc = 1; tc < 11; tc++)
		{
			node_num = Integer.parseInt(br.readLine());
			tree = new Node[node_num + 1];
			parsing_ok = true;
			for (int i = 0; i < node_num; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int index = Integer.parseInt(st.nextToken());
				String value = st.nextToken();
				if (value.equals("+") || value.equals("-") || value.equals("*")|| value.equals("/"))
				{
					tree[index] = new Node(value.charAt(0) * -1);
					if (!st.hasMoreTokens())
						parsing_ok = false ;
					else
					{
						tree[index].left = Integer.parseInt(st.nextToken());
						if (!st.hasMoreTokens())
							parsing_ok = false;
						else
							tree[index].right = Integer.parseInt(st.nextToken());											
					}
				}
				else
					tree[index] = new Node(Integer.parseInt(value));
				if (st.hasMoreTokens())
					parsing_ok = false;
			}
			if (parsing_ok)
				sb.append("#" + tc + " 1\n");
			else
				sb.append("#" + tc + " 0\n");
		}
		System.out.println(sb);
		br.close();
	}
}
