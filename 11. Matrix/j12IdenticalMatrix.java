import java.util.Scanner;

public class j12IdenticalMatrix {
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

        System.out.println(isIdentical(matrix1, matrix2));
        in.close();
    }

    // O(n^2)
    public static boolean isIdentical(int[][] g1, int[][] g2) {
        if(g1.length != g2.length || g1[0].length != g2[0].length) return false;
        for(int i = 0; i < g1.length; i++){
            for(int j = 0; j < g2.length; j++){
                if(g1[i][j] != g2[i][j]) return false;
            }
        }
        return true;
    }
}
