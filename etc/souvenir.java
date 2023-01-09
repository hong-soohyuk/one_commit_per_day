import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    static boolean  souvenir[];
    static int      row;
    static int      column;
    static int      test_cases;
    static int      answer;

    private static void travel(boolean souvenir[] , char[][] travel_map, int x, int y, int max)
    {
        int     i;
        int[]   move_vertical = {-1, 0, 1, 0};
        int[]   move_horizontal = {0, 1, 0, -1};

        if (x < 0 || y < 0 || x >= travel_map.length || y >= travel_map[0].length)
            return ;
        int current = travel_map[x][y] - 'A';
        if (souvenir[current])
            return ;
        souvenir[current] = true;
        max++;
        answer = Math.max(max, answer);
        i = -1;
        while (++i < 4)
            travel(souvenir, travel_map, x + move_horizontal[i], y + move_vertical[i], max);
        souvenir[current] = false;
    }
    private static void test_init(boolean souvenir[], int row, int column, BufferedReader bufferedReader) throws IOException {
        char[][]    travel_map = new char[row][column];
        int         i;
        int         j;

        Arrays.fill(souvenir, false);
        answer = 0;
        i = -1;
        while (++i < row)
        {
            String read_row = bufferedReader.readLine();
            j = -1;
            while (++j < column)
                travel_map[i][j] = read_row.charAt(j);
        }
        travel(souvenir, travel_map, 0, 0, 0);
    }

    public static void  main(String[] args) throws IOException
    {
        BufferedReader  bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        test_cases = Integer.parseInt(bufferedReader.readLine());
        souvenir = new boolean[26];
        for(int test_num = 0; test_num < test_cases; test_num++)
        {
            StringTokenizer row_col = new StringTokenizer(bufferedReader.readLine(), " ");
            row = Integer.parseInt(row_col.nextToken());
            column = Integer.parseInt(row_col.nextToken());
            test_init(souvenir, row, column, bufferedReader);
            System.out.printf("#%d %d\n", test_num + 1, answer);
        }
    }
}
