import java.util.Scanner;

public class j14MinimizeMaxProdDistributionToStore {

    public static void main(String args[]) {
        // Input reading
        Scanner in = new Scanner(System.in);
        int k = in.nextInt(); // Number of elements in the array
        int[] quantities = new int[k];

        for (int i = 0; i < k; i++) {
            quantities[i] = in.nextInt();
        }
        int n = in.nextInt(); // Number of stores

        // Output the result of the approach
        System.out.printf("Minimized Maximum products : ", minimizedMaximum(n,quantities));
        // Closing the input scanner
        in.close();
    }
    
    public static int minimizedMaximum(int n, int[] quantities) {
        int s = 1;
        int e = Integer.MIN_VALUE;
        for (int i = 0; i < quantities.length; i++) {
            e = Math.max(e, quantities[i]);
        }
        int ans = -1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (isPossible(n, quantities, mid)) {
                ans = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return ans;
    }

    public static boolean isPossible(int n, int[] quantities, int prod) {
        int count = 0;
        for (int i = 0; i < quantities.length; i++) {
            count += quantities[i] / prod;
            if (quantities[i] % prod != 0)
                count++;
        }

        return (count <= n);
    }
}
