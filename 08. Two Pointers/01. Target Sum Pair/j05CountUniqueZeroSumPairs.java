import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class j05CountUniqueZeroSumPairs {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        System.out.println(getPairs(arr));
        in.close();
    }

    public static ArrayList<ArrayList<Integer>> getPairs(int[] arr) {
        ArrayList<ArrayList<Integer>> out = new ArrayList<>();
        Arrays.sort(arr);
        int s = 0;
        int e = arr.length - 1;
        while (s < e) {
            int sum = arr[s] + arr[e];
            if (sum == 0) {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(arr[s]);
                temp.add(arr[e]);
                out.add(temp);

                while (s < e && arr[s] == arr[s + 1])
                    s++;
                while (s < e && arr[e] == arr[e - 1])
                    e--;
                s++;
                e--;
            } else if (sum > 0) {
                e--;
            } else {
                s++;
            }
        }
        return out;
    }
}
