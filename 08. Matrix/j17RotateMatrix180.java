import java.util.Scanner;

public class j17RotateMatrix180 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = in.nextInt();
            }
        }

        rotateMatrix180(matrix);

        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        in.close();
    }
    public static void rotateMatrix180(int[][] matrix)
    {
        int n = matrix.length;
        for(int i = 0; i < n; i++){
            int s = 0; 
            int e = n - 1;
            while(s < e){
                int temp = matrix[i][s];
                matrix[i][s] = matrix[i][e];
                matrix[i][e] = temp;
                s++;
                e--;
            }
        }
        
       for(int i = 0; i < n; i++){
            int s = 0; 
            int e = n - 1;
            while(s < e){
                int temp = matrix[s][i];
                matrix[s][i] = matrix[e][i];
                matrix[e][i] = temp;
                s++;
                e--;
            }
        }
    }
}
