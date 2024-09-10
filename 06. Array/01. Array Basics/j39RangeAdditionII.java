import java.util.Scanner;

public class j39RangeAdditionII {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int q = in.nextInt();
        int[][] ops = new int[q][2];
        for(int i = 0; i < q; i++){
            ops[i][0] = in.nextInt();
            ops[i][1] = in.nextInt();
        }
        System.out.println(maxCount(m, n, ops));
        in.close();
    }

    public static int maxCount(int m, int n, int[][] ops) {
        int minM = Integer.MAX_VALUE;
        int minN = Integer.MAX_VALUE;
        for (int i = 0; i < ops.length; i++) {
            minM = Math.min(ops[i][0], minM);
            minN = Math.min(ops[i][1], minN);
        }
        if (ops.length == 0)
            return m * n;
        return minM * minN;
    }
}
