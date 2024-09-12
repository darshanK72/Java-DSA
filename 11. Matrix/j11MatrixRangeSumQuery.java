import java.util.Scanner;

public class j11MatrixRangeSumQuery {
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

        int[][] prefixSumMatrix = getPrefixSum(matrix);

        int t = in.nextInt();

        while (t > 0) {
            int row1 = in.nextInt();
            int col1 = in.nextInt();
            int row2 = in.nextInt();
            int col2 = in.nextInt();
            System.out.println(getGridSum(prefixSumMatrix,row1,col1,row2,col2));
            t--;
        }
        in.close();
    }

    // O(n^2)
    public static int[][] getPrefixSum(int[][] matrix){
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] mat = new int[m+1][n+1];
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                mat[i][j] = mat[i][j-1] + mat[i-1][j] - mat[i-1][j-1] + matrix[i-1][j-1];
            }
        }
        return mat;
    }

    // O(1)
    public static int getGridSum(int[][] prefixSumMatrix,int row1,int col1,int row2,int col2){
        return prefixSumMatrix[row2 + 1][col2 + 1] - prefixSumMatrix[row2 + 1][col1] - prefixSumMatrix[row1][col2 + 1] + prefixSumMatrix[row1][col1];
    }


}
