import java.io.*;
import java.util.*;

class Solution
{
	static BufferedReader br;
	static StringBuilder sb;
	static int		TC;
	static int[]	value;
	static int[]	volume;	
	static int[][]	memo;

	static int knap_sack(int V, int volume[], int value[], int n, int memo[][])
	{
		if (n == 0 || V == 0)
			return (0);
		if (memo[n][V] != -1)
			return (memo[n][V]);
		if (volume[n - 1] > V)
			return (memo[n][V] = knap_sack(V, volume, value, n - 1, memo));
		else
			return (memo[n][V] = Math.max(
					knap_sack(V, volume, value, n - 1, memo),
					value[n - 1] + knap_sack(V - volume[n - 1], volume, value, n - 1, memo)
					));
	}
	
	public static void main(String[] args) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		TC =  Integer.parseInt(br.readLine());
		value = new int[100];
		volume = new int[100];
		memo = new int[101][1001];
		for (int tc = 0; tc < TC; tc++)
		{
			Arrays.fill(value, -1);
			Arrays.fill(volume, -1);
			for (int[] dimension : memo)
				Arrays.fill(dimension, -1);
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int	item_len = Integer.parseInt(st.nextToken());
			int	limit_volume = Integer.parseInt(st.nextToken());
			for (int i = 0; i < item_len; i++)
			{
				StringTokenizer item = new StringTokenizer(br.readLine(), " ");
				volume[i] = Integer.parseInt(item.nextToken());
				value[i] = Integer.parseInt(item.nextToken());
			}
			int result = knap_sack(limit_volume, volume, value, item_len, memo);
			sb.append("#" + (tc + 1) + " " + result + "\n");
		}
		System.out.println(sb);
		br.close();
	}
}
