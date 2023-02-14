import java.io.*;
import java.util.*;
class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter  bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder   sb = new StringBuilder();
        int		        test, T;
        int             dp[][];
        T = Integer.parseInt(br.readLine());
        for (test = 1; test <= T; test++)
        {
            String managers = br.readLine();
            dp = new int[managers.length()][16];

            int first_key = 1 << managers.charAt(0) - 'A'; // A: 1, B: 10, C: 100, D: 1000
            for (int i = 1; i < 16; i++)
                if ((first_key & i) != 0 && (i & 1) != 0)
                    dp[0][i] = 1;
            for (int i = 1; i < managers.length(); i++)
                other_day(managers, dp, i);
            int answer = 0;
            for (int i = 1; i < 16; i++)
            {
                answer += dp[dp.length - 1][i];
                answer %= 1000000007;
            }
            bw.write("#" + test + " " + answer + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }

    private static void other_day(String managers, int[][] dp, int day)
    {
        int key = 1 << managers.charAt(day) - 'A';
        for (int i = 1; i < 16; i++)
        {
            if (dp[day - 1][i] != 0)
            {
                for (int j = 1; j < 16; j++)
                {
                    if ((i & j) != 0 && (key & j) != 0)
                    {
                        dp[day][j] += dp[day - 1][i];
                        dp[day][j] %= 1000000007;
                    }
                }
            }
        }
    }
}
