private static void swap_element(int[] array, int i, int j)
{
	int	temp = array[i];
	array[i] = array[j];
	array[j] = temp;
}

private static void quick_sort(int[] array, int left, int right)
{
	int	i;
	int j;
	int	pivot;
	while (left < right)
	{
		pivot = left;
		i = left;
		j = right;
		while (i < j)
		{
			while (array[i] <= array[pivot] && i < right)
				++i;
			while (array[j] < array[pivot])
				++j;
			if (i < j)
				swap_element(array, i, j);
		}
		swap_element(array, j, pivot);
		quick_sort(array, left, j - 1);
		quick_sort(array, j + 1, right);
	}
}

