import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class j05ArrayOfDoubledPairs {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(canReorderDoubled1(arr));
        System.out.println(canReorderDoubled2(arr));
        in.close();
    }

    public static boolean canReorderDoubled1(int[] arr) {
        Arrays.sort(arr);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        for (int num : arr) {
            if (map.get(num) == 0)
                continue;
            int target = num < 0 ? num / 2 : num * 2;
            if (num < 0 && num % 2 != 0 || map.getOrDefault(target, 0) == 0) {
                return false;
            }

            map.put(num, map.get(num) - 1);
            map.put(target, map.get(target) - 1);

        }
        return true;
    }

    public static boolean canReorderDoubled2(int[] arr) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        for (int num : map.keySet()) {
            if (map.get(num) == 0)
                continue;
            int target = num < 0 ? num / 2 : num * 2;
            if (num < 0 && num % 2 != 0) {
                return false;
            }
            if (map.get(num) > map.getOrDefault(target, 0)) {
                return false;
            }
            map.put(target, map.get(target) - map.get(num));
        }
        return true;
    }
}
