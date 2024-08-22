import java.util.Scanner;

public class j8Convert2DTo1DArray {
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
        int[] arr = convertTo1D(matrix);

        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        in.close();
    }

    public static int[] convertTo1D(int[][] mat){
        int l = mat.length * mat[0].length;
        int[] out = new int[l];
        for(int i = 0; i < l; i++){
            int row = i / mat[0].length;
            int col = i % mat[0].length;
            out[i] = mat[row][col];
        }
        return out;
    }
}
