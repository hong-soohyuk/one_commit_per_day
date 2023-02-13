import java.util.*;

class UserSolution {

	int[]	string_hash;
	char[]	original;

	private	int	str2hash(char str[], int length)
	{
		int	hash = 0;
		for (int i = 0; i < length; i++)
		{
			hash <<= 5;
			hash += (str[i] - 'a' + 1);
		}
		return (hash);
	}
	public void init(int N, char[] string)
	{
		string_hash = new int[N];
		original = new char[N];
		for (int i = 0; i < N; i++)
		{
			string_hash[i] = 0;
			original[i] = string[i];
		}
	}
	public int change(char[] target, char[] replace)
	{
		int length = target.length;
		int	target_hash = str2hash(target, length);
		int	original_hash = str2hash(original, length);
		int	i = 0;
		int	count = 0;
		while (i + length < original.length)
		{
			if (original_hash == target_hash)
			{
				for (int j = 0; j < length; j++)
					original[i + j] = replace[j];
				++count;
				i += length;
			}
			else
			{
				original_hash <<= 5;
				original_hash += (original[i] - 'a' + 1);
				++i;
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

