import java.util.*;

class UserSolution {
	Map<Integer, Set<Integer>>	map;
	char[]			original;

	private	int	str2hash(char[] str, int start)
	{
		int	hash = 0;
		for (int i = 0; i < 3 && start + i < str.length; i++)
		{
			hash <<= 5;
			hash += (str[start + i] - 'a' + 1);
		}
		return (hash);
	}
	public void init(int N, char[] string)
	{
		map = new HashMap<>();
		original = new char[N];
		for (int i = 0; i < N; i++)
			original[i] = string[i];
		for (int i = 0; i < N - 2; i++)
		{
			int hash = str2hash(original, i);
			if (!map.containsKey(hash))
				map.put(hash, new TreeSet<>());
			map.get(hash).add(i);
		}
	}
	public int change(char[] target, char[] replace)
	{
		int	target_hash = str2hash(target, 0);
		int	replace_hash = str2hash(replace, 0);
		if (!map.containsKey(target_hash))
			return (0);
		if (!map.containsKey(replace_hash))
			map.put(replace_hash, new TreeSet<>());
		int		count = 0;
		int		j;
		int		prev = -1;
		boolean	string_check;
		List<Integer>	array = new LinkedList<>();
		for (int i : map.get(target_hash))
			array.add(i);
		for (int i : array)
		{
			if (prev == -1 || prev + 2 < i)
			{
			string_check = true;
			for (j = 0; j < 3; j++)
				if (original[i + j] != target[j])
					string_check = false;
			if (string_check)
			{
				for (j = 0; j < 3; j++)
					original[i + j] = replace[j];
				count++;
				map.get(target_hash).remove(i);
				prev = i;
			}
			}
		}
		array.removeAll(map.get(target_hash));
        map.get(target_hash).clear();
		for (int i : array)
		{
			for (j = -2; j < 3; j++)
			{
				if (i + j < 0 || i + j > original.length - 2)
					continue;
				int hash = str2hash(original, i + j);
				if (!map.containsKey(hash))
					map.put(hash, new TreeSet<>());
				map.get(hash).add(i+j);
			}
		}
		return (count);
	}

	public void result(char[] ret)
	{
		for (int i = 0; i < original.length; i++)
			ret[i] = original[i];
	}
}
