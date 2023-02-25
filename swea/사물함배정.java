import java.util.HashMap;
import java.util.Map;

class Locker
{
	int		id;
	Locker	prev;
	Locker	next;
	Locker(int id)
	{
		this.id = id;
		this.prev = null;
		this.next = null;
	}
}

class UserSolution {
	static final int	MAX_N = 100000000;
	Locker[]		lockers = new Locker[MAX_N + 1];
	Map<Integer, Integer> user_to_locker = new HashMap<>();
	int			n;
	int			size;
	Locker		head;

	public void init(int N) {
		n = N;
		size = 0;
		user_to_locker.clear();
		head = null;
		for (int i = 1; i <= N; i++)
			lockers[i] = null;
	}

	void	push_after(int prev, Locker new_node)
	{
		new_node.next = lockers[prev].next;
		lockers[prev].next = new_node;
		new_node.prev = lockers[prev];
		if (new_node.next != null)
			new_node.next.prev = new_node;
		++size;
	}

	void	push_before(int next, Locker new_node)
	{
		new_node.prev = lockers[next].prev;
		lockers[next].prev = new_node;
		new_node.next = lockers[next];
		if (new_node.prev != null)
			new_node.prev.next = new_node;
		else
			head = new_node;
		++size;
	}

	public int arrive(int mId)
	{
		int	ret_val = -1;
		if (size == 0)
		{
			lockers[1] = new Locker(1);
			user_to_locker.put(mId, 1);
			head = lockers[1];
			++size;
			return (1);
		}
		else if (size == 1)
		{
			if (head.id - 1 < n - head.id)
			{
				lockers[n] = new Locker(n);
				user_to_locker.put(mId, n);
				push_after(head.id, lockers[n]);
				ret_val = n;
			}
			else
			{
				lockers[1] = new Locker(1);
				user_to_locker.put(mId, 1);
				push_before(head.id, lockers[1]);
				ret_val = 1;
			}
		}
		else
			ret_val = find_new_node(mId);
		return ret_val;
	}

	private int find_new_node(int userId)
	{
		Locker	current;
		int		max_interval;
		int		push_id;

		current = head;
		push_id = current.id;
		max_interval = 0;

		while (current.next != null)
		{
			if (current.next.id - current.id > max_interval)
			{
				max_interval = current.next.id - current.id;
				push_id = current.id;
			}
			current = current.next;
		}
		int	index = check_side(current.id, max_interval, userId);
		if (index != -1)
			return (index);
		else
		{
			int middle;
			if (lockers[push_id].next != null)
				middle = (push_id + lockers[push_id].next.id) / 2;
			else
				middle = (push_id + n) / 2;
			lockers[middle] = new Locker(middle);
			user_to_locker.put(userId, middle);
			push_after(push_id, lockers[middle]);
			return (middle);
		}
	}
	private int check_side(int current_id, int max_interval, int userId)
	{
		int ret_val = -1;

		if ((lockers[1] == null && head.id >= (max_interval))
				|| (lockers[n] == null && (n - current_id + 1) > (max_interval)))
		{
			if (head.id < (n - current_id + 1))
				ret_val = n;
			else
				ret_val = 1;
			lockers[ret_val] = new Locker(ret_val);
			user_to_locker.put(userId, ret_val);
			if (ret_val == 1)
				push_before(head.id, lockers[1]);
			else
				push_after(current_id, lockers[n]);
			return (ret_val);
		}
		return (-1);
	}

	void delete(Locker del)
	{
		if (head == null || del == null)
			return;
		if (head == del)
			head = del.next;
		if (del.next != null)
			del.next.prev = del.prev;
		if (del.prev != null)
			del.prev.next = del.next;
		--size;
	}

	public int leave(int mId) {
		int	del = user_to_locker.get(mId);
		delete(lockers[del]);
		lockers[del] = null;
		user_to_locker.remove(mId);
		return (n - size);
	}
}
