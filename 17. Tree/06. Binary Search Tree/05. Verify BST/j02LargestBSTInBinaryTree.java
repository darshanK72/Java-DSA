/**
 * GeeksForGeeks. Largest BST in Binary Tree
 * 
 * Problem Statement:
 *     Given a binary tree, find the size of the largest BST (Binary Search Tree) 
 *     subtree in it.
 * 
 * Input:
 *     - root: Node (root of the binary tree)
 *     - Node structure: data (int), left (Node), right (Node)
 * 
 * Output:
 *     - Return the size of the largest BST subtree
 * 
 * Example:
 *     Input: 
 *           50
 *          /  \
 *         30   60
 *        /  \  / \
 *       5   20 45 70
 *                  / \
 *                 65 80
 *     Output: 5
 * 
 *     Explanation:
 *     The largest BST subtree is:
 *           60
 *          /  \
 *         45   70
 *              / \
 *             65 80
 *     Size = 5 nodes
 */

public class j02LargestBSTInBinaryTree {

    /**
     * Node class to represent tree nodes
     * Contains data and references to left and right children
     */
    static class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    /**
     * Approach 1: Naive Approach
     * 
     * Intuition:
     * - For each node, check if the subtree rooted at that node is a BST
     * - If it is a BST, calculate its size
     * - Keep track of the maximum size found
     * 
     * Explanation:
     * - Step 1: For each node, validate if it forms a BST using validateBST()
     * - Step 2: If valid BST, calculate size using size()
     * - Step 3: Recursively check left and right subtrees
     * - Step 4: Return maximum size found
     * 
     * Time Complexity: O(n²) where n is number of nodes
     * Space Complexity: O(h) where h is height of tree
     * 
     * @param root    Root node of the binary tree
     * @return        Size of the largest BST subtree
     */
    public static int largestBst(Node root) {
        // Base case: empty tree
        if (root == null)
            return 0;
            
        // If current subtree is BST, return its size
        if (validateBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            return size(root);
        }
        
        // Recursively check left and right subtrees
        int left = largestBst(root.left);
        int right = largestBst(root.right);
        
        // Return maximum size found
        return Math.max(left, right);
    }

    /**
     * Helper Method: size
     * 
     * Intuition:
     * - Recursively count the number of nodes in a subtree
     * - Base case is when we reach a null node (size = 0)
     * - For each node, add 1 to the sum of sizes of left and right subtrees
     * 
     * Explanation:
     * - Step 1: Check if root is null (base case)
     * - Step 2: Recursively calculate size of left subtree
     * - Step 3: Recursively calculate size of right subtree
     * - Step 4: Return sum of sizes plus 1 for current node
     * 
     * Time Complexity: O(n) where n is number of nodes in subtree
     * Space Complexity: O(h) where h is height of subtree
     * 
     * @param root    Root node of the subtree to measure
     * @return        Total number of nodes in the subtree
     */
    public static int size(Node root) {
        // Base case: empty subtree has size 0
        if (root == null)
            return 0;
        // Return sum of left subtree size + right subtree size + 1 (current node)
        return size(root.left) + size(root.right) + 1;
    }

    /**
     * Helper Method: validateBST
     * 
     * Intuition:
     * - Validate if a subtree is a BST by checking value constraints
     * - Each node's value must be greater than all nodes in left subtree
     * - Each node's value must be less than all nodes in right subtree
     * - Use min and max bounds to track valid value ranges
     * 
     * Explanation:
     * - Step 1: Check if root is null (empty tree is valid BST)
     * - Step 2: Verify current node's value is within bounds
     * - Step 3: Recursively validate left subtree with updated bounds
     * - Step 4: Recursively validate right subtree with updated bounds
     * 
     * Time Complexity: O(n) where n is number of nodes in subtree
     * Space Complexity: O(h) where h is height of subtree
     * 
     * @param root    Root node of the subtree to validate
     * @param lb      Lower bound for valid node values
     * @param rb      Upper bound for valid node values
     * @return        True if subtree is a valid BST
     */
    public static boolean validateBST(Node root, long lb, long rb) {
        // Base case: empty tree is valid BST
        if (root == null)
            return true;
        // Check if current node violates BST property
        if (root.data >= rb || root.data <= lb)
            return false;
        // Validate left and right subtrees with updated bounds
        return validateBST(root.left, lb, root.data) && 
               validateBST(root.right, root.data, rb);
    }

