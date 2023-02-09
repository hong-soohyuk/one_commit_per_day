package solution;

import java.io.*;
import java.util.*;

class Solution {
	static PriorityQueue<Integer>	max_queue;
	static PriorityQueue<Integer>	min_queue;

	static int	make_even()
	{
		if (max_queue.size() < min_queue.size())
			max_queue.add(min_queue.poll());
		else if (max_queue.size() - 1 > min_queue.size())
			min_queue.add(max_queue.poll());
		return (max_queue.peek());
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer info;
		StringTokenizer numbers;
		int		test, T, lineNum, init; 
		
		max_queue = new PriorityQueue<>(Collections.reverseOrder());
		min_queue = new PriorityQueue<>();
		T = Integer.parseInt(br.readLine());
		for (test = 1; test <= T; test++) {

			info = new StringTokenizer(br.readLine(), " ");

			lineNum = Integer.parseInt(info.nextToken());
			init = Integer.parseInt(info.nextToken());
			max_queue.clear();
			min_queue.clear();
			max_queue.add(init);
			int answer = 0;
			for (int i = 0; i < lineNum; i++)
			{
				numbers = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 2; j++)
				{
					int number = Integer.parseInt(numbers.nextToken());
					if (number > max_queue.peek())
						min_queue.add(number);
					else
						max_queue.add(number);
				}
				answer += make_even();
				if (answer >= 20171109) answer %= 20171109;
			}
			sb.append("#").append(test).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
		br.close();
	}	
}
