import java.util.Scanner;

public class j33ShellRotateByOne {
    
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
        System.out.println(rotateMatrix(row,col,matrix));
        in.close();
    }

    public static int[][] rotateMatrix(int M, int N, int Mat[][]) {
        int rowStart = 0;
        int rowEnd = M - 1;
        int colStart = 0;
        int colEnd = N - 1;
    
        if (M == 1 || N == 1) return Mat;
    
        while (rowStart < rowEnd && colStart < colEnd) {
            int prev = Mat[rowStart + 1][colStart];  // Corrected initial prev value
    
            // Top row (left to right)
            for (int j = colStart; j <= colEnd; j++) {
                int curr = Mat[rowStart][j];
                Mat[rowStart][j] = prev;
                prev = curr;
            }
            rowStart++;
    
            // Right column (top to bottom)
            for (int i = rowStart; i <= rowEnd; i++) {
                int curr = Mat[i][colEnd];
                Mat[i][colEnd] = prev;
                prev = curr;
            }
            colEnd--;
    
            // Bottom row (right to left)
            if (rowStart <= rowEnd) {  // Ensure we have rows to process
                for (int j = colEnd; j >= colStart; j--) {
                    int curr = Mat[rowEnd][j];
                    Mat[rowEnd][j] = prev;
                    prev = curr;
                }
                rowEnd--;
            }
            
            // Left column (bottom to top)
            if (colStart <= colEnd) {  // Ensure we have columns to process
                for (int i = rowEnd; i >= rowStart; i--) {
                    int curr = Mat[i][colStart];
                    Mat[i][colStart] = prev;
                    prev = curr;
                }
                colStart++;
            }
        }
        return Mat;
    }
}
