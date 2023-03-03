package solution;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	public static void main(String[] args) throws Exception {
		int 			TC;
		String			read_line, str1, str2;
		BufferedReader	br;
		StringTokenizer	st;
		StringBuilder	sb;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		read_line = br.readLine();
		TC = Integer.parseInt(read_line);
		
		for (int tc = 1; tc <= TC; tc++) {
			read_line = br.readLine();
			st = new StringTokenizer(read_line, " ");
			
			str1 = st.nextToken();
			str2 = st.nextToken();
			sb.append("#").append(tc).append(" ").append(longest_common_string(str1, str2)).append("\n");
		}
		System.out.println(sb);
	}

	private static int longest_common_string(String str1, String str2) {
		int		len_1 = str1.length();
		int		len_2 = str2.length();
		int		result = 0;	
		int[][]	memo = new int[len_1 + 1][len_2 + 1];
		for (int i = 0; i <= len_1; i++)
		{
			for (int j = 0; j <= len_2; j++)
			{
				if (i == 0 || j == 0)
					memo[i][j] = 0;
				else if (str1.charAt(i - 1) == str2.charAt(j - 1))
				{
					memo[i][j] = memo[i - 1][j - 1] + 1;
					result = Integer.max(result, memo[i][j]);
				}
				else
					memo[i][j] = Integer.max(memo[i][j - 1], memo[i - 1][j]);
			}
		}
		return (result);
	}
}
