import java.util.Scanner;

public class j01KokoEatingBananas {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int hours = in.nextInt();
        System.out.println(minEatingSpeed(arr, hours));
        System.out.println(minEatingSpeedEfficient(arr, hours));
        in.close();
    }

    public static int minEatingSpeed(int[] piles, int h) {
        int max = (int) 1e9;
        for (int ans = 1; ans <= max; ans++) {
            if (isPossible(piles, ans, h))
                return ans;
        }
        return -1;
    }

    public static int minEatingSpeedEfficient(int[] piles, int h) {
        int max = piles[0];
        for (int pile : piles) {
            if (pile > max)
                max = pile;
        }
        int min = 1;
        while (min <= max) {
            int mid = min + (max - min) / 2;
            boolean isPossible = isPossible(piles, mid, h);
            if (isPossible) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        return min;
    }

    public static boolean isPossible(int[] piles, int speed, int hours) {
        long ans = 0;
        for (int i = 0; i < piles.length; i++) {
            ans += (piles[i] / speed);
            if (piles[i] % speed != 0)
                ans++;
        }
        return (ans <= hours);
    }
}