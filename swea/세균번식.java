import java.util.*;

class Cell
{
	int target;
	int	energy;
	int	marker;
	Point	point;
	Cell(int target, int energy, int marker, Point point)
	{
		this.target = target;
		this.energy = energy;
		this.marker = marker;
		this.point = point;
	}
}

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

class UserSolution
{
	int	N;
	int	marker;
	int	nCount[] = new int[3];
	Cell[][]	map = new Cell[101][101];
	PriorityQueue<Cell>	pq;
	int[]	move_x = new int[]{1, -1, 0, 0};
	int[]	move_y = new int[]{0, 0, -1, 1};

	private boolean	bound_check(int x, int y) {return (x > 0 && y > 0 && x <= N && y <= N);}

	public void init(int N, int[][] mDish)
	{
		this.N = N;
		for (int i = 1; i <= N; i++)
			for(int j = 1; j <= N; j++)
				map[i][j] = new Cell(0, mDish[i - 1][j - 1], 0, new Point(i, j));
		marker = 0;
		nCount[1] = nCount[2] = 0;

		pq = new PriorityQueue<>((a, b) -> {
			if (a.energy == b.energy)
			{
				if(a.point.x == b.point.x)
					return (a.point.y - b.point.y);
				else
					return (a.point.x - b.point.x);
			}
			else
				return (b.energy - a.energy);
		});
	}

	private void	update(int row, int col, int t)
	{
		Queue<Integer[]> queue = new LinkedList<>();
		queue.add(new Integer[] {row, col});
		while (!queue.isEmpty())
		{
			Integer[] popped = queue.poll();
			row = popped[0];
			col = popped[1];

			for (int i = 0; i < 4; i++)
			{
				int	new_row = row + move_x[i];
				int	new_col = col + move_y[i];
				if (!bound_check(new_row, new_col)) continue;
				if (map[new_row][new_col].marker >= marker) continue;
				if (map[new_row][new_col].target == (3 - t)) continue;

				map[new_row][new_col].marker = marker;
				if (map[new_row][new_col].target == t)
					queue.add(new Integer[]{new_row, new_col});
				else if (map[new_row][new_col].target == 0)
					pq.add(map[new_row][new_col]);
			}
		}
	}

	public int dropMedicine(int mTarget, int mRow, int mCol, int mEnergy)
	{
		++marker;
		int	current_type = map[mRow][mCol].target;
		if (current_type != 0 && current_type != mTarget)
			return nCount[mTarget];
		Cell	cell = map[mRow][mCol];
		if (cell.target == 0)
		{
			cell.target = mTarget;
			nCount[mTarget]++;
			mEnergy -= cell.energy;
		}

		pq.clear();
		while (mEnergy > 0)
		{
			update(cell.point.x, cell.point.y, mTarget);
			if (pq.size() == 0)
				break;
			cell = pq.poll();
			cell.target = mTarget;
			nCount[mTarget]++;
			mEnergy -= cell.energy;
		}
		return nCount[mTarget];
	}

	public int cleanBacteria(int mRow, int mCol)
	{
		++marker;
		int	target = map[mRow][mCol].target;
		if (target == 0)
			return (-1);
		Queue<Integer[]> queue = new LinkedList<>();
		queue.add(new Integer[]{mRow, mCol});
		while (!queue.isEmpty())
		{
			Integer[]	popped = queue.poll();
			int row = popped[0];
			int	col = popped[1];

			map[row][col].target = 0;
			nCount[target]--;

			for (int i = 0; i < 4; i++)
			{
				int new_row = row + move_x[i];
				int new_col = col + move_y[i];
				if (!bound_check(new_row, new_col))	continue;
				if (map[new_row][new_col].marker >= marker)	continue;
				if (map[new_row][new_col].target != target)	continue;

				queue.add(new Integer[]{new_row, new_col});
				map[new_row][new_col].marker = marker;
			}
		}
		return nCount[target];
	}
}
