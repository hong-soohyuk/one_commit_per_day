import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    private static BufferedReader br;
    private static UserSolution userSolution = new UserSolution();

    private final static int CMD_INIT = 100;
    private final static int CMD_ADD = 200;
    private final static int CMD_ERASE = 300;
    private final static int CMD_FIND = 400;

    private final static int MAX_ARR_SIZE = 100000;
    private static int N;
    private static int M;
    private static int from;
    private static int to;
    private static int arr[] = new int[MAX_ARR_SIZE];

    private static boolean run() throws Exception {
        StringTokenizer stdin = new StringTokenizer(br.readLine(), " ");

        int query_num = Integer.parseInt(stdin.nextToken());
        int ret, ans;
        boolean ok = false;

        for (int q = 0; q < query_num; q++) {
            stdin = new StringTokenizer(br.readLine(), " ");
            int query = Integer.parseInt(stdin.nextToken());

            if (query == CMD_INIT) {
            	N = Integer.parseInt(stdin.nextToken());
                for (int i = 0; i < N; i++)
                {
                	arr[i] = Integer.parseInt(stdin.nextToken());
                }
                userSolution.init(N, arr);
                ok = true;
            } 
            else if (query == CMD_ADD) {
                M = Integer.parseInt(stdin.nextToken());

                for (int i = 0; i < M; i++)
                {
                	arr[i] = Integer.parseInt(stdin.nextToken());;
                }
                
                userSolution.add(M, arr);
            } 
            else if(query == CMD_ERASE) {
            	from = Integer.parseInt(stdin.nextToken());
            	to = Integer.parseInt(stdin.nextToken());
                
                userSolution.erase(from, to);
            	
            }
        	else if (query == CMD_FIND) {
                int K = Integer.parseInt(stdin.nextToken());
                ret = userSolution.find(K);
                ans = Integer.parseInt(stdin.nextToken());

	            if (ans != ret)
	            {
	                ok = false;
	            }
            }
        }
        return ok;
    }

    public static void main(String[] args) throws Exception {
        int T, MARK;

//        System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
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
