import java.util.Scanner;

public class j02PrintMatrixColWise {
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
        printMatrixColWise(matrix,row,col);
        in.close();
    }

     // O(row * col)
    public static void printMatrixColWise(int[][] mat,int m,int n){
        for(int col = 0; col < n; col++){
            for(int row = 0; row < m; row++){
                System.out.print(mat[row][col] + " ");
            }
            System.out.println();
        }
    }

}
