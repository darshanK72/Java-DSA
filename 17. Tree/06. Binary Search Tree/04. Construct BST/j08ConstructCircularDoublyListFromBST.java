/**
 * GeeksForGeeks - Convert Binary Tree to Circular Doubly Linked List
 * 
 * Problem Statement:
 *     Given a Binary Tree, convert it to a Circular Doubly Linked List (DLL) in-place.
 *     The left and right pointers in nodes are to be used as previous and next pointers
 *     respectively in converted DLL. The order of nodes in DLL must be same as inorder
 *     traversal of the given Binary Tree.
 * 
 * Input:
 *     - root: Root node of binary tree
 *     - Number of nodes in range [0, 10^4]
 *     - Node values in range [-10^3, 10^3]
 * 
 * Output:
 *     - Head of the converted Circular Doubly Linked List
 * 
 * Example:
 *     Input:        10
 *                 /    \
 *                20    30
 *               /  \  
 *              40  60
 * 
 *     Output: 40 <-> 20 <-> 60 <-> 10 <-> 30 <-> (back to 40)
 *     (Circular DLL should be in inorder traversal)
 */

public class j08ConstructCircularDoublyListFromBST {

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
     * Approach: Single Pass Inorder Traversal with Head-Tail Tracking
     * 
     * Intuition:
     * - Use inorder traversal to process nodes in sorted order
     * - Track both head and tail nodes using an array
     * - Finally connect head and tail to make it circular
     * 
     * Explanation:
     * - Step 1: Initialize result array
     *          - result[0] stores head of circular DLL
     *          - result[1] stores tail of circular DLL
     *          - Using array allows modification of references across recursive calls
     * 
     * - Step 2: Perform inorder traversal
     *          - Processes nodes in sorted order (left -> root -> right)
     *          - Builds DLL by updating links during traversal
     * 
     * - Step 3: Make DLL circular
     *          - Connect head and tail nodes
     *          - head.left points to tail (backward link)
     *          - tail.right points to head (forward link)
     * 
     * Time Complexity: O(n) - Single traversal of all nodes
     * Space Complexity: O(h) - Recursion stack height where h is tree height
     * 
     * @param root Root of binary tree
     * @return Head node of converted circular doubly linked list
     */
    public static Node bToDLL(Node root) {
        // Array to store head[0] and tail[1] references
        Node[] result = new Node[] { null, null };
        
        // Convert tree to DLL using inorder traversal
        convertToIncreasingBST(root, result);
        
        // Make the DLL circular by connecting head and tail
        Node head = result[0];
        Node tail = result[1];
        head.left = tail;
        tail.right = head;
        
        return head;
    }

    /**
     * Helper Method: Inorder Traversal for Circular DLL Conversion
     * 
     * Intuition:
     * - Process nodes in inorder (left, root, right)
     * - Maintain head and tail references in array
     * - Update double links during traversal
     * 
     * Explanation:
     * - Step 1: Handle base case
     *          - Return if current node is null to end recursion
     * 
     * - Step 2: Process left subtree
     *          - Recursively convert left subtree first
     *          - Ensures smaller values are processed before current node
     * 
     * - Step 3: Process current node
     *          - If first node (result[0] == null):
     *              * Set as head of DLL (result[0])
     *          - Otherwise:
     *              * Connect current node with previous tail node
     *              * Update double links between them
     *          - Update tail reference (result[1]) to current node
     * 
     * - Step 4: Process right subtree
     *          - Recursively convert right subtree
     *          - Ensures larger values are processed after current node
     * 
     * Time Complexity: O(n) - Visit each node once
     * Space Complexity: O(h) - Recursion stack height
     * 
     * @param root Current node being processed
     * @param result Array containing [head, tail] references
     */
    public static void convertToIncreasingBST(Node root, Node[] result) {
        if (root == null)
            return;
            
        // Process left subtree
        convertToIncreasingBST(root.left, result);
        
        // Handle first node (head) case
        if (result[0] == null) {
            result[0] = root; // First node becomes head
        } else {
            result[1].right = root; // Link previous node to current
            root.left = result[1];  // Link current node back to previous
        }
        
        // Update tail reference
        result[1] = root;
        
        // Process right subtree
        convertToIncreasingBST(root.right, result);
    }
}
