import java.io.*;
import java.util.*;

class Solution {
	
	public static void main(String[] argments) throws Exception
	{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		int				test_case = Integer.parseInt(br.readLine());
		int				M;
		int				N;
		int				i = -1;

		while (++i < test_case)
		{
			StringTokenizer tokenizer = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(tokenizer.nextToken());
			N = Integer.parseInt(tokenizer.nextToken());
			int	and_operend = (1 << M) - 1;
			int	result = N & and_operend;
			if (result == and_operend)
				System.out.printf("#%d ON\n", i + 1);
			else
				System.out.printf("#%d OFF\n", i + 1);
		}
	}
}

