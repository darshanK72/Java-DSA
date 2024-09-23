import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class j05CountTripletsOneEqualTwoSum {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(countTriplet(arr, n));
        in.close();
    }

    public static int countTriplet(int arr[], int n) {
        HashSet<Integer> set = new HashSet<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            set.add(arr[i]);
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (set.contains(arr[i] + arr[j])) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int countTripletEfficient(int arr[], int n) {
        Arrays.sort(arr);
        int count = 0;
        int i = n - 1;
        while (i >= 0) {
            int s = 0;
            int e = i - 1;
            while (s < e) {
                int sum = arr[s] + arr[e];
                if (sum == arr[i]) {
                    count++;
                    s++;
                    e--;
                } else if (sum > arr[i]) {
                    e--;
                } else {
                    s++;
                }
            }
            i--;
        }
        return count;
    }
}
