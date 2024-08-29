import java.util.Arrays;
import java.util.Scanner;

public class j31SpiralMatrix {

    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] mat = generateSpiralMatrix(n);
        for(int[] arr : mat){
            System.out.println(Arrays.toString(arr));
        }
        
        in.close();
    }

    public static int[][] generateSpiralMatrix(int n) {
        int rowStart = 0;
        int rowEnd = n - 1;
        int colStart = 0;
        int colEnd = n - 1;

        int[][] out = new int[n][n];
        int k = 1;

        while (true) {
            for (int i = colStart; i <= colEnd; i++) {
                out[rowStart][i] = k;
                k++;
            }
            rowStart++;
            if (rowStart > rowEnd)
                break;
            for (int j = rowStart; j <= rowEnd; j++) {
                out[j][colEnd] = k;
                k++;
            }
            colEnd--;
            if (colStart > colEnd)
                break;
            for (int i = colEnd; i >= colStart; i--) {
                out[rowEnd][i] = k;
                k++;
            }
            rowEnd--;
            if (rowStart > rowEnd)
                break;
            for (int j = rowEnd; j >= rowStart; j--) {
                out[j][colStart] = k;
                k++;
            }
            colStart++;
            if (colStart > colEnd)
                break;
        }
        return out;
    }
}
