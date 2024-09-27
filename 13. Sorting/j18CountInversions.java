import java.util.Scanner;

public class j18CountInversions {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextLong();
        }
        System.out.println(inversionCount(arr));
        in.close();
    }

    static long inversionCount(long arr[]) {
        long maxTill = arr[0];
        long ans = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < maxTill) {
                long c = 0;
                int j = i - 1;
                while (j >= 0) {
                    if (arr[j] > arr[i]) {
                        c++;
                    }
                    j--;
                }
                ans += c;
            } else {
                maxTill = arr[i];
            }
        }
        return ans;
    }
}
