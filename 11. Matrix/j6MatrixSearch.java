import java.util.Scanner;

public class j6MatrixSearch {
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
        int x = in.nextInt();
        int[] index = matrixSearch(matrix,x);
        System.out.println(index[0] + " " + index[1]);
        in.close();
    }

    public static int[] matrixSearch(int[][] mat,int x){
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[0].length; j++){
                if(mat[i][j] == x) return new int[]{i,j};
            }
        }
        return new int[]{-1,-1};
    }
}
