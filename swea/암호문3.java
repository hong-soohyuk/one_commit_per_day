import java.util.*;
import java.io.*;

class Solution
{
	static int	tc;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for(tc = 1; tc < 11; tc++)
		{
			int N = Integer.parseInt(br.readLine());
			List<Integer> crypto = new LinkedList<>();
			String[]	nums = br.readLine().split(" ");
			for (int i = 0; i < N; i++)
				crypto.add(Integer.parseInt(nums[i]));

			int M = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++){
				String cmd = st.nextToken();
				if (cmd.equals("I"))
					insert(crypto, st);
				else if (cmd.equals("D"))
					delete(crypto, st);
				else
					add(crypto, st);
			}

			String	answer = "";
			for (int i = 0; i < 10; i++)
				answer += (" " + crypto.get(i));
			bw.write("#" + tc + answer + "\n");
		}
		br.close();
		bw.flush();
		bw.close();
	}
	public static void insert(List<Integer> script, StringTokenizer st){
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		for (int i = 0; i < y; i++)
			script.add(x + i, Integer.parseInt(st.nextToken()));
	}
	public static void delete(List<Integer> script, StringTokenizer st){
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		for (int i = 0; i < y; i++)
			script.remove(x);
	}
	public static void add(List<Integer> script, StringTokenizer st){
		int y = Integer.parseInt(st.nextToken());
		for (int i = 0; i < y; i++)
			script.add(Integer.parseInt(st.nextToken()));
	}
}
