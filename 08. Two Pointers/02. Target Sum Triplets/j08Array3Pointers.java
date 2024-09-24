import java.util.Arrays;
import java.util.List;

public class j08Array3Pointers {
    // You are given 3 arrays A, B and C. All 3 of the arrays are sorted.
    // Find i, j, k such that :
    // max(abs(A[i] - B[j]), abs(B[j] - C[k]), abs(C[k] - A[i])) is minimized.
    // Return the minimum max(abs(A[i] - B[j]), abs(B[j] - C[k]), abs(C[k] - A[i]))

    public static void main(String[] args) {
        // Initialize the three sorted arrays
        List<Integer> A = Arrays.asList(1, 4, 10);
        List<Integer> B = Arrays.asList(2, 15, 20);
        List<Integer> C = Arrays.asList(10, 12);

        // Call the minimize method and print the result
        int result = minimize(A, B, C);
        System.out.println("The minimized maximum difference is: " + result);
    }

    public static int minimize(final List<Integer> A, final List<Integer> B, final List<Integer> C) {
        int i = 0, j = 0, k = 0;
        int ans = Integer.MAX_VALUE;

        while (i < A.size() && j < B.size() && k < C.size()) {
            // Get the current values from each array
            int a = A.get(i);
            int b = B.get(j);
            int c = C.get(k);

            // Calculate the current maximum of absolute differences
            int currentMax = Math.max(Math.abs(a - b), Math.max(Math.abs(b - c), Math.abs(c - a)));

            // Update the answer with the minimum of the current maximum difference
            ans = Math.min(ans, currentMax);

            // Move the pointer that has the smallest value to try and minimize the max
            // difference
            if (a <= b && a <= c) {
                i++;
            } else if (b <= a && b <= c) {
                j++;
            } else {
                k++;
            }
        }

        return ans;
    }
}
