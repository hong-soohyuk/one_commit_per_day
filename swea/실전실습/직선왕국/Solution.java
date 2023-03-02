import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

class Solution {
	private static Scanner sc;
	private static UserSolution usersolution = new UserSolution();

	static final int CMD_INIT = 100;
	static final int CMD_DESTROY = 200;
	static final int CMD_ORDER = 300;
	static final int CMD_CHECK = 400;

	private static int run() throws IOException {
		int isOK = 0;
		int C = sc.nextInt();
		int cmd, result, check;

		int mN, mM;
		int mTStamp;
		int mCityA, mCityB, mTax;

		for (int c = 0; c < C; ++c) {

			cmd =  sc.nextInt();
			switch (cmd) {
			case CMD_INIT:
				mN = sc.nextInt();
				mM = sc.nextInt();
				usersolution.init(mN, mM);
				isOK = 1;
				break;

			case CMD_ORDER:
				mTStamp = sc.nextInt();
				mCityA = sc.nextInt();
				mCityB = sc.nextInt();
				mTax = sc.nextInt();
				result = usersolution.order(mTStamp,mCityA,mCityB,mTax);
				check = sc.nextInt();
				if (result != check)
					isOK = 0;
				break;

			case CMD_CHECK:
				mTStamp = sc.nextInt();
				result = usersolution.check(mTStamp);
				check = sc.nextInt();
				if (result != check)
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
