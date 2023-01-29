package solution;

import java.util.*;

public class UserSolution
{
	List<List<Integer>>	childList;
	int					return_val;
	int					current;
	Stack<Integer>		stack;
	public void dfs_init(int N, int[][] path)
	{
		childList = new ArrayList<List<Integer>>(100);
		return_val = -1;
		stack = new Stack<>();
		for (int i = 0; i < 100; i++)
			childList.add(new ArrayList<>());
		for (int[] relation : path)
			childList.get(relation[0]).add(relation[1]);
	}

	public int dfs(int N)
	{
		if (childList.get(N).isEmpty())
			return (-1);
		return_val = -1;
		stack.clear();
		stack.push(N);
		while (!stack.isEmpty() && return_val == -1)
		{
			int poped = stack.pop();
			if (poped > N)
				return_val = poped;
			List<Integer> dfs_loop = childList.get(poped); 
			for (int i = dfs_loop.size() - 1; i >= 0; i--)
				stack.push(dfs_loop.get(i));
		}
		return (return_val);
	}
}
