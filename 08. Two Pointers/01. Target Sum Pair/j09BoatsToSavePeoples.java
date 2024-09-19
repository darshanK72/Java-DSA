import java.util.Arrays;
import java.util.Scanner;

public class j09BoatsToSavePeoples {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int limit = in.nextInt();
        System.out.println(countRescueBoats(arr, limit));
        System.out.println(countRescueBoatsEfficient(arr, limit));
        in.close();
    }

    public static int countRescueBoats(int[] people, int limit) {
        boolean[] used = new boolean[people.length];
        int boats = 0;
        Arrays.sort(people);
        for (int i = 0; i < people.length; i++) {
            if (!used[i]) {
                boats++;
                used[i] = true;
                int maxFit = -1;
                // Try to pair this person with someone else
                for (int j = 0; j < people.length; j++) {
                    if (!used[j] && people[i] + people[j] <= limit) {
                        maxFit = j;
                    }
                }
                // Mark the person as used if we found a pair
                if (maxFit != -1) {
                    used[maxFit] = true;
                }
            }
        }
        return boats;
    }

    public static int countRescueBoatsEfficient(int[] people, int limit) {
        Arrays.sort(people);
        int s = 0;
        int e = people.length - 1;
        int count = 0;
        while (s <= e) {
            int sum = people[s] + people[e];
            if (sum <= limit) {
                s++;
            }
            e--;
            count++;
        }
        return count;
    }
}
