import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    private static BufferedReader br;
    private static final UserSolution userSolution = new UserSolution();

    private final static int CMD_INIT = 100;
    private final static int CMD_UPDATE_SCORE = 200;
    private final static int CMD_UNION_TEAM = 300;
    private final static int CMD_GET_SCORE = 400;

    private static boolean run() throws IOException
    {
        int queryCnt = Integer.parseInt(br.readLine());
        boolean okay = false;
        int res, ans;

        for (int i = 0; i < queryCnt; i++)
        {
            StringTokenizer stdin = new StringTokenizer(br.readLine());
            switch (Integer.parseInt(stdin.nextToken()))
            {
                case CMD_INIT:
                    int N = Integer.parseInt(stdin.nextToken());
                    userSolution.init(N);
                    okay = true;
                    break;
                case CMD_UPDATE_SCORE:
                    int mWinnerID = Integer.parseInt(stdin.nextToken());
                    int mLoserID = Integer.parseInt(stdin.nextToken());
                    int mScore = Integer.parseInt(stdin.nextToken());
                    userSolution.updateScore(mWinnerID, mLoserID, mScore);
                    break;
                case CMD_UNION_TEAM:
                    int mPlayerA = Integer.parseInt(stdin.nextToken());
                    int mPlayerB = Integer.parseInt(stdin.nextToken());
                    userSolution.unionTeam(mPlayerA, mPlayerB);
                    break;
                case CMD_GET_SCORE:
                    int mID = Integer.parseInt(stdin.nextToken());
                    res = userSolution.getScore(mID);
                    ans = Integer.parseInt(stdin.nextToken());
                    if (ans != res)
                    {
                        okay = false;
                    }
                    break;
            }
        }

        return okay;
    }

    public static void main(String[] args) throws IOException {
        // System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stinit = new StringTokenizer(br.readLine(), " ");

        int T, MARK;
        T = Integer.parseInt(stinit.nextToken());
        MARK = Integer.parseInt(stinit.nextToken());

        for (int tc = 1; tc <= T; tc++)
        {
            int score = run() ? MARK : 0;
            System.out.printf("#%d %d\n", tc, score);
        }

        br.close();
    }
}
