/**
 * GeeksForGeeks. Check for Dead End in BST
 * 
 * Problem Statement:
 *     Given a Binary Search Tree (BST) and a key, check if there exists a dead end
 *     in the BST. A dead end is a node that has no children and its value is equal
 *     to either (value-1) or (value+1) of any other node in the tree.
 * 
 * Input:
 *     - root: Node (root of the BST)
 * 
 * Output:
 *     - boolean: true if dead end exists, false otherwise
 * 
 * Example:
 *     Input: 
 *           8
 *          / \
 *         5   9
 *        / \
 *       2   7
 *      /
 *     1
 *     Output: true
 * 
 *     Explanation:
 *     Node 1 is a dead end because it has no children and its value (1) is equal
 *     to (2-1), where 2 is another node in the tree.
 */

public class j04CheckForDeadEndInBST {

    /**
     * Node class to represent nodes in the BST
     * Each node contains:
     * - data: integer value
     * - left: reference to left child
     * - right: reference to right child
     */
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Approach: Range-based Validation
     * 
     * Intuition:
     * - For each node, we maintain a valid range of values it can have
     * - Left subtree can only have values less than current node
     * - Right subtree can only have values greater than current node
     * - A dead end occurs when a leaf node's value equals its range bounds
     * 
     * Explanation:
     * - Step 1: Start with full range (1 to MAX_VALUE) for root
     * - Step 2: For each node, update range for its children
     * - Step 3: Check if leaf node's value equals range bounds
     * - Step 4: Recursively check left and right subtrees
     * 
     * Time Complexity: O(N) where N is number of nodes in the BST
     * Space Complexity: O(H) where H is height of the BST (recursion stack)
     * 
     * @param root    Root node of the BST
     * @return        true if dead end exists, false otherwise
     */
    public static boolean isDeadEnd(Node root) {
        // Start with full range for root node
        return verifyIsDeadEndExists(root, 1, Integer.MAX_VALUE);
    }

    /**
     * Helper Method: Recursive function to check for dead end
     * 
     * Intuition:
     * - Maintain valid range for each node
     * - Check if leaf node's value equals range bounds
     * - Propagate result from left and right subtrees
     * 
     * Explanation:
     * - Step 1: Base case returns false for null nodes
     * - Step 2: For leaf nodes, check if value equals range bounds
     * - Step 3: Update range for left and right subtrees
     * - Step 4: Return true if either subtree has dead end
     * 
     * Time Complexity: O(1) per node
     * Space Complexity: O(H) for recursion stack
     * 
     * @param root    Current node being processed
     * @param lb      Lower bound of valid range
     * @param ub      Upper bound of valid range
     * @return        true if dead end exists in subtree, false otherwise
     */
    public static boolean verifyIsDeadEndExists(Node root, int lb, int ub) {
        // Base case: return false for null nodes
        if (root == null) {
            return false;
        }

        // Check if current node is a leaf
        if (root.left == null && root.right == null) {
            // Return true if leaf node's value equals range bounds
            if (lb == ub)
                return true;
            else
                return false;
        }

        // Check left subtree with updated range
        boolean left = verifyIsDeadEndExists(root.left, lb, root.data - 1);
        // Check right subtree with updated range
        boolean right = verifyIsDeadEndExists(root.right, root.data + 1, ub);
        // Return true if either subtree has dead end
        return left || right;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case with dead end
        System.out.println("\nBasic Test Case:");
        Node root1 = new Node(8);
        root1.left = new Node(5);
        root1.right = new Node(9);
        root1.left.left = new Node(2);
        root1.left.right = new Node(7);
        root1.left.left.left = new Node(1);
        System.out.println("Input: [8,5,9,2,7,null,null,1]");
        System.out.println("Expected: true, Output: " + isDeadEnd(root1));

        // Test Case 2: No dead end
        System.out.println("\nTest Case - No Dead End:");
        Node root2 = new Node(8);
        root2.left = new Node(7);
        root2.right = new Node(9);
        System.out.println("Input: [8,7,9]");
        System.out.println("Expected: false, Output: " + isDeadEnd(root2));

        // Test Case 3: Empty tree
        System.out.println("\nEdge Case - Empty Tree:");
        System.out.println("Input: null");
        System.out.println("Expected: false, Output: " + isDeadEnd(null));

        // Test Case 4: Single node
        System.out.println("\nEdge Case - Single Node:");
        Node root4 = new Node(1);
        System.out.println("Input: [1]");
        System.out.println("Expected: false, Output: " + isDeadEnd(root4));
    }
}
