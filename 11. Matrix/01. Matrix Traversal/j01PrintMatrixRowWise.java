import java.util.Scanner;

public class j01PrintMatrixRowWise{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int col = in.nextInt();
        int[][] matrix = new int[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                matrix[i][j] = in.nextInt();
            }
        }
        printMatrixRowWise(matrix);
        in.close();
    }

    // O(row * col)
    public static void printMatrixRowWise(int[][] mat){
        for(int row = 0; row < mat.length; row++){
            for(int col = 0; col < mat[row].length; col++){
                System.out.print(mat[row][col] + " ");
            }
            System.out.println();
        }
    }
}