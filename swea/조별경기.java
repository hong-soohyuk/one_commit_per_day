class Player
{
    int     score;
    int     team_id;
    Player  next;
    Player(int team_id)
    {
        this.score = 0;
        this.team_id = team_id;
        this.next = null;
    }
}

class Team
{
    int     scoreDiff;
    int     team_id;
    int     player_num;
    Player  head_p;
    Team(int team_id)
    {
        this.scoreDiff = 0;
        this.team_id = team_id;
        this.player_num = 1;
        this.head_p = null;
    }
}

class UserSolution
{
    Player[]    players = new Player[100001];
    Team[]      teams = new Team[100001];
    public void init(int N)
    {
        for (int i = 1; i <= N; i++)
        {
            this.players[i] = new Player(i);
            this.teams[i] = new Team(i);
            this.teams[i].head_p = this.players[i];
        }
    }

    public void updateScore(int mWinnerID, int mLoserID, int mScore)
    {
        int winner_team = this.players[mWinnerID].team_id;
        int loser_team = this.players[mLoserID].team_id;
        this.teams[winner_team].scoreDiff += mScore;
        this.teams[loser_team].scoreDiff -= mScore;
    }

    public void unionTeam(int mPlayerA, int mPlayerB)
    {
        int a_id = this.players[mPlayerA].team_id;
        int b_id = this.players[mPlayerB].team_id;
        Team    a = this.teams[a_id];
        Team    b = this.teams[b_id];

        if (a.player_num > b.player_num)
        {// make sure that a is smaller size group
            Team swap = a;
            a = b;
            b = swap;
        }
        Player current;
        current = a.head_p;
        while (current.next != null)
        {
            current.score += (a.scoreDiff - b.scoreDiff);
            current.team_id = b.team_id;
            current = current.next;
        }
        current.score += (a.scoreDiff - b.scoreDiff);
        current.team_id = b.team_id;
        current.next = b.head_p;

        b.head_p = a.head_p;
        b.player_num += a.player_num;
    }

    public int getScore(int mID)
    {
        return this.players[mID].score + this.teams[this.players[mID].team_id].scoreDiff;
    }
}

