import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Solution {
	static boolean digits[] = new boolean[10];
	
	static void	check_digit(int number, boolean digits[])
	{
		if (number == 0)
		{
			digits[0] = true;
			return ;
		}
		while (number > 0)
		{
			digits[number % 10] = true;
			number /= 10;
			
		}
	}
	
	static boolean is_all_checked(boolean digits[]) 
	{
		for(boolean digit : digits)
			if (digit == false)
				return (false);
		return (true);
	}
	
	public static void main(String[] argments) throws Exception
	{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		int				test_case = Integer.parseInt(br.readLine());
		int				number;
		int				i = -1;
		int				multies;

		while (++i < test_case)
		{
			Arrays.fill(digits, false);
			multies = 1;
			number = Integer.parseInt(br.readLine());
			while (is_all_checked(digits) == false)
			{
				check_digit(number * multies, digits);
				multies++;
			}
			System.out.printf("#%d %d\n", i+1, number * (multies - 1));
		}
	}
}
