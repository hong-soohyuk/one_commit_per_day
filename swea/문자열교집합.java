import java.io.*;
import java.util.*;

class Solution {	

	public static void main(String[] args) throws Exception {
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Set<String>		set;
		StringBuilder	sb = new StringBuilder();
		int				test, T;
		
		T = Integer.parseInt(br.readLine());
		for (test = 1; test <= T; test++)
		{
			st = new StringTokenizer(br.readLine(), " ");
			set = new HashSet<>();
			int	a_len = Integer.parseInt(st.nextToken());
			int	b_len = Integer.parseInt(st.nextToken());
			int	answer = 0;
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < a_len; i++)
				set.add(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < b_len; i++)
				if (set.contains(st.nextToken()))
					answer++;
			sb.append("#").append(test).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
}
