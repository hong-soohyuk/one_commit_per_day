import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    private static BufferedReader br;
    private static UserSolution usersolution = new UserSolution();

    private final static int CMD_INIT = 100;
    private final static int CMD_ADD = 200;
    private final static int CMD_CALC = 300;

    private final static int MAX_LEN = 200;

    private static void String2Char(String src, char[] dest) {
        int len = src.length();
        for (int i = 0; i < len; i++)
            dest[i] = src.charAt(i);
        for (int i = len; i <= MAX_LEN; i++)
            dest[i] = '\0';
    }

    private static boolean run() throws Exception {

        StringTokenizer stdin = new StringTokenizer(br.readLine(), " ");

        int query_num = Integer.parseInt(stdin.nextToken());
        int ret, ans;
        boolean ok = false;
        char mStr[] = new char[MAX_LEN+1];

        for (int q = 0; q < query_num; q++) {
            stdin = new StringTokenizer(br.readLine(), " ");
            int query = Integer.parseInt(stdin.nextToken());

            if (query == CMD_INIT) {
                usersolution.init();
                ok = true;
            } else if (query == CMD_ADD) {
                int mID = Integer.parseInt(stdin.nextToken());
                String str = stdin.nextToken();
                String2Char(str, mStr);
                ans = Integer.parseInt(stdin.nextToken());
                ret = usersolution.addExpression(mID, mStr);
                if (ans != ret) {
                    ok = false;
                }
            } else if (query == CMD_CALC) {
                int mID = Integer.parseInt(stdin.nextToken());
                int mA = Integer.parseInt(stdin.nextToken());
                int mB = Integer.parseInt(stdin.nextToken());
                int mC = Integer.parseInt(stdin.nextToken());
                ans = Integer.parseInt(stdin.nextToken());
                ret = usersolution.calcExpression(mID, mA, mB, mC);
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
