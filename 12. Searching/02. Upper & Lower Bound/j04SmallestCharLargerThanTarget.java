import java.util.Scanner;

public class j04SmallestCharLargerThanTarget {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        char[] arr = new char[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.next().charAt(0);
        }
        char target = in.next().charAt(0);
        System.out.println(searchChar(arr, target));
        in.close();
    }

    public static char searchChar(char[] letters, char target) {
        int s = 0;
        int e = letters.length - 1;
        while (s <= e) {
            int mid = (s + e) / 2;
            if (letters[mid] <= target) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        return letters[s % letters.length];
    }
}
