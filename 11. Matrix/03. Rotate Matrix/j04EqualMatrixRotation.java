import java.util.Scanner;

public class j04EqualMatrixRotation {
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

        System.out.println(findRotation(matrix1, matrix2));
        in.close();
    }
    public static boolean findRotation(int[][] mat, int[][] target) {
        int t = 4; // We will check for 0, 90, 180, and 270-degree rotations
        while (t-- > 0) {
            if (isSame(mat, target)) {
                return true;
            }
            rotateMatrix90(mat);
        }
        return false;
    }

    private static boolean isSame(int[][] mat, int[][] target) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if (mat[i][j] != target[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void rotateMatrix90(int[][] matrix) {
        int n = matrix.length;

        // Transpose the matrix
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Reverse each row to achieve 90-degree rotation
        for (int i = 0; i < n; i++) {
            int s = 0;
            int e = n - 1;
            while (s < e) {
                int temp = matrix[i][s];
                matrix[i][s] = matrix[i][e];
                matrix[i][e] = temp;
                s++;
                e--;
            }
        }
    }

}
