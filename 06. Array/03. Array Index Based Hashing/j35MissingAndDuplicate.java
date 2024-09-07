import java.util.Scanner;

public class j35MissingAndDuplicate {
     public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int[] ans = repeatAndMissingIndexBasedHashing(arr);
        System.out.println("Missing : " + ans[0] + "\nDuplicate : " + ans[1]);
        in.close();
    }

    public static int[] repeatAndMissingIndexBasedHashing(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int original = arr[i] % (arr.length + 1);
            arr[original - 1] += (arr.length + 1);
        }
        int[] out = new int[2];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] / (arr.length + 1) == 0)
                out[1] = i + 1;
            if (arr[i] / (arr.length + 1) > 1)
                out[0] = i + 1;
        }
        return out;
    }

    public static int[] repeatAndMissingBitManipulation(int[] arr) {
        int xor = 0;
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
        }

        for (int i = 1; i <= arr.length; i++) {
            xor ^= i;
        }

        int rd = xor & -xor;
        int first = 0;
        int second = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & rd) == 0) {
                first ^= arr[i];
            } else {
                second ^= arr[i];
            }
        }
        for (int i = 1; i <= arr.length; i++) {
            if ((i & rd) == 0) {
                first ^= i;
            } else {
                second ^= i;
            }
        }

        return new int[] { first, second };
    }
}
