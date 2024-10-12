import java.util.Arrays;
import java.util.Scanner;

public class j09MinRadiousToHeatHouses {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] houses = new int[n];
        for (int i = 0; i < n; i++) {
            houses[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] heaters = new int[m];
        for (int i = 0; i < m; i++) {
            heaters[i] = in.nextInt();
        }
        System.out.println(findRadius(houses, heaters));
        in.close();
    }

    public static int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int minRadious = 0;
        int n = houses.length;
        for (int i = 0; i < n; i++) {
            minRadious = Math.max(minRadious, findClosestHeater(houses[i], heaters));
        }
        return minRadious;
    }

    public static int findClosestHeater(int house, int[] heaters) {
        int s = 0;
        int e = heaters.length - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (heaters[mid] > house) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        int floorDist = e == -1 ? Integer.MAX_VALUE : house - heaters[e];
        int ceilDist = s == heaters.length ? Integer.MAX_VALUE : heaters[s] - house;

        return Math.min(floorDist, ceilDist);
    }
}
