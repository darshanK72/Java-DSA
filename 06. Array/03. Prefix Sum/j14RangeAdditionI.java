import java.util.Arrays;
import java.util.Scanner;

public class j14RangeAdditionI {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();
        int[][] updates = new int[q][3];
        for (int i = 0; i < q; i++) {
            updates[i][0] = in.nextInt();
            updates[i][1] = in.nextInt();
            updates[i][2] = in.nextInt();
        }

        System.out.println(Arrays.toString(rangeAdditionII(n, updates)));
        System.out.println(Arrays.toString(rangeAdditionIIEfficient(n, updates)));
        in.close();
    }

    public static int[] rangeAdditionII(int n, int[][] updates) {
        int[] out = new int[n];
        for (int i = 0; i < updates.length; i++) {
            for (int j = updates[i][0]; j <= updates[i][1]; j++) {
                out[j] += updates[i][2];
            }
        }
        return out;
    }

    public static int[] rangeAdditionIIEfficient(int n,int[][] updates){
        int[] out = new int[n];
        for(int i = 0; i < updates.length; i++){
            int startIndex = updates[i][0];
            int endIndex = updates[i][1];
            int inc = updates[i][2];

            out[startIndex] += inc;
            if(endIndex + 1 < n){
                out[endIndex + 1] -= inc;
            }

        }
        int sum = 0;
        for(int i = 0; i < n; i++){
            sum += out[i];
            out[i] = sum;
        }
        return out;
    }
}
