import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class j02DiagonalTraversalTRBL {
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
        System.out.println(Arrays.toString(diagonalTraverseTRBL1(matrix)));
        System.out.println(Arrays.toString(diagonalTraverseTRBL2(matrix)));
        in.close();
    }
    public static int[] diagonalTraverseTRBL1(int[][] mat){
        int m = mat.length; 
        int n = mat[0].length;

        int[] out = new int[m * n];
        int k = 0;

        for(int d = 0; d < m + n - 1; d++){
            int row = d < n ? 0 : d - n + 1;
            int col = d < n ? d : n - 1;

            while(row < m && col >= 0){
                out[k++] = mat[row][col];
                row++;
                col--;
            }
        }
        return out;
    }

     public static int[] diagonalTraverseTRBL2(int[][] mat){
        Map<Integer, List<Integer>> map = new HashMap<>();
        int count = 0;

        // O(N ^ 2)
        // Traverse the matrix and store the elements in diagonals
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if(!map.containsKey(i + j)){
                    map.put(i + j,new ArrayList<>());
                }
                map.get(i+j).add(mat[i][j]);
                count++;
            }
        }

        int[] result = new int[count];
        int k = 0;

        // O(N ^ 2)
        // Retrieve the diagonals and concatenate them to form the result array
        for (int i = 0; i < map.size(); i++) {
            List<Integer> diagonal = map.get(i);
            for (int j = 0; j <  diagonal.size(); j++) {
                result[k++] = diagonal.get(j);
            }
        }

        return result;
    }
}
