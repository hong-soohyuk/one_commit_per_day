import java.io.*;
import java.util.*;

class Node implements Comparable<Node>
{
	int	left;
	int	count;
	
	Node(int left, int count)
	{
		this.left = left;
		this.count = count;
	}

	@Override
	public int compareTo(Node o) {return (this.count - o.count);}}

class Solution {
	static PriorityQueue<Node>	min_queue;
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int		test, T;
		
		min_queue = new PriorityQueue<>();
		T = Integer.parseInt(br.readLine());
		for (test = 1; test <= T; test++) {
			min_queue.clear();
			int N = Integer.parseInt(br.readLine());
			int	A[] = new int[N];
			String array[] = br.readLine().split(" ");
			int K = Integer.parseInt(br.readLine());
			for (int i = 0; i < N; i++)
				A[i] = Integer.parseInt(array[i]);
			min_queue.add(new Node(K, 0));
			int count = K;
			while (!min_queue.isEmpty())
			{
				Node n = min_queue.poll();
				if (n.left == 0)
				{
					count = n.count;
					break ;
				}
				min_queue.add(new Node(0, n.count + n.left));
				
				for (int i = 0; i < N; i++)
					min_queue.add(new Node(n.left / A[i], n.count + n.left % A[i]));
			}
			sb.append("#").append(test).append(" ").append(count).append("\n");
		}
		System.out.println(sb);
		br.close();
	}	
}
