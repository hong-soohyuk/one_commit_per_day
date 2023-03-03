package solution;
import java.io.*;
import java.util.*;

class Solution
{
	
	static BufferedReader	br;
	static StringBuilder	sb;
	static int				c;
	static int				test_case;
	static int				num_of_island;
	public static void main(String[] argments) throws Exception
	{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		c = -1;
		test_case = Integer.parseInt(br.readLine()); 
		while (++c < test_case)
		{
			num_of_island = Integer.parseInt(br.readLine());
			
		}
		System.out.println(sb);
	}
	
}

