import java.util.Scanner;

public class j04ThreeWayPartitioning {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        int a = in.nextInt();
        int b = in.nextInt();
        // Call the efficient sorting method
        threeWayPartition(arr, a, b);

        // Output the sorted array
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }

        in.close();
    }

    public static void threeWayPartition(int arr[], int a, int b) {
        int i = 0;
        int j = arr.length - 1;
        int k = 0;
        while (k <= j) {
            if (arr[k] < a) {
                swap(arr, k, i);
                i++;
                k++;
            } else if (arr[k] > b) {
                swap(arr, k, j);
                j--;
            } else {
                k++;
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
