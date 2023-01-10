import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
  private static String solve(String target, int small, int big) {
    int target_length = target.length();
    int digits[] = new int[target_length];
    boolean fill_big = false;
    int start_index = -1;
    int i = -1;
    StringBuilder answer = new StringBuilder();

    while (++i < digits.length) {
      int digit = target.charAt(i) - '0';
      if (fill_big)
        digits[i] = big;
      else if (digit == big || digit == small)
        digits[i] = digit == big ? big : small;
      else if (digit > small) {
        digits[i] = digit > big ? big : small;
        fill_big = true;
      } else {
        int start_over = i - 1;
        while (start_over > -1 && digits[start_over] != big)
          start_over--;
        if (start_over == -1) {
          while (++start_over < target_length - 1)
            answer.append(big);
          return answer.length() == 0 ? "-1" : answer.toString();
        } else {
          digits[start_over] = small;
          i = start_over;
        }
        fill_big = true;
      }
    }
    int j = start_index;
    if (j == target_length - 1)
      return "-1";
    while (++j < target_length)
      answer.append(digits[j]);
    j = 0;
    while (j < answer.length() && answer.charAt(j) == '0')
      j++;
    if (j == answer.length())
      return "-1";
    return answer.substring(j).toString();
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    int test_cases;
    String target;
    int small;
    int big;
    test_cases = Integer.parseInt(bufferedReader.readLine());
    StringBuilder sb = new StringBuilder();
    for (int test_num = 0; test_num < test_cases; test_num++) {
      StringTokenizer row_col = new StringTokenizer(bufferedReader.readLine(), " ");
      target = row_col.nextToken();
      small = Integer.parseInt(row_col.nextToken());
      big = Integer.parseInt(row_col.nextToken());
      sb.append("#").append((test_num + 1)).append(" ");
      sb.append(solve(target, small, big)).append("\n");
    }
    System.out.print(sb);
  }
}

