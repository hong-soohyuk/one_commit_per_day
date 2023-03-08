#include <stdbool.h>
#include <vector>
#include <iostream>
#include <utility>

using namespace std;

vector<pair<int, int> >	vec;
int			board[9][9];
bool			solved;

void	print_sudoku()
{
	for (int i = 0; i < 9; i++)
	{
		cout << board[i][0];
		for (int j = 1; j < 9; j++)
			cout << " " << board[i][j];
		cout << endl;
	}
}
void	get_promisings(int x, int y, bool dupl[10])
{
	int	i;
	int	loop_x;
	int	loop_y;
	i = -1;
	while (++i < 10)
		dupl[i] = false;
	i = -1;
	while (++i < 9)
		dupl[board[x][i]] = true;
	i = -1;
	while (++i < 9)
		dupl[board[i][y]] = true;
	loop_x = (x / 3) * 3;
	loop_y = (y / 3) * 3;
	for (int a = loop_x; a < loop_x + 3; a++)
		for (int b = loop_y; b < loop_y + 3; b++)
			dupl[board[a][b]] = true;
}

void	sudoku()
{
	bool	dupl[10];
	int	i;
	int	x;
	int	y;

	if (vec.empty())
	{
		print_sudoku();
		solved = true;
		return ;
	}
	pair<int, int>	popped = vec.back();
	vec.pop_back();
	
	x = popped.first;
	y = popped.second;
	get_promisings(x, y, dupl);
	i = 0;
	while (++i < 10)
	{
		if (dupl[i] == false)
		{
			board[x][y] = i;
			sudoku();
		}
		if (solved)
			return ;
	}
	board[x][y] = 0;
	vec.push_back(make_pair(x, y));
	return ;
}

int main()
{
	for (int i = 0; i < 9; i++)
	{
		for (int j = 0; j < 9; j++)
		{
			cin >> board[i][j];
			if (board[i][j] == 0)
				vec.push_back(make_pair(i, j));
		}
	}
	solved = false;
	sudoku();
	return (0);
}

