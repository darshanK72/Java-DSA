import java.util.Arrays;
import java.util.Scanner;

public class j03SpiralMatrixIII {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int rows = in.nextInt();
        int cols = in.nextInt();
        int rStart = in.nextInt();
        int cStart = in.nextInt();
        int[][] mat = spiralMatrixIII(rows, cols, rStart, cStart);
        for (int[] arr : mat) {
            System.out.println(Arrays.toString(arr));
        }

        in.close();
    }

    public static int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int[][] out = new int[rows * cols][2];
        int dir = 0;
        int len = 0;
        int i = 0;
        while (i < rows * cols) {
            if (dir == 0 || dir == 2)
                len++;
            for (int k = 0; k < len; k++) {
                if (rStart >= 0 && rStart < rows && cStart >= 0 && cStart < cols) {
                    out[i++] = new int[] { rStart, cStart };
                }
                if (dir == 0)
                    cStart++;
                else if (dir == 1)
                    rStart++;
                else if (dir == 2)
                    cStart--;
                else if (dir == 3)
                    rStart--;
            }
            dir = (dir + 1) % 4;
        }
        return out;
    }
}
