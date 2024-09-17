import java.util.Arrays;
import java.util.Scanner;

public class j02SetMatrixZeros {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int col = in.nextInt();
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
        setMatrixZeros(matrix);
        for(int[] arr : matrix){
            System.out.println(Arrays.toString(arr));
        }
        in.close();
    }

    // TC : O(M * N * (M + N)) SP : O(M * N)
    public static void setMatrixZeros(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    for (int k = 0; k < matrix.length; k++) {
                        if (matrix[k][j] != 0) {
                            matrix[k][j] = -1;
                        }
                    }
                    for (int k = 0; k < matrix[0].length; k++) {
                        if (matrix[i][k] != 0) {
                            matrix[i][k] = -1;
                        }
                    }
                }
            }
        }
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == -1) matrix[i][j] = 0;
            }
        }
    }
    // TC : O(M * N) SP : O(N)
    public static void setMatrixZeroesEfficient(int[][] matrix) {
        boolean[] rowFlag = new boolean[matrix.length];
        boolean[] colFlag = new boolean[matrix[0].length];

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == 0){
                    rowFlag[i] = true;
                    colFlag[j] = true;
                }
            }
        }

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(rowFlag[i] || colFlag[j]){
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void setMatrixZerosInplace(int[][] matrix){
        boolean rowZero = false;
        boolean colZero = false;

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                    if(i == 0) rowZero = true;
                    if(j == 0) colZero = true;
                }
            }
        }

        for(int i = 1; i < matrix.length; i++){
            for(int j = 1; j < matrix[0].length; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }

        if(rowZero){
            for(int i = 0; i < matrix[0].length; i++){
                matrix[0][i] = 0;
            }
        }

        if(colZero){
            for(int i = 0; i < matrix.length; i++){
                matrix[i][0] = 0;
            }
        }
    }
}
