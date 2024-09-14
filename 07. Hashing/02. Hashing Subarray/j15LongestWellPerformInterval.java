import java.util.HashMap;
import java.util.Scanner;

public class j15LongestWellPerformInterval {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] hours = new int[n];
        for (int i = 0; i < n; i++) {
            hours[i] = in.nextInt();
        }
        System.out.println(longestWPI(hours));
        System.out.println(longestWPIHashMap(hours));
        in.close();
    }

    // O(N^2)
    public static int longestWPI(int[] hours) {
        int maxL = 0;
        for (int i = 0; i < hours.length; i++) {
            int count = 0;
            for (int j = i; j < hours.length; j++) {
                count += hours[j] > 8 ? 1 : -1;
                if (count > 0) {
                    maxL = Math.max(maxL, j - i + 1);
                }
            }
        }
        return maxL;
    }

    // O(N)
    public static int longestWPIHashMap(int[] hours) {
        int count = 0;
        int maxL = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < hours.length; i++) {
            count += hours[i] > 8 ? 1 : -1;
            if (count > 0) {
                maxL = i + 1;
            } else {
                if (map.containsKey(count - 1)) {
                    maxL = Math.max(maxL, i - map.get(count - 1));
                }
                if (!map.containsKey(count)) {
                    map.put(count, i);
                }
            }
        }
        return maxL;
    }
}
