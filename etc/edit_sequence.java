import java.io.*;
import java.util.*;

class Node
{
	int		data;
	Node	prev;
	Node	next;
	public Node(int data) 
	{
		this.data = data;
		this.prev = null;
		this.next = null;
	}
}

class Solution
{
	
	static BufferedReader	br;
	static StringBuilder	sb;
	static int				test_case;
	static int				t_case = -1;
	
	static int				node_count;
	static int				cmd_count;
	static int				print_index;
	static Node				head;

	private static void	info_initialize(StringTokenizer st)
	{
		head = null;
		node_count = Integer.parseInt(st.nextToken());
		cmd_count = Integer.parseInt(st.nextToken());
		print_index = Integer.parseInt(st.nextToken());
	}

	static void	add_head(int data)
	{
		Node	new_node;

		new_node = new Node(data);
		if (head != null)
			head.prev = new_node;
		new_node.next = head;
		head = new_node;
	}
	
	static void	add_tail(int data)
	{
		Node	current;

		current = head;
		if (current == null)
			add_head(data);
		else
		{
			while (current.next != null)
				current = current.next;
			current.next = new Node(data);
			current.next.prev = current;
		}
	}
	
	static void	insert_node(int index, int data)
	{
		Node	node_added;
		Node	current;
		int		i;

		if (index == 0)
			add_head(data);
		else if (index > node_count - 1)
			add_tail(data);
		else
		{
			current = head;
			i = 0;
			while (++i < index - 1)
				current = current.next;
			node_added = new Node(data);
			node_added.next = current.next;
			node_added.prev = current;
			current.next.prev = node_added;
			current.next = node_added;
		}
		++node_count;
	}
	
	static void	delete_node(int index)
	{
		Node	current;
		int		i;

		current = head;
		if (index == 0)
		{
			current.next.prev = null;
			head = current.next;
			current.next = null;
		}
		else
		{
			i = -1;
			while (++i < index)
				current = current.next;
			if (current == null)
				return ;
			if (current.next == null && index == i)
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
		--node_count;
	}
	
	static void	change_node(int index, int data)
	{
		Node	current;
		int		i;

		current = head;
		if (index == 0)
			current.data = data;
		else if (index == node_count - 1)
		{
			while (current.next != null)
				current = current.next;
			current.data = data;
		}
		else
		{
			i = -1;
			while (++i < index)
				current = current.next;
			current.data = data;
		}
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

			StringTokenizer node_token = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < node_count; i++)
				add_tail(Integer.parseInt(node_token.nextToken()));
			
			for (int j = 0; j < cmd_count; j++)
			{
				StringTokenizer cmd_token = new StringTokenizer(br.readLine(), " ");				
				switch(cmd_token.nextToken())
				{
				case "I":
					insert_node(Integer.parseInt(cmd_token.nextToken()), Integer.parseInt(cmd_token.nextToken()));
					break ;
				case "D":
					delete_node(Integer.parseInt(cmd_token.nextToken()));
					break ;
				case "C":
					change_node(Integer.parseInt(cmd_token.nextToken()), Integer.parseInt(cmd_token.nextToken()));
					break ;
				}
			}
			int	node_data = get_node(print_index);
			sb.append("#").append(t_case + 1).append(" ").append(node_data).append("\n");
		}
		System.out.println(sb);
	}

	private static int get_node(int print_index)
	{
		Node	current;
		int		i;

		if (print_index < 0 || print_index > node_count - 1)
			return (-1);
		current = head;
		i = -1;
		while (++i < print_index)
			current = current.next;
		return (current.data);
	}
	
}

