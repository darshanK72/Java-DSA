import java.util.Scanner;

public class j7Convert1DTo2DArray {
     public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] original = new int[n];
        for(int i = 0; i < n; i++){
            original[i] = in.nextInt();
        }
        int row = in.nextInt();
        int col = in.nextInt();
        int[][] mat = convertTo2D(original,row,col);
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[0].length; j++){
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
        in.close();
    }

    public static int[][] convertTo2D(int[] original,int m,int n){
        if(m * n != original.length) return new int[0][0];
        int k = 0;
        int[][] out = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                out[i][j] = original[k++];
            }
        }
        return out;
    }
}
