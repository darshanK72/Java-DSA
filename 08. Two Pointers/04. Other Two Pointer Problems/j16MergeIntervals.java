import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class j16MergeIntervals {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = in.nextInt();
            arr[i][1] = in.nextInt();
        }

        System.out.println(Arrays.toString(mergeIntervals1(arr)));
        System.out.println(Arrays.toString(mergeIntervals2(arr)));
        in.close();
    }

    public static int[][] mergeIntervals1(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        ArrayList<Integer[]> list = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int first = intervals[i][0];
            int second = intervals[i][1];
            int j = i + 1;
            while (j < intervals.length && intervals[j][0] <= second) {
                second = Math.max(second, intervals[j][1]);
                j++;
            }
            Integer[] temp = new Integer[] { first, second };
            list.add(temp);
            i = j - 1;
        }

        int[][] out = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            out[i] = new int[] { list.get(i)[0], list.get(i)[1] };
        }
        return out;
    }

    public static int[][] mergeIntervals2(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int first = intervals[i][0];
            int second = intervals[i][1];
            int j = i + 1;
            while (j < intervals.length && intervals[j][0] <= second) {
                second = Math.max(second, intervals[j][1]);
                j++;
            }
            int[] temp = new int[] { first, second };
            list.add(temp);
            i = j - 1;
        }

        return list.toArray(new int[list.size()][]);
    }
}
