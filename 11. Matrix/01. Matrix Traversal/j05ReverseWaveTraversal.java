import java.util.Scanner;

public class j05ReverseWaveTraversal {
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
        reverseWaveTraversal(matrix);
        in.close();
    }

    public static void reverseWaveTraversal(int[][] matrix){
        for(int col = matrix[0].length - 1; col >= 0; col--){
            if(col % 2 == 0){
                for(int row = 0; row < matrix.length; row++){
                    System.out.print(matrix[row][col] + " ");
                }
            }else{
                for(int row = matrix.length - 1; row >= 0; row--){
                    System.out.print(matrix[row][col] + " ");
                }
            }
            System.out.println();
        }
    }

}
