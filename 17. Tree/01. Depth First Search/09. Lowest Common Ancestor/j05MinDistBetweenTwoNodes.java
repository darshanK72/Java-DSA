/**
 * GeeksForGeeks: Min distance between two given nodes of a Binary Tree
 * 
 * Problem Statement:
 *     Given a binary tree and two node values, find the minimum distance between them.
 *     The distance between two nodes is the minimum number of edges to be traversed
 *     to reach one node from another.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Two integer values a and b representing nodes
 * 
 * Output:
 *     - Integer representing minimum distance between nodes
 * 
 * Example:
 *     Input: 
 *           1
 *          / \
 *         2   3
 *        /     \
 *       4       5
 *     
 *     a = 4, b = 5
 *     Output: 4 (path: 4->2->1->3->5)
 */

public class j05MinDistBetweenTwoNodes {

    /**
     * Node class representing each node in the binary tree
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
     * Approach: LCA + Path Distance
     * 
     * Intuition:
     * - Find LCA of both nodes first
     * - Calculate distance from LCA to each node
     * - Sum of distances gives minimum path length
     * 
     * Example visualization:
     *           1
     *          / \
     *         2   3
     *        /     \
     *       4       5
     * 
     * For nodes 4,5:
     * 1. Find LCA (node 1)
     * 2. Distance from 1 to 4 = 2
     * 3. Distance from 1 to 5 = 2
     * 4. Total distance = 2 + 2 = 4
     * 
     * Time Complexity: O(n) - need to traverse tree for LCA and distances
     * Space Complexity: O(h) - recursion stack height
     * 
     * @param root Root node of binary tree
     * @param a First node value
     * @param b Second node value
     * @return Minimum distance between nodes
     */
    public static int findDist(Node root, int a, int b) {
        Node lca = lowestCommonAncestor(root, a, b);        // Find LCA
        return dist(lca, a, 0) + dist(lca, b, 0);          // Sum distances from LCA
    }

    /**
     * Helper method to find distance from root to target node
     * 
     * Intuition:
     * - Use DFS to find target node
     * - Track distance while traversing
     * - Return -1 if node not found in current path
     * 
     * @param root Current node being processed
     * @param a Target node value
     * @param dist Current distance from start
     * @return Distance to target node or -1 if not found
     */
    public static int dist(Node root, int a, int dist) {
        if (root == null) return -1;                        // Node not found
        if (root.data == a) return dist;                    // Found target node

        int left = dist(root.left, a, dist + 1);           // Try left subtree
        if (left != -1) return left;                       // Found in left subtree
        return dist(root.right, a, dist + 1);              // Try right subtree
    }

    /**
     * Helper method to find LCA of two nodes
     * 
     * Intuition:
     * - Use standard LCA algorithm
     * - Return node if it matches either target
     * - Return LCA if found in different subtrees
     * 
     * @param root Current node being processed
     * @param a First target value
     * @param b Second target value
     * @return LCA node of a and b
     */
    public static Node lowestCommonAncestor(Node root, int a, int b) {
        if (root == null) return null;                      // Base case: empty tree
        if (root.data == a || root.data == b) return root; // Found target node

        Node left = lowestCommonAncestor(root.left, a, b); // Search left subtree
        Node right = lowestCommonAncestor(root.right, a, b); // Search right subtree

        if (left != null && right != null) return root;    // Found LCA
        if (left != null) return left;                     // LCA in left subtree
        else return right;                                 // LCA in right subtree
    }

    /**
     * Main method to test the implementation
     */
    public static void main(String[] args) {
        // Test Case 1: Regular binary tree
        /**
         *           1
         *          / \
         *         2   3
         *        /     \
         *       4       5
         */
        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(3);
        root1.left.left = new Node(4);
        root1.right.right = new Node(5);
        
        System.out.println("Test Case 1 - Distance between nodes 4 and 5");
        System.out.println("Expected: 4");
        System.out.println("Output: " + findDist(root1, 4, 5));

        System.out.println("\nTest Case 2 - Distance between nodes 4 and 2");
        System.out.println("Expected: 1");
        System.out.println("Output: " + findDist(root1, 4, 2));
    }
}
