public class j02PunishTheStudents {
    public static int shouldPunish(int roll[], int marks[], int n, double avg) {
        int swaps = 0;
        int marksSum = 0;
        for (int i = 0; i < n; i++)
            marksSum += marks[i];
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (roll[j] > roll[j + 1]) {
                    swap(roll, j, j + 1);
                    swaps++;
                }
            }
        }
        return ((marksSum - swaps) / n >= avg) ? 1 : 0;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
