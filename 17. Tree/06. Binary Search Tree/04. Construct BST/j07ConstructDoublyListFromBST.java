/**
 * GeeksForGeeks - Binary Tree to DLL
 * 
 * Problem Statement:
 *     Given a Binary Tree (BT), convert it to a Doubly Linked List (DLL) in-place.
 *     The left and right pointers in nodes are to be used as previous and next
 *     pointers respectively in converted DLL.
 * 
 * Input:
 *     - root: Root node of binary tree
 *     - Number of nodes in range [0, 10^4]
 *     - Node values in range [-10^3, 10^3]
 * 
 * Output:
 *     - Head of the converted Doubly Linked List
 * 
 * Example:
 *     Input:        10
 *                 /    \
 *                20    30
 *               /  \  
 *              40  60
 * 
 *     Output: 40 <-> 20 <-> 60 <-> 10 <-> 30
 *     (DLL should be in inorder traversal)
 */
public class j07ConstructDoublyListFromBST {

    /**
     * Definition for tree/DLL node
     */
    static class Node {
        int data;
        Node left, right;  // left as prev, right as next in DLL

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    /**
     * Approach: Single Pass Inorder Traversal
     * 
     * Intuition:
     * - Use inorder traversal to process nodes in sorted order
     * - Maintain previous node reference to update double links
     * - Use dummy node to handle head case
     * 
     * Explanation:
     * - Step 1: Create a dummy node (-1) to handle the head case uniformly
     *          - Avoids special handling for first node
     *          - Simplifies link updates
     * 
     * - Step 2: Perform inorder traversal
     *          - Visit left subtree first (smaller values)
     *          - Process current node by updating double links
     *          - Visit right subtree last (larger values)
     * 
     * - Step 3: Clean up and return result
     *          - Set head as dummy.right (first actual node)
     *          - Remove dummy node connections
     *          - Return the head of DLL
     * 
     * Time Complexity: O(n) - Single traversal of all nodes
     * Space Complexity: O(h) - Recursion stack height
     * 
     * @param root Root of binary tree
     * @return Head node of converted doubly linked list
     */
    public static Node bToDLL(Node root) {
        // Create dummy node to handle head case
        Node dummy = new Node(-1);
        Node[] prev = new Node[] { dummy };
        
        // Convert tree to DLL using inorder traversal
        convertToIncreasingBST(root, prev);
        
        // Setup head node and clean up dummy connections
        Node head = dummy.right;
        head.left = null;
        dummy.right = null;
        
        return head;
    }

    /**
     * Helper Method: Inorder Traversal for DLL Conversion
     * 
     * Intuition:
     * - Process nodes in inorder (left, root, right)
     * - Update double links during traversal
     * - Previous node reference passed as array for modification
     * 
     * Explanation:
     * - Step 1: Base case
     *          - Return if current node is null
     * 
     * - Step 2: Process left subtree recursively
     *          - Ensures smaller values are processed first
     * 
     * - Step 3: Update DLL connections
     *          - Connect current node with previous node
     *          - Update previous pointer for next iteration
     * 
     * - Step 4: Process right subtree recursively
     *          - Handles larger values last
     * 
     * Time Complexity: O(n) - Visit each node once
     * Space Complexity: O(h) - Recursion stack height
     * 
     * @param root Current node being processed
     * @param prev Array containing previous node reference
     */
    public static void convertToIncreasingBST(Node root, Node[] prev) {
        if (root == null)
            return;
            
        // Process left subtree
        convertToIncreasingBST(root.left, prev);
        
        // Update double links
        prev[0].right = root;  // next pointer
        root.left = prev[0];   // prev pointer
        prev[0] = root;        // update previous node
        
        // Process right subtree
        convertToIncreasingBST(root.right, prev);
    }
}
