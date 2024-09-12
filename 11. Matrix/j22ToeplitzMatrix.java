import java.util.Scanner;

public class j22ToeplitzMatrix {
     public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int col = in.nextInt();
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
        System.out.println(isToeplitzMatrix(matrix));
        in.close();
    }
    public static boolean isToeplitzMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        for(int d = 1 - n; d <= m - 1; d++){
            int row = d <= 0 ? 0 : d;
            int col = d <= 0 ? Math.abs(d) : 0;
            int x = mat[row][col];
            while(row < m && col < n){
                if(mat[row][col] != x) return false;
                row++;
                col++;
            }
        }
        return true;
    }
}
