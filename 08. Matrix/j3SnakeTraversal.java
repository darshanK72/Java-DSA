import java.util.Scanner;

public class j3SnakeTraversal {
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
        snakeTraverse(matrix);
        in.close();
    }

    // O(row * col)
    public static void snakeTraverse(int[][] matrix){
        for(int row = 0; row < matrix.length; row++){
            for(int col = 0; col < matrix[row].length; col++){
                if(row % 2 == 0){
                    System.out.print(matrix[row][col] + " ");
                }else{
                    System.out.print(matrix[row][(matrix[row].length - 1) - col] + " ");
                }
            }
            System.out.println();
        }
    }
}
