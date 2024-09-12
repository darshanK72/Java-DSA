import java.util.Scanner;

public class j13MatrixAddition {
      public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int col = in.nextInt();
        int[][] matrix1 = new int[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                matrix1[i][j] = in.nextInt();
            }
        }

        int[][] matrix2 = new int[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                matrix2[i][j] = in.nextInt();
            }
        }
        
        add(matrix1, matrix2);

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                System.out.print(matrix1[i][j] + " ");
            }
            System.out.println();
        }

        in.close();
    }

    public static void add(int[][] g1, int[][] g2) {
        for(int i = 0; i < g1.length; i++){
            for(int j = 0; j < g2.length; j++){
                g1[i][j] += g2[i][j];
            }
        }
    }
}
