package solution;

import java.io.*;
import java.util.*;

class Solution {	

	public static void main(String[] args) throws Exception {
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		int				test, T;
		
		T = Integer.parseInt(br.readLine());
		for (test = 1; test <= T; test++)
		{
			long	N = Long.parseLong(br.readLine());
			sb.append("#").append(test).append(" ").append(find(N)).append("\n");
		}
		System.out.print(sb);
		br.close();
	}

	private static long find(long n) {
		if (n == 0)
			return (-1);
		if (n == 1)
			return (1);
		long	left = 0;
		long	right = (1L << 31) - 1;
		long	middle, candle;
		while (left <= right)
		{
			middle = (left + right) / 2;
			candle = middle * (middle + 1) / 2;
			if (candle == n)
				return (middle);
			if (candle < n)
				left = middle + 1;
			else
				right = middle - 1;
		}
		return (-1);
	}
}
