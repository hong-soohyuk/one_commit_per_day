# include <unistd.h>
# include <stdlib.h>
# include <stdio.h>

void	print_result(int result[], int size)
{
	char	c;
	int		i;
	
	i = size;
	while (--i > -1)
	{
		if (result[i] != -1)
		{
		c = result[i] + '0';
		write(1, &c, 1);
		}
	}
}

int	str_length(char *s)
{
	int	i;

	i = 0;
	while (s[i])
		i++;
	return (i);
}

int	*sum_of_residue(char *residue, int left, int i, int *result)
{
	int	carry;

	carry = result[i] == -1 ? 0 :  result[i];
	while (left > -1)
	{
		result[i] = ((residue[left] - '0') + carry);
		if (result[i] > 9)
		{
			result[i] -= 10;
			carry = 1;
		}
		else
			carry = 0;
		left--;
		i++;
	}
	if (carry)
		result[i] = carry;
	return (result);
}

int	*sum_of_two(char *a, int a_length, char *b, int b_length, int *result)
{
	int	i;
	int	carry;

	i = 0;
	carry = 0;
	a_length -= 1;
	b_length -= 1;
	while (a_length > -1 && b_length > -1)
	{
		result[i] = (a[a_length] - '0') + (b[b_length] - '0') + carry;
		if (result[i] > 9)
		{
			result[i] -= 10;
			carry = 1;
		}
		else
			carry = 0;
		a_length--;
		b_length--;
		i++;
	}
	if (carry)
		result[i] = carry;
	if (a_length > -1)
		return (sum_of_residue(a, a_length, i, result));
	else if (b_length > -1)
		return (sum_of_residue(b, b_length, i, result));
	else
		return (result);
}

void	big_sum(char *a, char *b)
{
	int	a_length;
	int	b_length;
	int	size;
	int	*result;
	int	i;

	a_length = str_length(a);
	b_length = str_length(b);
	size = a_length > b_length ? a_length + 1 : b_length + 1;
	result = (int *)malloc(sizeof(int) * size);
	i = -1;
	while (++i < size)
		result[i] = -1;
	result = sum_of_two(a, a_length, b, b_length, result);
	print_result(result, size);
}

int main(void)
{
	char a[10002] = {0};
	char b[10002] = {0};
	scanf("%s %s", a, b);
	big_sum(a, b);
	return (0);
}
