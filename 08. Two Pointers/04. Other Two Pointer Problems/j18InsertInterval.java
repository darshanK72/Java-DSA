import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class j18InsertInterval {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = in.nextInt();
            arr[i][1] = in.nextInt();
        }

        int[] newInterval = new int[2];
        newInterval[0] = in.nextInt();
        newInterval[1] = in.nextInt();

        System.out.println(Arrays.toString(insertInterval(arr, newInterval)));
        in.close();
    }

    public static int[][] insertInterval(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0)
            return new int[][] { newInterval };
        ArrayList<int[]> list = new ArrayList<>();
        int i = 0;
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            list.add(intervals[i]);
            i++;
        }

        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        list.add(newInterval);

        while (i < intervals.length) {
            list.add(intervals[i]);
            i++;
        }
        return list.toArray(new int[list.size()][]);
    }
}
