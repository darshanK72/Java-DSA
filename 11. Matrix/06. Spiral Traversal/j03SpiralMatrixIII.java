import java.util.Arrays;
import java.util.Scanner;

public class j03SpiralMatrixIII {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Input: number of rows, columns, and starting point coordinates
        int rows = in.nextInt();
        int cols = in.nextInt();
        int rStart = in.nextInt();
        int cStart = in.nextInt();

        // Generate the spiral matrix starting from the specified coordinates
        int[][] mat = spiralMatrixIII(rows, cols, rStart, cStart);

        // Print the result matrix
        for (int[] arr : mat) {
            System.out.println(Arrays.toString(arr));
        }

        // Close the scanner to avoid resource leak
        in.close();
    }

    /**
     * Method to generate the spiral matrix starting from a specific position.
     * The matrix is traversed in a spiral order, and only valid positions
     * (within the matrix bounds) are stored in the output array.
     * 
     * @param rows    The number of rows in the matrix.
     * @param cols    The number of columns in the matrix.
     * @param rStart  The starting row index.
     * @param cStart  The starting column index.
     * @return A 2D array containing the coordinates of the spiral path.
     */
    public static int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        // Output array to store the coordinates of the spiral path
        int[][] out = new int[rows * cols][2];

        // Direction indicator: 0 = right, 1 = down, 2 = left, 3 = up
        int dir = 0;

        // The length of steps to take in one direction
        int len = 0;

        // Index to track the number of positions filled
        int i = 0;

        // Continue until we've filled all rows * cols positions
        while (i < rows * cols) {
            // Increase the length of the path after every two directions (right+down,
            // left+up)
            if (dir == 0 || dir == 2)
                len++;

            // Traverse the matrix in the current direction
            for (int k = 0; k < len; k++) {
                // If the current position is within the bounds of the matrix, store it
                if (rStart >= 0 && rStart < rows && cStart >= 0 && cStart < cols) {
                    out[i++] = new int[] { rStart, cStart };
                }

                // Move in the current direction
                if (dir == 0) {
                    cStart++; // Move right
                } else if (dir == 1) {
                    rStart++; // Move down
                } else if (dir == 2) {
                    cStart--; // Move left
                } else if (dir == 3) {
                    rStart--; // Move up
                }
            }

            // Update the direction (right -> down -> left -> up -> right ...)
            dir = (dir + 1) % 4;
        }

        return out;
    }
}
