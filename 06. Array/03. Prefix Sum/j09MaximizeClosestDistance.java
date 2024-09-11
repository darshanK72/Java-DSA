import java.util.Scanner;

public class j09MaximizeClosestDistance {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(maxDistToClosest(arr));
        System.out.println(maxDistToClosestEfficient(arr));
        System.out.println(maxDistToClosestTwoPointers(arr));
        in.close();
    }

    public static int maxDistToClosest(int[] seats) {
        int n = seats.length;
        int[] leftDist = new int[n];
        int[] rightDist = new int[n];

        int d = n;
        for (int i = 0; i < n; i++) {
            if (seats[i] == 1) {
                d = 0;
            } else {
                d++;
            }
            leftDist[i] = d;
        }

        d = n;
        for (int i = n - 1; i >= 0; i--) {
            if (seats[i] == 1) {
                d = 0;
            } else {
                d++;
            }
            rightDist[i] = d;
        }

        int maxDist = 0;
        for (int i = 0; i < n; i++) {
            maxDist = Math.max(maxDist, Math.min(leftDist[i], rightDist[i]));
        }
        return maxDist;
    }

    public static int maxDistToClosestEfficient(int[] seats) {
        int n = seats.length;
        int first = -1;
        int maxDist = 0;

        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                first = i;
                break;
            }
        }

        if (first != -1) {
            maxDist = first;
        }

        int last = first;
        for (int i = first + 1; i < seats.length; i++) {
            if (seats[i] == 1) {
                maxDist = Math.max(maxDist, (i - last) / 2);
                last = i;
            }
        }

        if (last != n - 1) {
            maxDist = Math.max(maxDist, n - 1 - last);
        }
        return maxDist;
    }

    public static int maxDistToClosestTwoPointers(int[] seats) {
        int i = -1;
        int j = -1;
        int maxDist = 0;
        for (int k = 0; k < seats.length; k++) {
            if (seats[k] == 1) {
                if (i != -1) {
                    maxDist = Math.max(maxDist, k - i);
                }
                if (j == -1) {
                    j = k;
                }
                i = k;
            }
        }

        return Math.max(maxDist / 2, Math.max(j, seats.length - i - 1));
    }
}
