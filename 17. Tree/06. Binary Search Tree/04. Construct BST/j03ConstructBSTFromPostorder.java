/**
 * GeeksForGeeks. Construct BST from Postorder
 * 
 * Problem Statement:
 *     Given postorder traversal of a Binary Search Tree, construct the BST.
 * 
 * Input:
 *     - postorder: Integer array representing postorder traversal of BST
 *     - n: Length of the array
 * 
 * Output:
 *     - Root node of constructed BST
 * 
 * Example:
 *     Input: [1, 7, 5, 12, 10, 8]
 *     Output: 
 *              8
 *            /   \
 *           5    10
 *          / \     \
 *         1   7    12
 *     
 *     Explanation:
 *     - Last element 8 is root
 *     - All elements < 8 form left subtree [1,7,5]
 *     - All elements > 8 form right subtree [12,10]
 *     - Process repeats recursively
 */

import java.util.*;

public class j03ConstructBSTFromPostorder {

    static class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    /**
     * Explanation:
     * - Convert postorder array to BST:
     *   1. Last element is root
     *   2. Find first smaller element (separates left and right subtrees)
     *   3. Recursively build left and right subtrees
     * 
     * Time Complexity: O(N²)
     * - For each node, may need to scan entire remaining array
     * 
     * Space Complexity: O(N)
     * - Recursion stack in worst case (skewed tree)
     * 
     * @param post Array containing postorder traversal
     * @param n    Length of the array
     * @return     Root node of constructed BST
     */
    public static Node constructTree(int post[], int n) {
        return convertPostorderToBST(post, n - 1, 0);
    }

    /**
     * Helper Method: Find Index of First Smaller Value
     * 
     * Explanation:
     * - Linear scan from end to find first element < key
     * - Returns -1 if no smaller element found
     * 
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public static int findSmallerValueIndex(int[] postorder, int e, int s, int key) {
        for (int i = e; i >= s; i--) {
            if (postorder[i] < key)
                return i;
        }
        return -1;
    }

    /**
     * Helper Method: Convert Postorder Segment to BST
     * 
     * Explanation:
     * - Converts array segment [s,e] to BST:
     *   1. Create root from last element
     *   2. Find index of first smaller element
     *   3. If no smaller element:
     *      - All remaining elements form right subtree
     *   4. If smaller element found at index idx:
     *      - Elements [s, idx] form left subtree
     *      - Elements [idx+1, e-1] form right subtree
     */
    public static Node convertPostorderToBST(int[] postorder, int e, int s) {
        if (e < s)
            return null;
        Node root = new Node(postorder[e]);
        int idx = findSmallerValueIndex(postorder, e - 1, s, postorder[e]);
        if (idx == -1) {
            root.right = convertPostorderToBST(postorder, e - 1, s);
        } else {
            root.right = convertPostorderToBST(postorder, e - 1, idx + 1);
            root.left = convertPostorderToBST(postorder, idx, s);
        }
        return root;
    }

    /**
     * Helper Method: Print BST Level by Level
     * 
     * Explanation:
     * - Uses Queue to process nodes level by level:
     *   1. Add root to queue
     *   2. For each level:
     *      - Process all nodes in current level
     *      - Add their children to queue
     *      - Print level separator
     * 
     * Time Complexity: O(N)
     * Space Complexity: O(W) where W is max width of tree
     */
    public static void printLevelOrder(Node root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);  // Level separator

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            if (curr == null) {
                System.out.println();  // New level
                if (!queue.isEmpty()) {
                    queue.add(null);  // Add separator for next level
                }
                continue;
            }

            System.out.print(curr.data + " ");

            if (curr.left != null)
                queue.add(curr.left);
            if (curr.right != null)
                queue.add(curr.right);
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        int[] post1 = {1, 7, 5, 12, 10, 8};
        System.out.println("\nBasic Test Case:");
        Node root1 = constructTree(post1, post1.length);
        printLevelOrder(root1);

        // Test Case 2: Single element
        int[] post2 = {1};
        System.out.println("\nSingle Element Test Case:");
        Node root2 = constructTree(post2, post2.length);
        printLevelOrder(root2);

        // Test Case 3: Increasing sequence
        int[] post3 = {1, 2, 3, 4, 5};
        System.out.println("\nIncreasing Sequence Test Case:");
        Node root3 = constructTree(post3, post3.length);
        printLevelOrder(root3);

        // Test Case 4: Decreasing sequence
        int[] post4 = {5, 4, 3, 2, 1};
        System.out.println("\nDecreasing Sequence Test Case:");
        Node root4 = constructTree(post4, post4.length);
        printLevelOrder(root4);
    }
}