    /**
     * Approach 2: Efficient Approach using Postorder Traversal
     * 
     * Intuition:
     * - Use postorder traversal to process children before parent
     * - For each node, track min, max values and size of BST
     * - Combine information from children to determine if current
     *   subtree is BST
     * 
     * Explanation:
     * - Step 1: Create Result class to store subtree information
     * - Step 2: Process nodes in postorder fashion
     * - Step 3: For each node, check if it can be part of BST
     * - Step 4: Update size, min, max values accordingly
     * 
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(h) where h is height of tree
     */
    static class Result {
        long min;
        long max;
        int size;

        Result() {
            this.size = 0;
            this.min = Long.MAX_VALUE;
            this.max = Long.MIN_VALUE;
        }

        Result(int size, long min, long max) {
            this.size = size;
            this.min = min;
            this.max = max;
        }
    }

    /**
     * Helper Method: largestBstEfficient
     * 
     * Intuition:
     * - Entry point for the efficient approach using postorder traversal
     * - Delegates the actual work to findLargestBSTPostroder
     * - Returns only the size from the Result object
     * 
     * Explanation:
     * - Step 1: Call findLargestBSTPostroder with root node
     * - Step 2: Extract and return the size from Result object
     * 
     * Time Complexity: O(n) where n is number of nodes in tree
     * Space Complexity: O(h) where h is height of tree
     * 
     * @param root    Root node of the binary tree
     * @return        Size of the largest BST subtree
     */
    public static int largestBstEfficient(Node root) {
        return findLargestBSTPostroder(root).size;
    }

    /**
     * Helper Method: findLargestBSTPostroder
     * 
     * Intuition:
     * - Uses postorder traversal to process children before parent
     * - For each node, determines if it can be part of a BST
     * - Tracks min, max values and size of potential BST
     * - Combines information from children to make decisions
     * 
     * Explanation:
     * - Step 1: Handle base case (null node)
     * - Step 2: Process left and right subtrees recursively
     * - Step 3: Check if current node can be part of BST
     * - Step 4: Update and return appropriate Result object
     * 
     * Time Complexity: O(n) where n is number of nodes in tree
     * Space Complexity: O(h) where h is height of tree
     * 
     * @param root    Root node of the subtree to process
     * @return        Result object containing size and value bounds
     */
    public static Result findLargestBSTPostroder(Node root) {
        // Base case: empty tree
        if (root == null) {
            return new Result();
        }
        
        // Process left and right subtrees
        Result left = findLargestBSTPostroder(root.left);
        Result right = findLargestBSTPostroder(root.right);

        // Check if current node can be part of BST
        if (root.data > left.max && root.data < right.min) {
            return new Result(
                left.size + right.size + 1,
                Math.min(root.data, left.min),
                Math.max(root.data, right.max)
            );
        }
        
        // Current subtree is not BST, return max size from children
        return new Result(
            Math.max(left.size, right.size),
            Long.MIN_VALUE,
            Long.MAX_VALUE
        );
    }

    /**
     * Main Method: Test Cases
     * 
     * Intuition:
     * - Provides comprehensive test cases for both approaches
     * - Tests various tree structures and edge cases
     * - Compares results from both naive and efficient solutions
     * 
     * Test Cases:
     * 1. Basic BST: Complex tree with multiple levels
     * 2. Empty Tree: Tests null input handling
     * 3. Single Node: Tests minimal valid BST
     * 4. Invalid BST: Tests tree that violates BST properties
     * 
     * @param args    Command line arguments (unused)
     */
    public static void main(String[] args) {
        // Test Case 1: Basic BST
        Node root1 = new Node(50);
        root1.left = new Node(30);
        root1.right = new Node(60);
        root1.left.left = new Node(5);
        root1.left.right = new Node(20);
        root1.right.left = new Node(45);
        root1.right.right = new Node(70);
        root1.right.right.left = new Node(65);
        root1.right.right.right = new Node(80);
        
        System.out.println("\nTest Case 1: Basic BST");
        System.out.println("Naive Approach: " + largestBst(root1));
        System.out.println("Efficient Approach: " + largestBstEfficient(root1));

        // Test Case 2: Empty Tree
        System.out.println("\nTest Case 2: Empty Tree");
        System.out.println("Naive Approach: " + largestBst(null));
        System.out.println("Efficient Approach: " + largestBstEfficient(null));

        // Test Case 3: Single Node
        Node root3 = new Node(1);
        System.out.println("\nTest Case 3: Single Node");
        System.out.println("Naive Approach: " + largestBst(root3));
        System.out.println("Efficient Approach: " + largestBstEfficient(root3));

        // Test Case 4: Invalid BST
        Node root4 = new Node(10);
        root4.left = new Node(20);
        root4.right = new Node(5);
        System.out.println("\nTest Case 4: Invalid BST");
        System.out.println("Naive Approach: " + largestBst(root4));
        System.out.println("Efficient Approach: " + largestBstEfficient(root4));
    }
}
