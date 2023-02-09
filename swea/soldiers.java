import java.util.*;

class UserSolution
{
	Map<Integer, Integer>		soldiers = new HashMap<>();
	Map<Integer, List<Integer>>	teams = new HashMap<>();

	public void init()
	{
		soldiers.clear();
		teams.clear();
		for (int i = 1; i < 6; i++)
			teams.put(i, new ArrayList<>());
	}

	public void hire(int mID, int mTeam, int mScore)
	{
		soldiers.put(mID, mScore);
		teams.get(mTeam).add(mID);
	}

	public void fire(int mID)
	{
		for (int i = 1; i < 6; i++)
		{
			if (teams.get(i).contains(mID))
			{
				int index = teams.get(i).indexOf(mID);
				teams.get(i).remove(index);
			}
		}
		soldiers.remove(mID);
	}

	public void updateSoldier(int mID, int mScore)
	{
		int	change;

		change = soldiers.get(mID);
		change = mScore;
		soldiers.put(mID, change);
	}

	public void updateTeam(int mTeam, int mChangeScore)
	{
		List<Integer>	team = teams.get(mTeam);
		int				score;
		int				change;

		for (int id : team)
		{
			if (!soldiers.containsKey(id))
				continue ;
			score = soldiers.get(id);
			change = score + mChangeScore;
			if (change > 5)
				updateSoldier(id, 5);
			else if (change < 1)
				updateSoldier(id, 1);
			else
				updateSoldier(id, change);
		}
	}
	
	public int bestSoldier(int mTeam)
	{
		List<Integer>	team = teams.get(mTeam);
		int				best = -1;
		int				returnVal = -1;

		for (int id : team)
		{
			if (!soldiers.containsKey(id))
				continue ;
			int	score = soldiers.get(id);
			if (score > best)
			{
				best = score;
				returnVal = id;
			}
			else if (score == best)
				returnVal = Math.max(returnVal, id);
		}
		return returnVal;
	}
}
