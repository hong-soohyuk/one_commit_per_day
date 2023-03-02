import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class UserSolution {
	Stack<int[]>	operand;
	Stack<Character>	operator;
	Map<String, Integer> count;
	Map<Integer, int[]> storage;

	void init() {
		operand = new Stack<>();
		operator = new Stack<>();
		count = new HashMap<>();
		storage = new HashMap<>();
	}

	public Boolean priority_ops(char pre_ops, char new_ops)
	{
		if(pre_ops == '(')
			return true;

		if(pre_ops=='*' && new_ops=='(')
			return true;

		if(new_ops=='*' || new_ops=='(')
			return true;

		return false;
	}

	public void calculate()
	{
		char ops = this.operator.pop();
		int[] data1 = this.operand.pop();
		int[] data2 = this.operand.pop();
		int[] result = new int[10];

		if(ops == '+')
			for(int i=0;i<10;i++)
				result[i] = data1[i]+data2[i];
		else if(ops=='-')
			for(int i=0;i<10;i++)
				result[i] = data2[i]-data1[i];
		else if(ops=='*')
		{
			result[9] = data1[9] * data2[0] + data2[9] * data1[0];
			result[8] = data1[8] * data2[0] + data2[8] * data1[0];
			result[7] = data1[7] * data2[0] + data2[7] * data1[0];
			result[6] = data1[9] * data2[8] + data1[8] * data2[9];
			result[5] = data1[9] * data2[7] + data1[7] * data2[9];
			result[4] = data1[8] * data2[7] + data1[7] * data2[8];
			result[3] = data1[9] * data2[9];
			result[2] = data1[8] * data2[8];
			result[1] = data1[7] * data2[7];
			result[0] = data1[0] * data2[0];
		}
		this.operand.add(result);
	}


	int addExpression(int mID, char mExpression[]) {

		for(int i = 0; i < mExpression.length; i++)
		{
			char c = mExpression[i];
			if(c == '\0')
				break;
			int [] temp = new int[10];
			Arrays.fill(temp,0);

			if('0' <= c && c<='9') {
				int num = Character.getNumericValue(mExpression[i]);
				temp[0] = num;
				this.operand.push(temp);
				continue;
			}
			if('A' <= c && c<='C'){
				int index = 'C'-c+7;
				temp[index] = 1;
				this.operand.push(temp);
				continue;
			}

			//연산자 일경우
			if(operator.isEmpty())
				operator.push(c);
			else {
				if(c==')') {
					while (this.operator.peek()!='(')
						calculate();
					this.operator.pop();
					continue;
				}

				if (priority_ops(this.operator.peek(), c))
					this.operator.push(c);
				else
				{
					calculate();
					i--;
				}
			}
		}

		while (!this.operator.isEmpty())
			calculate();
		int answer = 0;
		int[] result = this.operand.pop();

		String key = "";
		for(int i = 9; i >= 0; i--)
			key += String.valueOf(result[i]);

		if(count.get(key)!=null)
		{
			answer = count.get(key);
			count.replace(key, answer+1);
		}
		else
		{
			if (result[9] != 0 || result[6] != 0 || result[5] != 0 || result[3] != 0)
				answer -= 1;
			if(result[8] != 0 || result[6] != 0 || result[4] != 0 || result[2] != 0)
				answer -= 1;
			if(result[7] != 0 || result[5] != 0 || result[4] != 0 || result[1] != 0)
				answer -= 1;
			count.put(key, 1);
		}
		storage.put(mID, result);
		return answer;
	}

	int calcExpression(int mID, int mA, int mB, int mC) {

		int	answer = 0;
		int	result[] = storage.get(mID);

		answer += (result[9] * mA);
		answer += (result[8] * mB);
		answer += (result[7] * mC);
		answer += (result[6] * mA*mB);
		answer += (result[5] * mA*mC);
		answer += (result[4] * mB*mC);
		answer += (result[3] * mA*mA);
		answer += (result[2] * mB*mB);
		answer += (result[1] * mC*mC);
		answer += result[0];
		return answer;
	}
}
