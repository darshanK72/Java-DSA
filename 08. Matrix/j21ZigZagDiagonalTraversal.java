import java.util.Arrays;
import java.util.Scanner;

public class j21ZigZagDiagonalTraversal {
     public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int col = in.nextInt();
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
        System.out.println(Arrays.toString(zigZagTraversal(matrix)));
        in.close();
    }

    public static int[] zigZagTraversal(int[][] mat){
        int n = mat.length; 

        int[] out = new int[n * n];
        int k = 0;

        for(int d = 0; d < 2*n - 1; d++){
            int i = d < n ? 0 : d - n + 1;
            int j = d < n ? d : n - 1;

            while(i < n && j >= 0){
                if(d % 2 == 1){
                    out[k++] = mat[i][j];
                }else{
                    out[k++] = mat[j][i];
                }
                i++;
                j--;
            }
        }
        return out;   
    }

}
