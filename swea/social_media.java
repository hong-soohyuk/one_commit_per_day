import java.util.*;
class Post
{
	int pid;
	int time_stamp;
	int	like;
	Set<Integer> visible_to_them;

	Post(int pid, int time_stamp) {
		this.pid = pid;
		this.time_stamp = time_stamp;
		this.visible_to_them = new HashSet<>();
		this.like = 0;
	}
}

class UserSolution {
	Map<Integer, List<Integer>>	follow_table;
	Map<Integer, List<Post>>	user_posts;
	Map<Integer, Post>			posts;
	public void init(int N)
	{
		this.follow_table = new HashMap<>();
		this.user_posts = new HashMap<>();
		this.posts = new HashMap<>();
		for (int i = 1; i <= N; i++)
		{
			follow_table.put(i, new ArrayList<>());
			follow_table.get(i).add(i);
			user_posts.put(i, new ArrayList<>());
		}
	}

	public void follow(int uID1, int uID2, int timestamp)
	{
		this.follow_table.get(uID1).add(uID2);
	}

	public void makePost(int uID, int pID, int timestamp)
	{
		Post	post = new Post(pID, timestamp);
		this.posts.put(pID, post);
		this.user_posts.get(uID).add(post);
	}

	public void like(int pID, int timestamp)
	{
		this.posts.get(pID).like++;
	}

	public void getFeed(int uID, int timestamp, int pIDList[])
	{
		PriorityQueue<Post>	in_k = new PriorityQueue<>((o1, o2) -> {
			if (o1.like == o2.like) return (o2.time_stamp - o1.time_stamp);
			else return (o2.like - o1.like);
		});
		PriorityQueue<Post>	over_k = new PriorityQueue<>((o1, o2) -> (o2.time_stamp - o1.time_stamp));
		for (int following : this.follow_table.get(uID))
			for (Post post : user_posts.get(following))
				if (timestamp - post.time_stamp <= 1000) in_k.add(post);
				else over_k.add(post);
		int index = 0;
		while (!in_k.isEmpty() && index < 10) pIDList[index++] = in_k.poll().pid;
		while (!over_k.isEmpty() && index < 10) pIDList[index++] = over_k.poll().pid;
	}
}

