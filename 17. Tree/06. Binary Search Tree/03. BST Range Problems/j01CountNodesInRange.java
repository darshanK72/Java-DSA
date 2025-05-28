/**
 * GeeksForGeeks. Count BST nodes that lie in a given range
 * 
 * Problem Statement:
 *     Given a Binary Search Tree (BST) and a range [low, high], count the number
 *     of nodes that lie in the given range (inclusive).
 * 
 * Input:
 *     - root: Root node of BST
 *     - low: Lower bound of range (inclusive)
 *     - high: Upper bound of range (inclusive)
 * 
 * Output:
 *     - Return count of nodes in the given range
 * 
 * Example:
 *     Input: 
 *              10
 *            /    \
 *           5      50
 *          /  \   /  \
 *         1    8 40   100
 *         low = 5, high = 45
 *     
 *     Output: 3
 *     
 *     Explanation:
 *     Nodes with values 5, 8, and 40 lie between 5 and 45
 */

public class j01CountNodesInRange {

    static class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    /**
     * Approach 2: Inorder Traversal with Counter
     * 
     * Intuition:
     * - Use inorder traversal to visit nodes in sorted order
     * - Maintain count using array to allow modification in recursion
     * 
     * Explanation:
     * - Step 1: Traverse left subtree
     * - Step 2: Process current node if in range
     * - Step 3: Traverse right subtree
     * 
     * Time Complexity: O(N) where N is number of nodes
     * Space Complexity: O(H) for recursion stack
     */
    public static int countNodesInRangeII(Node root, int l, int h) {
        int[] count = new int[1];
        getCount(root, l, h, count);
        return count[0];
    }

    /**
     * Helper method for Count Nodes in Range
     * 
     * Intuition:
     * - Use inorder traversal to count nodes in range
     * 
     * Explanation:
     * - Step 1: If current node is null, return
     * - Step 2: Traverse left subtree
     * - Step 3: If current node is in range, increment count
     * - Step 4: Traverse right subtree
     * 
     * Time Complexity: O(N) where N is number of nodes
     * Space Complexity: O(H) for recursion stack
     * 
     * @param root   Root node of BST
     * @param low    Lower bound of range (inclusive)
     * @param high   Upper bound of range (inclusive)
     * @param count  Array to hold count of nodes in range
     */
    private static void getCount(Node root, int low, int high, int[] count) {
        if (root == null)
            return;
        // Traverse left subtree
        getCount(root.left, low, high, count);
        // Check if current node is in range
        if (root.data >= low && root.data <= high) {
            count[0]++;
        }
        // Traverse right subtree
        getCount(root.right, low, high, count);
    }

    /**
     * Approach 1: Recursive with Range Check
     * 
     * Intuition:
     * - Use BST property to optimize traversal
     * - Skip subtrees that can't contain nodes in range
     * - Only traverse relevant portions of the tree
     * 
     * Explanation:
     * - Step 1: If current node < low, only check right subtree
     * - Step 2: If current node > high, only check left subtree
     * - Step 3: If node is in range, include it and check both subtrees
     * 
     * Time Complexity: O(H + K) where H is height and K is nodes in range
     * Space Complexity: O(H) for recursion stack
     * 
     * @param root    Root node of BST
     * @param low     Lower bound of range (inclusive)
     * @param high    Upper bound of range (inclusive)
     * @return        Count of nodes in the given range
     */
    public static int countNodesInRangeI(Node root, int low, int high) {
        // Base case: empty tree
        if (root == null) {
            return 0;
        }

        // If current node is less than low, check only right subtree
        if (root.data < low) {
            return countNodesInRangeI(root.right, low, high);
        } 
        // If current node is greater than high, check only left subtree
        else if (root.data > high) {
            return countNodesInRangeI(root.left, low, high);
        } 
        // Node is in range, count it and check both subtrees
        else {
            return 1 + countNodesInRangeI(root.left, low, high) 
                     + countNodesInRangeI(root.right, low, high);
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        Node root1 = new Node(10);
        root1.left = new Node(5);
        root1.right = new Node(50);
        root1.left.left = new Node(1);
        root1.left.right = new Node(8);
        root1.right.left = new Node(40);
        root1.right.right = new Node(100);
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: [5,45], Expected: 3, Output: " + countNodesInRangeI(root1, 5, 45));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: null tree, Expected: 0, Output: " + countNodesInRangeI(null, 1, 10));
        
        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        Node singleNode = new Node(5);
        System.out.println("Input: Single node [5,5], Expected: 1, Output: " + 
                          countNodesInRangeI(singleNode, 5, 5));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: No nodes in range [100,200], Expected: 0, Output: " + 
                          countNodesInRangeI(root1, 100, 200));
    }
}