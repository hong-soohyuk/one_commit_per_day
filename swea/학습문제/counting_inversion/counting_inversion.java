import java.io.*;
import java.util.*;
class Solution {

    static long answer;
    static int  array[] = new int[100002];
    static int  temp[] = new int[100002];

    public static void main(String[] args) throws Exception {
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder   sb = new StringBuilder();
        int		        test, T;
        T = Integer.parseInt(br.readLine());
        for (test = 1; test <= T; test++)
        {
            answer = 0;
            int length = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < length; i++)
                array[i] = Integer.parseInt(st.nextToken());
            merge_sort(array, 0, length - 1);
            sb.append("#").append(test).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
        br.close();
    }

    private static void merge_sort(int[] array, int start, int end)
    {
        if (start >= end)
            return ;

        int mid = (start + end) >> 1;
        merge_sort(array, start, mid);
        merge_sort(array, mid + 1, end);

        int left = start;
        int right = mid + 1;
        int index = start;
        while (left <= mid || right <= end)
        {
            if (right > end || (left <= mid && array[left] < array[right]))
                temp[index++] = array[left++];
            else
            {
                answer += (mid - left + 1);
                temp[index++] = array[right++];
            }
        }
        for (int i = start; i <= end; i++)
            array[i] = temp[i];
    }
}
