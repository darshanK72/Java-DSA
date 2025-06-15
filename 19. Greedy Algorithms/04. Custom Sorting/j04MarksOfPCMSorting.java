/**
 * GeeksForGeeks: PCM Marks Sorting
 * 
 * Problem Statement:
 *     Given three arrays containing marks of N students in Physics, Chemistry, and
 *     Mathematics, sort the students based on the following criteria:
 *     1. First by Physics marks in ascending order
 *     2. If Physics marks are same, then by Chemistry marks in descending order
 *     3. If Chemistry marks are also same, then by Mathematics marks in ascending order
 * 
 * Input:
 *     - phy[] (int[]): Array of Physics marks
 *     - chem[] (int[]): Array of Chemistry marks
 *     - math[] (int[]): Array of Mathematics marks
 *     - N (int): Number of students
 *     - 1 <= N <= 10^5
 *     - 0 <= marks <= 100
 * 
 * Output:
 *     - The three arrays should be modified in-place to reflect the sorted order
 * 
 * Example:
 *     Input: 
 *     phy[] = {4, 1, 4, 2}
 *     chem[] = {3, 2, 1, 2}
 *     math[] = {1, 3, 2, 4}
 *     N = 4
 * 
 *     Output:
 *     phy[] = {1, 2, 4, 4}
 *     chem[] = {2, 2, 1, 3}
 *     math[] = {3, 4, 2, 1}
 * 
 *     Explanation:
 *     Students are sorted first by Physics marks (ascending), then by Chemistry
 *     marks (descending), and finally by Mathematics marks (ascending).
 */

import java.util.Arrays;

public class j04MarksOfPCMSorting {
    /**
     * Approach 1: Custom Sorting with Comparator
     * 
     * Intuition:
     * - We need to sort students based on multiple criteria
     * - We can combine the three arrays into a 2D array where each row
     *   represents a student's marks
     * - Use a custom comparator to implement the sorting criteria
     * - After sorting, update the original arrays
     * 
     * Explanation:
     * 1. Create a 2D array to store all marks for each student
     * 2. Sort the 2D array using custom comparator:
     *    - First compare Physics marks (ascending)
     *    - If equal, compare Chemistry marks (descending)
     *    - If equal, compare Mathematics marks (ascending)
     * 3. Update original arrays with sorted values
     * 
     * Time Complexity: O(n log n) where n is the number of students
     *                  Due to sorting operation
     * Space Complexity: O(n) to store the 2D array of marks
     * 
     * @param phy  Array of Physics marks
     * @param chem Array of Chemistry marks
     * @param math Array of Mathematics marks
     * @param N    Number of students
     */
    public static void customSort(int phy[], int chem[], int math[], int N) {
        // Create 2D array to store all marks for each student
        int[][] students = new int[N][3];
        for (int i = 0; i < N; i++) {
            students[i] = new int[] {
                    phy[i], chem[i], math[i]
            };
        }

        // Sort using custom comparator
        Arrays.sort(students, (a, b) -> {
            // First compare Physics marks (ascending)
            if (a[0] != b[0])
                return a[0] - b[0];
            else {
                // If Physics marks are equal, compare Chemistry marks (descending)
                if (a[1] != b[1])
                    return b[1] - a[1];
                else {
                    // If Chemistry marks are also equal, compare Mathematics marks (ascending)
                    return a[2] - b[2];
                }
            }
        });

        // Update original arrays with sorted values
        for (int i = 0; i < N; i++) {
            phy[i] = students[i][0];
            chem[i] = students[i][1];
            math[i] = students[i][2];
        }
    }

