import java.util.Scanner;

public class j38CelebrityProblem {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] M = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                M[i][j] = in.nextInt();
            }
        }
        System.out.println(celebrity(M, n));
        in.close();
    }

    public static int celebrity(int M[][], int n) {
        int[] mapTrust = new int[n];
        int[] mapNoTrust = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (M[i][j] == 1) {
                    mapTrust[i]++;
                    mapNoTrust[j]++;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (mapTrust[i] == 0 && mapNoTrust[i] == n - 1)
                return i;
        }
        return -1;
    }
}
