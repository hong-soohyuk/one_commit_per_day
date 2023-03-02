import java.util.Scanner;

class Solution {
	private final static int CMD_INIT = 1;
	private final static int CMD_ADD = 2;
	private final static int CMD_MIN_TRAVEL_TIME = 3;
	private final static int CMD_MIN_PRICE = 4;
	
	private final static UserSolution usersolution = new UserSolution();

	private static boolean run(Scanner sc)
	{
		int numQuery;
		int N, mStartAirport, mEndAirport, mStartTime, mTravelTime, mPrice;
		int userAns, ans;

		boolean isCorrect = false;

		numQuery = sc.nextInt();

		for (int q = 0; q < numQuery; q++)
		{
			int cmd;
			cmd = sc.nextInt();

			switch (cmd)
			{
			case CMD_INIT:
				N = sc.nextInt();
				usersolution.init(N);
				isCorrect = true;
				break;
			case CMD_ADD:
				mStartAirport = sc.nextInt();
				mEndAirport = sc.nextInt();
				mStartTime = sc.nextInt();
				mTravelTime = sc.nextInt();
				mPrice = sc.nextInt();
				usersolution.add(mStartAirport, mEndAirport, mStartTime, mTravelTime, mPrice);
				break;
			case CMD_MIN_TRAVEL_TIME:
				mStartAirport = sc.nextInt();
				mEndAirport = sc.nextInt();
				mStartTime = sc.nextInt();
				userAns = usersolution.minTravelTime(mStartAirport, mEndAirport, mStartTime);
				ans = sc.nextInt();
				if (userAns != ans)
				{
					isCorrect = false;
				}
				break;
			case CMD_MIN_PRICE:
				mStartAirport = sc.nextInt();
				mEndAirport = sc.nextInt();
				userAns = usersolution.minPrice(mStartAirport, mEndAirport);
				ans = sc.nextInt();
				if (userAns != ans)
				{
					isCorrect = false;
				}
				break;
			default:
				isCorrect = false;
				break;
			}
		}
		return isCorrect;
	}

	public static void main(String[] args) throws Exception
	{
		int T, MARK;
	
		//System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		MARK = sc.nextInt();

		for (int tc = 1; tc <= T; tc++)
		{
			int score = run(sc) ? MARK : 0;
			System.out.println("#" + tc + " " + score);
		}

		sc.close();
	}
}
