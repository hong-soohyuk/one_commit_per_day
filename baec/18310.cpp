#include <stdio.h>
#include <stdbool.h>
#include <vector>
#include <iostream>
#include <utility>
#include <algorithm>

using namespace std;

int main(void)
{
	int			n;
	int			input;
	vector<int>	v;

	cin >> n;
	for (int i = 0; i < n; i++)
	{
		cin >> input;
		v.push_back(input);
	}
	sort(v.begin(), v.end());
	if (n % 2 == 0)
	{
		int med = (v[n / 2 - 1] + v[n / 2]) / 2;
		if (v[n / 2 - 1] - med < v[n / 2] - med)
			cout << v[n / 2 - 1] << endl;
		else
			cout << v[n / 2] << endl;
	}
	else
		cout << v[n / 2] << endl;
}
