import java.util.Scanner;

public class j09XorOfAllSubarrays {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] A = new int[N];
        for(int i = 0; i < N; i++){
            A[i] = in.nextInt();
        }
        System.out.println(gameOfXor(N, A));
        in.close();
    }
    public static int gameOfXor(int N, int[] A) {
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            int occ = i * (N - i + 1);
            if (occ % 2 == 1) {
                ans ^= A[i - 1];
            }
        }
        return ans;
    }
}
