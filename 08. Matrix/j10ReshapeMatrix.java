import java.util.Scanner;

public class j10ReshapeMatrix {
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

        int r = in.nextInt();
        int c = in.nextInt();

        int[][] grid = reshapeMatrix(matrix,r,c);

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        in.close();
    }

    // O(n^2)
    public static int[][] reshapeMatrix(int[][] mat,int r,int c){
        if(mat.length * mat[0].length != r * c) return mat;
        int[][] out = new int[r][c];
        int row = 0;
        int col = 0;
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[0].length; j++){
                if(row < r && col < c){
                    out[row][col] = mat[i][j];
                }
                col++;
                if(col == c){
                    col = 0;
                    row++;
                }
            }
        }
        return out;
    }
}
