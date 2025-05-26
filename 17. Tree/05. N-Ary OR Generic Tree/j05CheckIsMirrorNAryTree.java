/**
 * GeeksForGeeks: Check Mirror in N-ary Tree
 * 
 * Problem Statement:
 *     Given two n-ary trees consisting of n edges and n+1 vertices with no loops. 
 *     The task is to check if they are mirror images of each other or not.
 * 
 * Input:
 *     - int n (number of nodes)
 *     - int e (number of edges)
 *     - int[] A (edge array for first tree, pairs of parent-child)
 *     - int[] B (edge array for second tree, pairs of parent-child)
 *     - 1 <= n <= 10^5
 *     - Values in arrays are in range [1, n]
 * 
 * Output:
 *     - int (1 if trees are mirror images, 0 otherwise)
 * 
 * Example:
 *     Input: n = 3, e = 2
 *            A[] = {1, 2, 1, 3}
 *            B[] = {1, 3, 1, 2}
 *     Output: 1
 * 
 *     Explanation:
 *     Tree 1:        Tree 2:
 *         1             1
 *        / \           / \
 *       2   3         3   2
 */

import java.util.HashMap;
import java.util.Stack;

public class j05CheckIsMirrorNAryTree {
    
    /**
     * Checks if two N-ary trees are mirror images
     * 
     * Intuition:
     * - Use HashMap to store parent-children relationships
     * - Stack to maintain order of children for comparison
     * - Mirror image means reversed order of children
     * 
     * Explanation:
     * - Build HashMap with parent->Stack<children> for first tree
     * - Check if second tree's children appear in reverse order
     * - Use Stack's LIFO property for reverse order check
     * 
     * Time Complexity: O(e) where e is number of edges
     * Space Complexity: O(n) for HashMap storage
     * 
     * @param n     Number of nodes
     * @param e     Number of edges
     * @param A     Edge array for first tree
     * @param B     Edge array for second tree
     * @return     1 if trees are mirror images, 0 otherwise
     */
    public static int checkMirrorTree(int n, int e, int[] A, int[] B) {
        // Create HashMap to store parent->children relationships
        HashMap<Integer, Stack<Integer>> map = new HashMap<>();
        
        // Build relationships for first tree
        for (int i = 0; i < A.length; i += 2) {
            // Create new stack for parent if not exists
            map.putIfAbsent(A[i], new Stack<>());
            // Add child to parent's stack
            map.get(A[i]).push(A[i + 1]);
        }

        // Check mirror property using second tree
        for (int i = 0; i < B.length; i += 2) {
            // Get stack of children for current parent
            Stack<Integer> stk = map.get(B[i]);
            // Compare top of stack with current child
            if (stk.peek() == B[i + 1]) {
                stk.pop();
            } else {
                return 0;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic mirror trees
        System.out.println("\nBasic Mirror Trees Test:");
        int[] A1 = {1, 2, 1, 3};
        int[] B1 = {1, 3, 1, 2};
        System.out.println("Result: " + checkMirrorTree(3, 2, A1, B1));  // Expected: 1

        // Test Case 2: Non-mirror trees
        System.out.println("\nNon-mirror Trees Test:");
        int[] A2 = {1, 2, 1, 3};
        int[] B2 = {1, 2, 1, 3};
        System.out.println("Result: " + checkMirrorTree(3, 2, A2, B2));  // Expected: 0

        // Test Case 3: Larger trees
        System.out.println("\nLarger Trees Test:");
        int[] A3 = {1, 2, 1, 3, 2, 4, 2, 5};
        int[] B3 = {1, 3, 1, 2, 2, 5, 2, 4};
        System.out.println("Result: " + checkMirrorTree(5, 4, A3, B3));  // Expected: 1
    }
}
