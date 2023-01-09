import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    private static void fill_digits(int[] array, int size, int n)
    {
        int i;

        i = size - 1;

        while (n != 0)
        {
            array[i] = n % 10;
            n /= 10;
            --i;
        }
    }
    private static int num_length(int n)
    {
        int length = 0;

        if (n == 0)
            return 1;
        while (n != 0)
        {
            n /= 10;
            ++length;
        }
        return length;
    }
    private static int solve(int target, int small, int big)
    {
        int     digits[];
        int     answer = 0;
        boolean fill_big = false;
        int     start_index= -1;

        digits = new int[num_length(target)];
        fill_digits(digits, digits.length, target);
        int i = -1;
        while (++i < digits.length)
        {
            if (fill_big)
                digits[i] = big;
            else if (digits[i] == big || digits[i] == small)
                digits[i] = digits[i] == big ? big : small;
            else if (digits[i] > small)
            {
                digits[i] = digits[i] > big ? big : small;
                fill_big = true;
            }
            else
            {
                digits[i] = -1;
                start_index = i;
            }
        }
        int j = start_index;
        while(++j < digits.length)
        {
            answer *= 10;
            answer += digits[j];
        }
        return (answer != 0 ? answer : -1);
    }

    public static void  main(String[] args) throws IOException
    {
        BufferedReader  bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int             test_cases;
        int             target;
        int             small;
        int             big;
        int             answer;

        test_cases = Integer.parseInt(bufferedReader.readLine());
        for(int test_num = 0; test_num < test_cases; test_num++)
        {
            StringTokenizer row_col = new StringTokenizer(bufferedReader.readLine(), " ");
            target = Integer.parseInt(row_col.nextToken());
            small = Integer.parseInt(row_col.nextToken());
            big = Integer.parseInt(row_col.nextToken());
            answer = solve(target, small, big);
            System.out.printf("#%d %d\n", test_num + 1, answer);
        }
    }
}
