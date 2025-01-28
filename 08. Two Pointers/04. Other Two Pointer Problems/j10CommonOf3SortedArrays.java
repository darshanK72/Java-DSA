/**
 * Problem Statement:
 * 
 *     Given three sorted arrays, find the common elements between all three arrays.
 *     The common elements should be returned in a list, in the order of their appearance in the arrays.
 *     If no common elements exist, return [-1].
 * 
 * Input:
 *     - Three integer lists: `arr1`, `arr2`, `arr3` representing the three sorted arrays.
 *     - The size of each list is denoted by `n1`, `n2`, and `n3` respectively.
 * 
 * Output:
 *     - A list of integers containing the common elements between the three arrays.
 *     - If no common elements are found, the list should contain [-1].
 * 
 * Example:
 *     Input:
 *     arr1 = [1, 5, 10, 20, 40, 80]
 *     arr2 = [6, 7, 20, 80, 100]
 *     arr3 = [3, 4, 20, 80, 120]
 * 
 *     Output:
 *     [20, 80]
 * 
 *     Explanation:
 *     The common elements in all three arrays are 20 and 80.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j10CommonOf3SortedArrays {

    public static void main(String[] args) {
        // Creating Scanner object for user input
        Scanner sc = new Scanner(System.in);

        // Reading size of the three arrays
        System.out.println("Enter the size of the first array:");
        int n1 = sc.nextInt();
        System.out.println("Enter the size of the second array:");
        int n2 = sc.nextInt();
        System.out.println("Enter the size of the third array:");
        int n3 = sc.nextInt();

        // Reading elements of the arrays
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        List<Integer> arr3 = new ArrayList<>();

        System.out.println("Enter the elements of the first array:");
        for (int i = 0; i < n1; i++) {
            arr1.add(sc.nextInt());
        }

        System.out.println("Enter the elements of the second array:");
        for (int i = 0; i < n2; i++) {
            arr2.add(sc.nextInt());
        }

        System.out.println("Enter the elements of the third array:");
        for (int i = 0; i < n3; i++) {
            arr3.add(sc.nextInt());
        }

        // Getting the common elements from the arrays
        List<Integer> result = commonElements(arr1, arr2, arr3);

        // Displaying the result
        System.out.println("Common elements: " + result);

        // Closing the scanner
        sc.close();
    }

    /**
     * Approach 1: Three Pointer Approach
     * 
     * Intuition:
     * - Since the arrays are sorted, we use a three-pointer technique to traverse each array simultaneously.
     * - We compare the current elements from each array and increment the pointer of the array that has the smallest element.
     * - If the elements from all arrays are the same, we add it to the result list. 
     * - This way we efficiently find common elements while avoiding duplicate entries in the result.
     * 
     * Time Complexity:
     * - O(n1 + n2 + n3), where n1, n2, and n3 are the sizes of the three arrays. This is because we traverse each array once.
     * 
     * Space Complexity:
     * - O(min(n1, n2, n3)) for storing the result list, which in the worst case holds the common elements.
     * 
     * @param arr1 The first input sorted array.
     * @param arr2 The second input sorted array.
     * @param arr3 The third input sorted array.
     * @return A list of integers containing the common elements or [-1] if no common elements exist.
     */
    public static List<Integer> commonElements(List<Integer> arr1, List<Integer> arr2, List<Integer> arr3) {
        ArrayList<Integer> out = new ArrayList<>();
        int i = 0, j = 0, k = 0;
        int n1 = arr1.size(), n2 = arr2.size(), n3 = arr3.size();

        while (i < n1 && j < n2 && k < n3) {
            int val1 = arr1.get(i);
            int val2 = arr2.get(j);
            int val3 = arr3.get(k);

            // If all three values match, add to the result list
            if (val1 == val2 && val2 == val3) {
                if (out.isEmpty() || out.get(out.size() - 1) != val1) {
                    out.add(val1);
                }
                i++;
                j++;
                k++;
            }
            // Move the pointer with the smallest value
            else if (val1 < val2) {
                i++;
            } else if (val2 < val3) {
                j++;
            } else {
                k++;
            }
        }

        if (out.size() == 0)
            out.add(-1);

        return out;
    }
}
