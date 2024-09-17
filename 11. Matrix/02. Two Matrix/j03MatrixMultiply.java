import java.util.Scanner;

public class j03MatrixMultiply {
     public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int row1 = in.nextInt();
        int col1 = in.nextInt();
        int[][] matrix1 = new int[row1][col1];
        for(int i = 0; i < row1; i++){
            for(int j = 0; j < col1; j++){
                matrix1[i][j] = in.nextInt();
            }
        }

        int row2 = in.nextInt();
        int col2 = in.nextInt();
        int[][] matrix2 = new int[row2][col2];
        for(int i = 0; i < row2; i++){
            for(int j = 0; j < col2; j++){
                matrix2[i][j] = in.nextInt();
            }
        }

        int[][] out = null;

        if(col1 != row2) System.out.println("Canno't multiply");
        else{
            out = multiply(matrix2, matrix2);
        }

        for(int i = 0; i < row1; i++){
            for(int j = 0; j < col2; j++){
                System.out.print(out[i][j] + " ");
            }
            System.out.println();
        }

        in.close();
    }

    // O(n^3)
    public static int[][] multiply(int A[][], int B[][]) {
        int[][] C = new int[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < A[0].length; k++) {
                    C[i][j] += (A[i][k] * B[k][j]);
                }
            }
        }
        return C;
    }
}
