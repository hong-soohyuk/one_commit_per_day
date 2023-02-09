package solution;

import java.io.*;
import java.util.*;


class Solution {	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int		test, T;
		
		T = Integer.parseInt(br.readLine());
		for (test = 1; test <= T; test++)
		{
			String	text = br.readLine();
			String	target = br.readLine();
			int		text_len = text.length();
			int		target_len = target.length();
			int		kmp[] = new int[target_len];

			int		answer = 0;
			int		j = 0;

			//init kmp_table;
			for (int i = 1; i < target_len; i++)
			{
				while (j > 0 && target.charAt(i) != target.charAt(j))
					j = kmp[j - 1];
				if (target.charAt(i) == target.charAt(j))
					kmp[i] = ++j;
			}

			j = 0;
			for (int i = 0; i < text_len; i++)
			{
				while (j > 0 && text.charAt(i) != target.charAt(j))
					j = kmp[j - 1];
				if (text.charAt(i) == target.charAt(j))
				{
					if (j == target_len - 1)
					{
						answer++;
						j = kmp[j];
					}
					else
						j++;
				}
			}
			sb.append("#").append(test).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
}
