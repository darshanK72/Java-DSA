import java.util.Scanner;

public class j01DiagonalSum {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] matrix = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = in.nextInt();
            }
        }
        System.out.println(diagonalSum(matrix));
        in.close();
    }

    public static int diagonalSum(int[][] mat){
        int sum = 0;
        int i = 0;
        while(i < mat.length){
            sum += mat[i][i];
            if(i != mat.length - i - 1){
                sum += mat[i][mat.length - i - 1];
            }
            i++;
        }

        return sum;
    }
}
