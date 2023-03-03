import java.util.*;

class UserSolution {

	class Dir
	{
		List<Dir>	children;
		int			dir_name;
		Dir			parent;
		int			size;

		Dir(int dir_name)
		{
			this.children = new LinkedList<>();
			this.dir_name = dir_name;
			this.parent = null;
			this.size = 0;
		}

		Dir		get(int find)
		{
			for (Dir child : children)
				if (child.dir_name == find)
					return (child);
			return (null);
		}

		void	mkdir(int dir_name)
		{
			Dir	new_dir = new Dir(dir_name);;
			new_dir.parent = this;
			this.children.add(new_dir);
			this.size++;
		}

		void	rm(int find)
		{
			int	remove = -1;
			for (int i = 0; i < this.size; i++)
				if (this.children.get(i).dir_name == find)
					remove = i;
			if (remove == -1)
				return ;
			this.children.remove(remove);
			this.size--;
		}

		void	add_dir(Dir dir)
		{
			dir.parent = this;
			this.children.add(dir);
			this.size++;
		}		
	}

	static Dir root;

	int	dir2int(String dir_name) {
		int	len = dir_name.length();
		int	value = 0;;
		for (int i = 0; i < len; i++)
		{
			value += (dir_name.charAt(i) - 'a' + 1);
			value = value << 5;
		}
		return (value);
	}

	void init(int n)
	{
		root = new Dir(dir2int("/"));
	}

	int	path_len(char[] path)
	{
		int	i = -1;
		int	len = 0;

		while (path[++i] != '\0')
			if (path[i] == '/')
				len++;
		return len;
	}

	String	split_path(char[] path, int start, int end)
	{
		char[]	dir;
		int		i;

		dir = new char[end - start + 1];
		i = -1;
		while ((++i)+start < end)
			dir[i] = path[i+start];
		dir[i] = '\0';
		return new String(dir);
	}

	String[] make_path(char[] path)
	{
		int	len = path_len(path) - 1;
		int	i = 0;
		int	start = 0;
		int	end = 0;
		if (len == 0)
			return (null);
		String[] ret = new String[len];
		while (i < len)
		{
			start = end;
			while (path[start] == '/')
				start++;
			end = start;
			while (path[end] != '/')
				end++;
			if (start != end)
				ret[i++] = split_path(path, start, end);
		}
		return (ret);
	}

	void cmd_mkdir(char[] path, char[] name)
	{
		Dir			current;
		String[]	pathString = make_path(path);

		current = root;
		if (pathString != null)
			for (String dir : pathString)
				current = current.get(dir2int(dir));
		current.mkdir(dir2int(new String(name)));
	}
	
	void cmd_rm(char[] path)
	{
		Dir			current;
		int			rm_dir;
		String[]	pathString = make_path(path);

		current = root;
		if (pathString != null)
			for (String dir : pathString)
				current = current.get(dir2int(dir));
		rm_dir = current.dir_name;
		current = current.parent;
		current.rm(rm_dir);
	}
	
	void cmd_cp(char[] srcPath, char[] dstPath)
	{
		Dir			current;
		Dir			cp_dir;
		String[]	srcString = make_path(srcPath);
		String[]	dstString = make_path(dstPath);

		current = root;
		if (srcString != null)
			for (String dir : srcString)
				current = current.get(dir2int(dir));
		cp_dir = new Dir(current.dir_name);
		cp_dfs(current, cp_dir);
		current = root;
		if (dstString != null)
			for (String dir : dstString)
				current = current.get(dir2int(dir));
		current.add_dir(cp_dir);
	}
	
	void	cp_dfs(Dir src, Dir dst)
	{
		Stack<Dir>	src_dfs;
		Stack<Dir>	dst_dfs;

		src_dfs = new Stack<>();
		dst_dfs = new Stack<>();
		
		src_dfs.push(src);
		dst_dfs.push(dst);
		while (!src_dfs.isEmpty() && !dst_dfs.isEmpty())
		{
			Dir src_popped = src_dfs.pop();
			Dir	dst_popped = dst_dfs.pop();
			for (Dir child : src_popped.children)
			{
				dst_popped.mkdir(child.dir_name);
				src_dfs.push(child);
			} 
			for (Dir child : dst_popped.children)
				dst_dfs.push(child);
		}
	}
	
	void cmd_mv(char[] srcPath, char[] dstPath)
	{
		Dir		current;
		Dir		mv_dir;
		String[]	srcString = make_path(srcPath);
		String[]	dstString = make_path(dstPath);
		current = root;
		if (srcString != null)
			for (String dir : srcString)
				current = current.get(dir2int(dir));
		mv_dir = current;
		current = current.parent;
		mv_dir.parent = null;
		current.rm(mv_dir.dir_name);
		current = root;
		if (dstString != null)
			for (String dir : dstString)
				current = current.get(dir2int(dir));
		current.add_dir(mv_dir);
	}
	
	int cmd_find(char[] path)
	{
		Dir			current;
		String[]	pathString = make_path(path);
		int			ret;
		Stack<Dir>	dfs;

		current = root;
		ret = 0;
		if (pathString != null)
			for (String dir : pathString)
				current = current.get(dir2int(dir));
		dfs = new Stack<>();
		dfs.push(current);
		while (!dfs.isEmpty())
		{
			Dir popped = dfs.pop();
			ret += popped.size;
			for (Dir child : popped.children)
				dfs.push(child);
		}
		return (ret);
	}
}

