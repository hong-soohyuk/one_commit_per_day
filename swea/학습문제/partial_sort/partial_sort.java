class User
{
	int	id;
	int	income;
	User(int id, int income)
	{
		this.id = id;
		this.income = income;
	}
}

public class UserSolution
{
	User[]	heap;
	int		size;
	public void init() {
		this.heap = new User[100001];
		this.size = 0;
	}
	
	static int	parent(int index) {return (index >> 1);}
	static int	left(int index) {return (index << 1);}
	static int	right(int index) {return ((index << 1) | 1);}

	static void	swap(User heap[], int i, int j) {User temp = heap[i]; heap[i] = heap[j]; heap[j] = temp;}

	public void addUser(int uID, int income)
	{
		int	i;

		heap[++size] = new User(uID, income);
		i = size;
		while (parent(i) != 0 && heap[i].income >= heap[parent(i)].income)
		{
			if (heap[i].income == heap[parent(i)].income && heap[i].id > heap[parent(i)].id)
				break ;
			swap(heap, i, parent(i));
			i = parent(i);
		}
	}
	
	User pop()
	{
		User return_val;
		if (size == 0)
			return (null);
		return_val = heap[1];
		heap[1] = heap[size--];
		for (int i = 1; left(i) <= size;)
		{
			if (left(i) == size || heap[left(i)].income > heap[right(i)].income
					|| (heap[left(i)].income == heap[right(i)].income && heap[left(i)].id < heap[right(i)].id))
			{
				if (heap[i].income < heap[left(i)].income)
				{
					swap(heap, i, left(i));
					i = left(i);
				}
				else if (heap[i].income == heap[left(i)].income && heap[i].id > heap[left(i)].id)
				{
					swap(heap, i, left(i));
					i = left(i);
				}
				else
					break ;
			}
			else
			{
				if (heap[i].income < heap[right(i)].income)
				{
					swap(heap, i, right(i));
					i = right(i);
				}
				else if (heap[i].income == heap[right(i)].income && heap[i].id > heap[right(i)].id)
				{
					swap(heap, i, right(i));
					i = right(i);
				}
				else
					break ;
			}
		}
		return (return_val);
	}

	int getTop10(int[] result)
	{
		int temp_size = size > 10 ? 10 : size;
		User[] users = new User[temp_size];
		for (int i = 0; i < temp_size; i++)
		{		
			users[i] = pop();
			result[i] = users[i].id;
		}
		
		for (int i = 0; i < temp_size; i++)
			addUser(users[i].id, users[i].income);
		return (result.length);
	}
}
