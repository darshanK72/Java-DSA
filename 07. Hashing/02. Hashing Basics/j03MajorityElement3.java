import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class j03MajorityElement3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(majorityElement3BruitForce(arr, k));
        System.out.println(majorityElement3Efficient(arr, k));
        in.close();
    }

    // O(n^2)
    public static int majorityElement3BruitForce(int[] arr, int k) {
        boolean[] visited = new boolean[arr.length];
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int tempCount = 0;
            for (int j = 0; j < arr.length; j++) {
                if (!visited[j] && arr[j] == arr[i]) {
                    tempCount++;
                    visited[j] = true;
                }
            }
            if (tempCount > arr.length / k) {
                count++;
            }
        }
        return count;
    }

    // TC : O(n) SC : O(n)
    public static ArrayList<Integer> majorityElement3UsingHashing(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], 0);
            }
            map.put(arr[i], map.get(arr[i]) + 1);
        }
        ArrayList<Integer> output = new ArrayList<Integer>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > arr.length / k) {
                output.add(entry.getKey());
            }
        }
        return output;
    }

    // O(n * logn)
    public static int majorityElement3Efficient(int[] arr, int k) {
        Arrays.sort(arr);
        int count = 0;
        int i = 0;
        while (i < arr.length) {
            int c = 0;
            int ele = arr[i];
            while (i < arr.length && arr[i] == ele) {
                c++;
                i++;
            }
            if (c > (arr.length / k))
                count++;
        }
        return count;
    }
}