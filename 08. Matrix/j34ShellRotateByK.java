import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class j34ShellRotateByK {

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
        int k = in.nextInt();
        rotateRings(matrix,k,row,col);
        for(int[] arr:matrix){
            System.out.println(Arrays.toString(arr));
        }
        in.close();
    }

    public static void rotateRings(int[][] mat, int k, int m, int n) {
        // Spiral rotation parameters.
        int top = 0, bottom = m - 1, left = 0, right = n - 1;

        // Rotate while the ring exists.
        while (top <= bottom) {

            // To hold the ring elements.
            List<Integer> elems = new ArrayList<>();

            // Do the spiral traversal and store the ring elements.
            for (int i = left; i <= right; i++) {
                elems.add(mat[top][i]);
            }

            for (int i = top + 1; i <= bottom; i++) {
                elems.add(mat[i][right]);
            }

            for (int i = right - 1; i >= left; i--) {
                elems.add(mat[bottom][i]);
            }

            for (int i = bottom - 1; i > top; i--) {
                elems.add(mat[i][left]);
            }

            // Check if the ring size is less than or equal to k
            // If true, break as the rings after will also be smaller than k.
            if (elems.size() <= k) {
                break;
            }

            // Rotation starts.
            // ind represents the index of the 
            // position that should be at the start of the ring.
            int sz = elems.size();
            int ind = sz - k;

            // Store the rotated ring.
            for (int i = left; i <= right; i++) {
                mat[top][i] = elems.get(ind);
                ind++;
                ind %= sz;
            }

            for (int i = top + 1; i <= bottom; i++) {
                mat[i][right] = elems.get(ind);
                ind++;
                ind %= sz;
            }

            for (int i = right - 1; i >= left; i--) {
                mat[bottom][i] = elems.get(ind);
                ind++;
                ind %= sz;
            }

            for (int i = bottom - 1; i > top; i--) {
                mat[i][left] = elems.get(ind);
                ind++;
                ind %= sz;
            }

            // Update the rotation parameters.
            top++;
            bottom--;
            left++;
            right--;
        }
    }
}
