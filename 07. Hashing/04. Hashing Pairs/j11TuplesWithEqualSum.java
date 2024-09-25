import java.util.HashMap;
import java.util.Scanner;

public class j11TuplesWithEqualSum {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(findPairs(arr));
        in.close();
    }

    public static boolean findPairs(int arr[]) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int sum = arr[i] + arr[j];
                if (map.containsKey(sum))
                    return true;
                else {
                    map.put(sum, 1);
                }
            }
        }
        return false;
    }
}
