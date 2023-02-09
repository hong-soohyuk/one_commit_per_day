import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String arguments[]) throws Exception
	{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		int				test, T;
		int				n, p;
		boolean			visited[];
		T = Integer.parseInt(br.readLine());
		for (test = 1; test <= T; test++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			p = Integer.parseInt(st.nextToken());
			visited = new boolean[1000001];
			Arrays.fill(visited, false);
			st = new StringTokenizer(br.readLine(), " ");
			int last_day = -1;
			for (int i = 0; i < n; i++)
			{
				int day = Integer.parseInt(st.nextToken());
				visited[day] = true;
				last_day = Math.max(last_day, day);
			}
			sb.append("#").append(test).append(" ").append(find_max(n, p, visited, last_day)).append("\n");
		}
		System.out.print(sb);
		br.close();
	}
	private static int find_max(int n, int p, boolean[] visited, int last_day)
	{
		int max = p + 1;
		int	start = 1;
		int	end = 1;
		int	seq = 0;
		while (end < last_day + 1)
		{
			if (visited[end])
			{
				end++;
				seq++;
				max = Math.max(max, seq);
			}
			else
			{
				if (p == 0)
				{
					max = Math.max(max, seq);
					if (!visited[start])
						p++;
					start++;
					seq--;
				}
				else {
					p--;
					seq++;
					end++;
				}
			}
		}
		return (max);
	}
}

