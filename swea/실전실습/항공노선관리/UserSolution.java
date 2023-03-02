import java.util.*;

class pair<T> implements Comparable<pair>
{
	int	first;
	T	second;
	pair(int a, T b)
	{
		this.first = a;
		this.second = b;
	}
	@Override
	public int compareTo(pair o) {return (this.first - o.first);}
}

class UserSolution
{
	static int d1[];
	static int adj1[][];
	static int d0[][];
	static Vector<pair<Integer>> adj0[][];
	static int			n;

	public void init(int N)
	{
		n = N;
		d1 = new int[N];
		d0 = new int[N][24];
		adj1 = new int[N][N];
		adj0 = new Vector[N][24];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				adj1[i][j] = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++)
			for (int j = 0; j < 24; j++)
				adj0[i][j] = new Vector<>();
	}

	public void add(int mStartAirport, int mEndAirport, int mStartTime, int mTravelTime, int mPrice)
	{
		adj0[mStartAirport][mStartTime].add(new pair<>(mEndAirport, mTravelTime));
		adj1[mStartAirport][mEndAirport] = Math.min(adj1[mStartAirport][mEndAirport], mPrice);
	}

	public int minTravelTime(int mStartAirport, int mEndAirport, int mStartTime)
	{
		PriorityQueue<pair<pair<Integer>>> pq = new PriorityQueue<>(Comparator.naturalOrder());
		for (int i = 0; i < n; i++)
			for (int j = 0; j < 24; j++)
				d0[i][j] = Integer.MAX_VALUE;

		d0[mStartAirport][mStartTime] = 0;
		pq.add(new pair(0, new pair<>(mStartAirport, mStartTime)));
		while (!pq.isEmpty())
		{
			pair<pair<Integer>> p = pq.poll();
			int	totalCost = p.first;
			int	current = p.second.first;
			int	arrival = p.second.second;

			if (d0[current][arrival] != totalCost)
				continue ;
			if (current == mEndAirport)
				return totalCost;

			for (int depart_time = 0; depart_time < 24; depart_time++)
			{
				if (adj0[current][depart_time].size() == 0)
					continue;
				for (int index = 0; index < adj0[current][depart_time].size(); index++)
				{
					pair<Integer> vec = adj0[current][depart_time].get(index);
					int	destination = vec.first;
					int	travel_time = vec.second;
					int	wait_time = (depart_time < arrival ? depart_time + 24 : depart_time) - arrival;
					int	next_departure = (arrival + wait_time+ travel_time) % 24;
					if (d0[destination][next_departure] > d0[current][arrival] +wait_time+ travel_time)
					{
						d0[destination][next_departure] = d0[current][arrival] +wait_time+ travel_time;
						pq.add(new pair(d0[destination][next_departure], new pair<>(destination, next_departure)));
					}
				}

			}
		}
		return -1;
	}

	public int minPrice(int mStartAirport, int mEndAirport)
	{
		PriorityQueue<pair<Integer>>	pq = new PriorityQueue<>(Comparator.naturalOrder());
		for (int i = 0; i < n; i++)
			d1[i] = Integer.MAX_VALUE;
		d1[mStartAirport] = 0;
		pq.add(new pair<>(0, mStartAirport));
		while (!pq.isEmpty())
		{
			pair<Integer>	popped = pq.poll();
			int		totalCost = popped.first;
			int		current = popped.second;
			if (totalCost != d1[current])
				continue ;
			if (current == mEndAirport)
				return (totalCost);

			for (int i = 0; i < n; i++)
			{
				if (adj1[current][i] == Integer.MAX_VALUE)
					continue;
				if (d1[i] > d1[current] + adj1[current][i])
				{
					d1[i] = d1[current] + adj1[current][i];
					pq.add(new pair(d1[i], i));
				}
			}
		}
		return (-1);
	}
}

