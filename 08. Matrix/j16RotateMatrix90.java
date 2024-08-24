import java.util.Scanner;

public class j16RotateMatrix90 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = in.nextInt();
            }
        }

        rotateMatrix90(matrix);

        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        in.close();
    }

    // O(n^2)
    public static void rotateMatrix90(int[][] matrix){
        for(int i = 0; i < matrix.length; i++){
            for(int j = i; j < matrix.length; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        int s = 0;
        int e = matrix.length - 1;
        while(s < e){
            for(int i = 0; i < matrix.length; i++){
                int temp = matrix[i][e];
                matrix[i][e] = matrix[i][s];
                matrix[i][s] = temp;
            }
            s++;
            e--;
        }
    }
}
