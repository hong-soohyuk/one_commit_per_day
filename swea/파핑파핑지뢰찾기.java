import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

class Point
{
	int	x;
	int	y;
	Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}

class Solution {
	static int		N;
	static boolean	revealed[][];
	static int		map[][];
	static Queue<Point> bfs = new LinkedList<>();
	static Queue<Point>	click_later = new LinkedList<>();
	static int answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter	bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int		        test, T;
		T = Integer.parseInt(br.readLine());
		for (test = 1; test <= T; test++)
		{
			bfs.clear();
			click_later.clear();
			answer = 0;
			N = Integer.parseInt(br.readLine());
			revealed = new boolean[N][N];
			map = new int[N][N];
			for (int i = 0; i < N; i++)
			{
				String row = br.readLine();
				for (int j = 0; j < N; j++)
					if (row.charAt(j) == '*')
						mine_reveal(i, j);
			}
			click();
			bw.write("#" + test + " " + answer + "\n");
//			System.out.println();
//			for (int i = 0; i < N; i++)
//			{
//				for (int j = 0; j < N; j++)
//					System.out.print(revealed[i][j] + "		");
//				System.out.println();
//			}
		}
		br.close();
		bw.flush();
		bw.close();
	}

	private static void click()
	{
		for (int i = 0; i < N; i++)
		{
			for (int j = 0 ; j < N; j++)
			{
				if (map[i][j] == 0)
					click_bfs(i, j);
				else if (map[i][j] == -1)
					continue ;
				else
					click_later.add(new Point(i, j));
			}
		}
		while (!click_later.isEmpty())
		{
			Point p = click_later.poll();
			if (!revealed[p.x][p.y])
			{
				revealed[p.x][p.y] = true;
				answer++;
			}
		}
	}

	private static void click_bfs(int x, int y)
	{
		if (revealed[x][y])
			return ;
		int	move_x[] = new int[]{1, -1, 0, 0, -1, -1, 1, 1};
		int	move_y[] = new int[]{0, 0, 1, -1, -1, 1, -1, 1};
		bfs.add(new Point(x, y));
		revealed[x][y] = true;
		answer++;
		while(!bfs.isEmpty())
		{
			Point	p = bfs.poll();
			x = p.x;
			y = p.y;
			for (int i = 0; i < 8; i++)
			{
				if (x + move_x[i] >= 0 && y + move_y[i] >= 0 && x + move_x[i] < N && y + move_y[i] < N)
				{
					if (map[x + move_x[i]][y + move_y[i]] == 0 && !revealed[x + move_x[i]][y + move_y[i]])
						bfs.add(new Point(x + move_x[i], y + move_y[i]));
					revealed[x + move_x[i]][y + move_y[i]] = true;
				}
			}
		}
	}

	static void mine_reveal(int x, int y)
	{
		int	move_x[] = new int[]{1, -1, 0, 0, -1, -1, 1, 1};
		int	move_y[] = new int[]{0, 0, 1, -1, -1, 1, -1, 1};
		if (x < 0 || y < 0 || x > N - 1 || y > N - 1)
			return ;
		map[x][y] = -1;
		revealed[x][y] = true;
		for (int i = 0; i < 8; i++)
			if (x + move_x[i] >= 0 && y + move_y[i] >= 0 && x + move_x[i] < N && y + move_y[i] < N)
				if (map[x + move_x[i]][y + move_y[i]] != -1) map[x + move_x[i]][y + move_y[i]]++;
	}
}

