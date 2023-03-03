import java.io.*;

class Solution {
	static int		MAX_LENGTH = 100000;
	static int[]	max_seg = new int[MAX_LENGTH << 2];
	static int[]	min_seg = new int[MAX_LENGTH << 2];
	static int[]	array = new int[MAX_LENGTH];
	static int[]	answers = new int[MAX_LENGTH];
	static int		length;

	public static void main(String[] args) throws Exception {
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter	bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int		        test, T;
		int				num_query, answer_size;
		String[]		infos, elements, queries;
		T = Integer.parseInt(br.readLine());
		for (test = 1; test <= T; test++)
		{
			infos = br.readLine().split(" ");
			length = Integer.parseInt(infos[0]);
			num_query = Integer.parseInt(infos[1]);
			answer_size = 0;
			elements = br.readLine().split(" ");

			for (int i = 0; i < length; i++)
				array[i] = Integer.parseInt(elements[i]);
			init(1, 0, length - 1);
			for (int i = 0; i < num_query; i++)
			{
				queries = br.readLine().split(" ");
				int cmd = Integer.parseInt(queries[0]);
				int	first = Integer.parseInt(queries[1]);
				int	second = Integer.parseInt(queries[2]);
				if (cmd == 0)
					replace(1, 0, length - 1, first, second);
				else
					answers[answer_size++] = (get_max(1, 0, length - 1, first, second - 1) - get_min(1, 0, length - 1, first, second - 1));
			}
			bw.write("#" + test);
			for (int j = 0; j < answer_size; j++)
				bw.write(" "+answers[j]);
			bw.write("\n");
		}
		br.close();
		bw.flush();
		bw.close();
	}

	private static void init(int index, int left, int right)
	{
		if (left == right)
		{
			max_seg[index] = array[left];
			min_seg[index] = array[left];
			return ;
		}
		int mid = (left + right) / 2;
		int	ind1 = index << 1;
		int ind2 = (index << 1) | 1;
		init(ind1, left, mid);
		init(ind2, mid + 1, right);
		max_seg[index] = Math.max(max_seg[ind1], max_seg[ind2]);
		min_seg[index] = Math.min(min_seg[ind1], min_seg[ind2]);
	}

	private static void replace(int idx, int left, int right, int i, int value)
	{
		if (left > i || right < i)
			return;
		if (left == right) {
			max_seg[idx] = value;
			min_seg[idx] = value;
			return;
		}

		int mid = (left + right) / 2;
		int idx1 = idx << 1;
		int idx2 = (idx << 1) | 1;

		replace(idx1, left, mid, i, value);
		replace(idx2, mid + 1, right, i, value);

		max_seg[idx] = Math.max(max_seg[idx1], max_seg[idx2]);
		min_seg[idx] = Math.min(min_seg[idx1], min_seg[idx2]);
	}

	private static int get_min(int i, int left, int right, int l, int r)
	{
		if (r < left || right < l)
			return (Integer.MAX_VALUE);
		if (l <= left && r >= right)
			return (min_seg[i]);
		int mid = (left + right) / 2;
		return Math.min(get_min(i << 1, left, mid, l, r), get_min(i << 1 | 1, mid + 1, right, l, r));
	}

	private static int get_max(int idx, int left, int right, int start, int end)
	{
		if (right < start || left > end)
			return (Integer.MIN_VALUE);
		if (left >= start && right <= end)
			return max_seg[idx];
		int mid = (left + right) / 2;
		return Math.max(get_max(idx << 1, left, mid, start, end), get_max((idx << 1) | 1, mid + 1, right, start, end));
	}
}

