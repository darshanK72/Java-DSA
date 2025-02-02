public class j02SortAnyDates{
    public static int[][] sortBigListDates(int[][] is) {
		countingSort(is,0,31,1);
		countingSort(is,1,12,1);
		countingSort(is,2,100,2000);
		return is;	
	}

	public static void countingSort(int[][] dates, int index, int range, int minValue) {
        int n = dates.length;
        int[] count = new int[range + 1];
        int[][] output = new int[n][3];

        // Count occurrences
        for (int i = 0; i < n; i++) {
            count[dates[i][index] - minValue]++;
        }

        // Convert to prefix sum (cumulative count)
        for (int i = 1; i <= range; i++) {
            count[i] += count[i - 1];
        }

        // Build output array (sorted)
        for (int i = n - 1; i >= 0; i--) {
            int value = dates[i][index] - minValue;
            output[count[value] - 1] = dates[i];
            count[value]--;
        }

        // Copy sorted values back to original array
        for (int i = 0; i < n; i++) {
            dates[i] = output[i];
        }
    }

}