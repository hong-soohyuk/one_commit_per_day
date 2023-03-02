import java.util.LinkedList;
import java.util.Queue;

public class UserSolution
{
	static Queue<Integer>[]	diff = new LinkedList[50000];

	int	bin_search(int index, int N)
	{
		int	left = 0;
		int	right = N;
		int	mid;
		while (left < right)
		{
			mid = (left + right) / 2;
			if (Solution.checkCards(0, index, mid))
				right = mid;
			else
				left = mid + 1;
		}
		return left;
	}

	public void playGame(int N)
	{
		for (int i = 1; i < (N + N); i++)
		{
			int pos = bin_search(i, N);
			if (diff[pos] == null)
				diff[pos] = new LinkedList<>();
			diff[pos].add(i);
		}

		Solution.checkCards(0, diff[0].poll(), 0);
		diff[0].clear();

		for (int i = 1; i < N; i++)
		{
			if (diff[i] == null) continue ;
			while (!diff[i].isEmpty())
			{
				int	a = diff[i].poll();
				int	polled = diff[i].poll();
				while (Solution.checkCards(a, polled, 0) == false)
				{
					diff[i].add(polled);
					polled = diff[i].poll();
				}
			}
		}
	}
}

