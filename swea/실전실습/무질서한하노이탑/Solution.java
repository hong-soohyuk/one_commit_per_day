import java.io.IOException;
import java.util.Scanner;

class Solution {
    private static Scanner sc;
    private static UserSolution usersolution = new UserSolution();
    static final int MAX_N = 1000;
    static final int CMD_INIT = 100;
    static final int CMD_DESTROY = 200;
    static final int CMD_GO = 300;
    static int [][] mDisk = new int [3][MAX_N];

    private static int run() throws IOException {
        int isOK = 0;
        int N = sc.nextInt();
        int cmd;

        for (int c = 0; c < N; ++c) {
            cmd =  sc.nextInt();
            switch (cmd) {
            case CMD_INIT:
                int [] mCount = new int[3];
                mCount[0] = sc.nextInt();
                mCount[1] = sc.nextInt();
                mCount[2] = sc.nextInt();
                for (int j = 0; j < 3; j++)
                    for(int i=0;i<mCount[j];i++)
                        mDisk[j][i] = sc.nextInt();
                usersolution.init(mCount,mDisk);
                isOK = 1;
                break;
            case CMD_GO:
                int mK = sc.nextInt();
                int [] result = new int[3];
                result[0] = result[1] = result[2] = -1;
                usersolution.go(mK, result);
                int [] check = new int[3];
                check[0] = sc.nextInt();
                check[1] = sc.nextInt();
                check[2] = sc.nextInt();
                if (result[0] != check[0] || result[1] != check[1] || result[2] != check[2])
                    isOK = 0;
                break;
            default:
                isOK = 0;
                break;
            }
        }
        usersolution.destroy();
        return isOK;
    }

    public static void main(String[] args) throws Exception {
        int T, MARK;
        //System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
        sc = new Scanner(System.in);
        T = sc.nextInt();
        MARK = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            if (run() == 1)
                System.out.println("#" + tc + " " + MARK);
            else
                System.out.println("#" + tc + " 0");
        }
        sc.close();
    }
}
