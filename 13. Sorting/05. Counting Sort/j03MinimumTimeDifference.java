
import java.util.Arrays;
import java.util.List;

public class j03MinimumTimeDifference {

    public int findMinDifferenceBruitForce(List<String> timePoints) {
        int n = timePoints.size();
        int[] minutes = new int[n];
        for (int i = 0; i < n; i++) {
            minutes[i] = getTimeInMinutes(timePoints.get(i));
        }

        Arrays.sort(minutes);

        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; i++) {
            minDiff = Math.min(minDiff, minutes[i + 1] - minutes[i]);
        }

        return Math.min(minDiff, 24 * 60 - minutes[n - 1] + minutes[0]);
    }

    public int findMinDifferenceCountingSort(List<String> timePoints) {
        boolean[] count = new boolean[24 * 60];
        for (String time : timePoints) {
            int tm = getTimeInMinutes(time);
            if (count[tm]) {
                return 0;
            }
            count[tm] = true;
        }

        int prevTime = -1;
        int firstTime = -1;
        int minDiff = Integer.MAX_VALUE;
        for (int currTime = 0; currTime < 24 * 60; currTime++) {
            if (count[currTime]) {
                if (prevTime == -1) {
                    firstTime = currTime;
                    prevTime = currTime;
                } else {
                    minDiff = Math.min(minDiff, currTime - prevTime);
                    prevTime = currTime;
                }
            }
        }

        minDiff = Math.min(minDiff, 24 * 60 + firstTime - prevTime);
        return minDiff;
    }

    public static int getTimeInMinutes(String timeStamp) {
        String[] time = timeStamp.split(":");
        int mins = Integer.parseInt(time[0]) * 60;
        mins += Integer.parseInt(time[1]);
        return mins;
    }
}
