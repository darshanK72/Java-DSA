/**
 * GeeksForGeeks - Construct BST from Level Order Traversal
 * 
 * Problem Statement:
 *     Given an array representing level order traversal of a Binary Search Tree,
 *     construct the BST. All nodes in BST have unique values.
 * 
 * Input:
 *     - arr[]: Array containing level order traversal (1 <= length <= 100)
 *     - All values are unique and in range [-10^8, 10^8]
 * 
 * Output:
 *     - Root node of the constructed BST
 * 
 * Example:
 *     Input: arr[] = {7,4,12,3,6,8,1,5,10}
 *     Output: Tree Structure:
 *            7
 *           / \
 *          4   12
 *         / \  /
 *        3   6 8
 *       /   /   \
 *      1   5    10
 */
import java.util.LinkedList;
import java.util.Queue;

public class j04ConstructBSTFromLevelOrder {

    /**
     * Node class for Binary Search Tree
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
     * Approach: Queue Based Construction
     * 
     * Intuition:
     * - First element in level order is always the root
     * - For each node, elements smaller go to left subtree
     * - Elements larger go to right subtree
     * - Use separate queues to partition elements
     * 
     * Time Complexity: O(n^2) - Each element processed for each level
     * Space Complexity: O(n) - Queue storage
     * 
     * @param arr Array containing level order traversal
     * @return Root of constructed BST
     */
    public static Node constructBST(int[] arr) {
        // Convert array to queue for processing
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);
        }
        return convertLevelOrderToBST(queue);
    }

    /**
     * Helper Method: Convert Level Order Queue to BST
     * 
     * Intuition:
     * - First element becomes root of current subtree
     * - Partition remaining elements into left and right queues
     * - Recursively construct left and right subtrees
     * 
     * Explanation:
     * 1. If queue empty, return null (base case)
     * 2. Create root from first element
     * 3. Partition remaining elements based on value comparison
     * 4. Recursively build left and right subtrees
     * 
     * Time Complexity: O(n) for current level partitioning
     * Space Complexity: O(n) for queue storage
     * 
     * @param queue Queue containing level order elements
     * @return Root of constructed subtree
     */
    public static Node convertLevelOrderToBST(Queue<Integer> queue) {
        // Base case: empty queue
        if (queue.isEmpty())
            return null;
            
        // Create root from first element
        int val = queue.poll();
        Node root = new Node(val);
        
        // Create separate queues for left and right subtrees
        Queue<Integer> q1 = new LinkedList<>();  // For elements < root
        Queue<Integer> q2 = new LinkedList<>();  // For elements > root
        
        // Partition remaining elements
        while (!queue.isEmpty()) {
            if (queue.peek() < root.data)
                q1.add(queue.poll());  // Add to left subtree queue
            else
                q2.add(queue.poll());  // Add to right subtree queue
        }
        
        // Recursively construct subtrees
        root.left = convertLevelOrderToBST(q1);
        root.right = convertLevelOrderToBST(q2);
        
        return root;
    }
}