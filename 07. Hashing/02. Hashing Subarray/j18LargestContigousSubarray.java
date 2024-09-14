import java.util.HashSet;
import java.util.Scanner;

public class j18LargestContigousSubarray {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(largestContigousSubarray(arr));
        in.close();
    }

    public static int largestContigousSubarray(int[] arr) {
        int maxL = 0;
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int max = arr[i];
            HashSet<Integer> set = new HashSet<>();
            set.add(arr[i]);
            for (int j = i + 1; j < arr.length; j++) {
                if (set.contains(arr[j]))
                    break;
                else {
                    set.add(arr[j]);
                    max = Math.max(max, arr[j]);
                    min = Math.min(min, arr[j]);
                    if (max - min == j - i) {
                        maxL = Math.max(maxL, j - i + 1);
                    }
                }
            }
        }
        return maxL;
    }
}
