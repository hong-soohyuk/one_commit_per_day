import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Math.abs;

public class Solution
{
    private static BufferedReader br;
    private static final UserSolution userSolution = new UserSolution();

    private final static int MAX_N = 50000;
    private final static int[] cards = new int[MAX_N * 2];
    private final static boolean[] found = new boolean[MAX_N + 1];

    private static int N;
    private static int foundCnt;
    private static boolean ok;

    public static boolean checkCards(int mIndexA, int mIndexB, int mDiff)
    {
        if (!ok || mIndexA < 0 || mIndexA >= N * 2 || mIndexB < 0 || mIndexB >= N * 2)
        {
            ok = false;
            return false;
        }

        if (abs(cards[mIndexA] - cards[mIndexB]) > mDiff)
        {
            return false;
        }

        int val = cards[mIndexA];
        if (mDiff == 0 && mIndexA != mIndexB && !found[val])
        {
            foundCnt += 1;
            found[val] = true;
        }

        return true;
    }

    private static void init()
    {
        foundCnt = 0;
        ok = true;

        for (int i = 1; i <= N; i++)
        {
            found[i] = false;
        }
    }

    private static boolean run() throws IOException
    {
        N = Integer.parseInt(br.readLine());

        StringTokenizer stdin = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++)
        {
            cards[i] = Integer.parseInt(stdin.nextToken());
        }

        init();

        userSolution.playGame(N);

        return ok && foundCnt == N;
    }

    public static void main(String[] args) throws IOException
    {
        // System.setIn(new FileInputStream("res/sample_input.txt"));
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
