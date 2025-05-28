/**
 * GeeksForGeeks. Find the closest element in BST
 * 
 * Problem Statement:
 *     Given a BST and a value K, find the absolute difference between K and the
 *     closest element in the BST. If there are multiple closest elements, return
 *     the minimum absolute difference.
 * 
 * Input:
 *     - root: Root node of BST
 *     - k: Target value to find closest element
 * 
 * Output:
 *     - Minimum absolute difference between k and any node in BST
 * 
 * Example:
 *     Input: 
 *              10
 *            /    \
 *           5     15
 *          / \    / \
 *         2   8  12  20
 *         k = 13
 *     
 *     Output: 1
 *     
 *     Explanation:
 *     Closest value to 13 is 12, giving absolute difference of |13-12| = 1
 */

public class j04ClosestInBST {

    static class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    /**
     * Approach: Floor and Ceil Based Solution
     * 
     * Intuition:
     * - The closest value will be either floor (largest smaller) or ceil (smallest larger)
     * - Compare differences of k with both floor and ceil to find minimum
     * 
     * Explanation:
     * - Step 1: Find floor value (largest value ≤ k)
     * - Step 2: Find ceil value (smallest value ≥ k)
     * - Step 3: Return minimum of (k - floor) and (ceil - k)
     * 
     * Time Complexity: O(H) where H is height of BST
     * Space Complexity: O(1)
     * 
     * @param root    Root node of BST
     * @param k       Target value to find closest element
     * @return        Minimum absolute difference
     */
    static int minDiff(Node root, int k) {
        // Find floor and ceil values
        int floor = floor(root, k);
        int ceil = ceil(root, k);
        int ans = Integer.MAX_VALUE;
        
        // Compare with floor if exists
        if (floor != -1)
            ans = Math.min(ans, k - floor);
        
        // Compare with ceil if exists
        if (ceil != -1)
            ans = Math.min(ans, ceil - k);
            
        return ans;
    }

    /**
     * Helper Method: Find Floor Value
     * 
     * Intuition:
     * - Keep track of last smaller value seen while traversing
     * - Update when current node is smaller than k
     * 
     * Time Complexity: O(H)
     * Space Complexity: O(1)
     */
    public static int floor(Node root, int k) {
        if (root == null)
            return -1;
        int floor = Integer.MIN_VALUE;
        
        while (root != null) {
            // Found exact match
            if (root.data == k) {
                floor = k;
                break;
            } 
            // Current value too large, go left
            else if (root.data > k) {
                root = root.left;
            } 
            // Current value potential floor, go right
            else if (root.data < k) {
                floor = root.data;
                root = root.right;
            }
        }
        return (floor != Integer.MIN_VALUE) ? floor : -1;
    }

    /**
     * Helper Method: Find Ceil Value
     * 
     * Intuition:
     * - Keep track of last larger value seen while traversing
     * - Update when current node is larger than k
     * 
     * Time Complexity: O(H)
     * Space Complexity: O(1)
     */
    public static int ceil(Node root, int k) {
        if (root == null)
            return -1;
        int ceil = Integer.MAX_VALUE;
        
        while (root != null) {
            // Found exact match
            if (root.data == k) {
                ceil = k;
                break;
            } 
            // Current value potential ceil, go left
            else if (root.data > k) {
                ceil = root.data;
                root = root.left;
            } 
            // Current value too small, go right
            else if (root.data < k) {
                root = root.right;
            }
        }
        return (ceil != Integer.MAX_VALUE) ? ceil : -1;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        Node root1 = new Node(10);
        root1.left = new Node(5);
        root1.right = new Node(15);
        root1.left.left = new Node(2);
        root1.left.right = new Node(8);
        root1.right.left = new Node(12);
        root1.right.right = new Node(20);
        
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: k=13, Expected: 1, Output: " + minDiff(root1, 13));
        System.out.println("Input: k=7, Expected: 2, Output: " + minDiff(root1, 7));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: null tree, Expected: Integer.MAX_VALUE, Output: " + 
                          minDiff(null, 5));

        // Test Case 3: Boundary cases
        Node singleNode = new Node(5);
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: Single node k=3, Expected: 2, Output: " + 
                          minDiff(singleNode, 3));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: Exact match k=10, Expected: 0, Output: " + 
                          minDiff(root1, 10));
    }
}
