import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    private static BufferedReader br;
    private static UserSolution usersolution = new UserSolution();

    private final static int CMD_INIT = 100;
    private final static int CMD_DROP = 200;
    private final static int CMD_CLEAN = 300;

    private final static int MAX_N = 100;
    private static int mDish[][] = new int[MAX_N][MAX_N];

    private static boolean run() throws Exception {

        StringTokenizer stdin = new StringTokenizer(br.readLine(), " ");

        int query_num = Integer.parseInt(stdin.nextToken());
        int ret, ans;
        boolean ok = false;

        for (int q = 0; q < query_num; q++) {
            stdin = new StringTokenizer(br.readLine(), " ");
            int query = Integer.parseInt(stdin.nextToken());

            if (query == CMD_INIT) {
                int N = Integer.parseInt(stdin.nextToken());
                for (int i = 0; i < N; i++) {
                    stdin = new StringTokenizer(br.readLine(), " ");
                    for (int j = 0; j < N; j++) {
                        mDish[i][j] = Integer.parseInt(stdin.nextToken());
                    }
                }
                usersolution.init(N, mDish);
                ok = true;
            } else if (query == CMD_DROP) {
                int mTarget = Integer.parseInt(stdin.nextToken());
                int mRow = Integer.parseInt(stdin.nextToken());
                int mCol = Integer.parseInt(stdin.nextToken());
                int mEnergy = Integer.parseInt(stdin.nextToken());
                ans = Integer.parseInt(stdin.nextToken());
                ret = usersolution.dropMedicine(mTarget, mRow, mCol, mEnergy);
                if (ans != ret) {
                    ok = false;
                }
            } else if (query == CMD_CLEAN) {
                int mRow = Integer.parseInt(stdin.nextToken());
                int mCol = Integer.parseInt(stdin.nextToken());
                ans = Integer.parseInt(stdin.nextToken());
                ret = usersolution.cleanBacteria(mRow, mCol);
                if (ans != ret) {
                    ok = false;
                }
            }
        }
        return ok;
    }

    public static void main(String[] args) throws Exception {
        int T, MARK;

        // System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stinit = new StringTokenizer(br.readLine(), " ");
        T = Integer.parseInt(stinit.nextToken());
        MARK = Integer.parseInt(stinit.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            int score = run() ? MARK : 0;
            System.out.println("#" + tc + " " + score);
        }

        br.close();
    }
}
