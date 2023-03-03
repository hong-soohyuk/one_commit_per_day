import java.io.*;
import java.util.*;
class Solution {
    static long candies[] = new long[100];

    public static void main(String[] args) throws Exception {
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder   sb = new StringBuilder();
        int		        test, T;
        T = Integer.parseInt(br.readLine());
        for (test = 1; test <= T; test++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int     N = Integer.parseInt(st.nextToken());
            long    M = Long.parseLong(st.nextToken());
            st = new StringTokenizer(br.readLine(), " ");
            long    max = 0;
            for (int i = 0; i < N; i++)
            {
                candies[i] = Long.parseLong(st.nextToken());
                max = Math.max(max, candies[i]);
            }
            long    answer = 0;
            long    start = 1;
            long    end = max;
            while (start <= end)
            {
                long    mid = start + (end - start) / 2;
                long    sum_of_candy = 0;
                for (int i = 0; i < N; i++)
                    sum_of_candy += (candies[i] / mid);
                if (sum_of_candy < M)
                    end = mid - 1;
                else
                {
                    answer = Math.max(answer, mid);
                    start = mid + 1;
                }
            }

            sb.append("#").append(test).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
        br.close();
    }
}