    /**
     * Approach 2: In-place Sorting with Custom Comparator
     * 
     * Intuition:
     * - Instead of creating a 2D array, we can create an array of indices
     * - Sort the indices based on the given criteria
     * - Use the sorted indices to rearrange the original arrays
     * 
     * Explanation:
     * 1. Create an array of indices [0, 1, 2, ..., N-1]
     * 2. Sort the indices using a custom comparator that compares the actual values
     *    from the original arrays
     * 3. Use the sorted indices to rearrange the original arrays
     * 
     * Time Complexity: O(n log n) where n is the number of students
     *                  Due to sorting operation
     * Space Complexity: O(n) for storing indices, but avoids creating a 2D array
     * 
     * Advantages over Approach 1:
     * 1. More memory efficient as it avoids creating a 2D array
     * 2. Better cache locality as it works with smaller data structures
     * 3. More flexible as it can be easily modified to sort by different criteria
     * 
     * @param phy  Array of Physics marks
     * @param chem Array of Chemistry marks
     * @param math Array of Mathematics marks
     * @param N    Number of students
     */
    public static void customSortOptimized(int phy[], int chem[], int math[], int N) {
        // Create array of indices
        Integer[] indices = new Integer[N];
        for (int i = 0; i < N; i++) {
            indices[i] = i;
        }

        // Sort indices using custom comparator
        Arrays.sort(indices, (a, b) -> {
            // First compare Physics marks (ascending)
            if (phy[a] != phy[b])
                return phy[a] - phy[b];
            else {
                // If Physics marks are equal, compare Chemistry marks (descending)
                if (chem[a] != chem[b])
                    return chem[b] - chem[a];
                else {
                    // If Chemistry marks are also equal, compare Mathematics marks (ascending)
                    return math[a] - math[b];
                }
            }
        });

        // Create temporary arrays to store sorted values
        int[] tempPhy = new int[N];
        int[] tempChem = new int[N];
        int[] tempMath = new int[N];

        // Rearrange arrays using sorted indices
        for (int i = 0; i < N; i++) {
            tempPhy[i] = phy[indices[i]];
            tempChem[i] = chem[indices[i]];
            tempMath[i] = math[indices[i]];
        }

        // Copy back to original arrays
        System.arraycopy(tempPhy, 0, phy, 0, N);
        System.arraycopy(tempChem, 0, chem, 0, N);
        System.arraycopy(tempMath, 0, math, 0, N);
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] phy1 = {4, 1, 4, 2};
        int[] chem1 = {3, 2, 1, 2};
        int[] math1 = {1, 3, 2, 4};
        int N1 = 4;
        System.out.println("Input:");
        System.out.println("Physics: " + Arrays.toString(phy1));
        System.out.println("Chemistry: " + Arrays.toString(chem1));
        System.out.println("Mathematics: " + Arrays.toString(math1));
        
        // Test both approaches
        System.out.println("\nOriginal Approach Output:");
        customSort(phy1.clone(), chem1.clone(), math1.clone(), N1);
        System.out.println("Physics: " + Arrays.toString(phy1));
        System.out.println("Chemistry: " + Arrays.toString(chem1));
        System.out.println("Mathematics: " + Arrays.toString(math1));
        
        System.out.println("\nOptimized Approach Output:");
        customSortOptimized(phy1, chem1, math1, N1);
        System.out.println("Physics: " + Arrays.toString(phy1));
        System.out.println("Chemistry: " + Arrays.toString(chem1));
        System.out.println("Mathematics: " + Arrays.toString(math1));

        // Test Case 2: All same marks
        System.out.println("\nAll Same Marks Test Case:");
        int[] phy2 = {5, 5, 5};
        int[] chem2 = {5, 5, 5};
        int[] math2 = {5, 5, 5};
        int N2 = 3;
        System.out.println("Input:");
        System.out.println("Physics: " + Arrays.toString(phy2));
        System.out.println("Chemistry: " + Arrays.toString(chem2));
        System.out.println("Mathematics: " + Arrays.toString(math2));
        
        // Test both approaches
        System.out.println("\nOriginal Approach Output:");
        customSort(phy2.clone(), chem2.clone(), math2.clone(), N2);
        System.out.println("Physics: " + Arrays.toString(phy2));
        System.out.println("Chemistry: " + Arrays.toString(chem2));
        System.out.println("Mathematics: " + Arrays.toString(math2));
        
        System.out.println("\nOptimized Approach Output:");
        customSortOptimized(phy2, chem2, math2, N2);
        System.out.println("Physics: " + Arrays.toString(phy2));
        System.out.println("Chemistry: " + Arrays.toString(chem2));
        System.out.println("Mathematics: " + Arrays.toString(math2));

        // Test Case 3: Single student
        System.out.println("\nSingle Student Test Case:");
        int[] phy3 = {10};
        int[] chem3 = {20};
        int[] math3 = {30};
        int N3 = 1;
        System.out.println("Input:");
        System.out.println("Physics: " + Arrays.toString(phy3));
        System.out.println("Chemistry: " + Arrays.toString(chem3));
        System.out.println("Mathematics: " + Arrays.toString(math3));
        
        // Test both approaches
        System.out.println("\nOriginal Approach Output:");
        customSort(phy3.clone(), chem3.clone(), math3.clone(), N3);
        System.out.println("Physics: " + Arrays.toString(phy3));
        System.out.println("Chemistry: " + Arrays.toString(chem3));
        System.out.println("Mathematics: " + Arrays.toString(math3));
        
        System.out.println("\nOptimized Approach Output:");
        customSortOptimized(phy3, chem3, math3, N3);
        System.out.println("Physics: " + Arrays.toString(phy3));
        System.out.println("Chemistry: " + Arrays.toString(chem3));
        System.out.println("Mathematics: " + Arrays.toString(math3));
    }
}
