/**
 * Problem Statement:
 *     Isomorphic Trees (GeeksForGeeks)
 * 
 *     Given two binary trees, check if they are isomorphic. Two trees are called isomorphic if one of them
 *     can be obtained from the other by a series of flips, i.e., by swapping left and right children of a number of nodes.
 *     Any number of nodes at any level can have their children swapped. Two empty trees are isomorphic.
 * 
 * Input:
 *     - Root nodes of two binary trees
 *     - Trees can have any number of nodes
 *     - Node values can be any integer
 * 
 * Output:
 *     - Boolean indicating if the two trees are isomorphic
 * 
 * Example:
 *     Input:
 *         Tree1:      1
 *                    / \
 *                   2   3
 *                  /     \
 *                 4       5
 *         Tree2:      1
 *                    / \
 *                   3   2
 *                  /     \
 *                 5       4
 *     Output: true
 *     Explanation: Tree2 can be obtained from Tree1 by swapping the left and right children of node 1.
 */

public class j05IsomorphicTrees {

    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int x) {
            data = x;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Isomorphic trees (flip at root)
        Node t1 = new Node(1);
        t1.left = new Node(2);
        t1.right = new Node(3);
        t1.left.left = new Node(4);
        t1.right.right = new Node(5);

        Node t2 = new Node(1);
        t2.left = new Node(3);
        t2.right = new Node(2);
        t2.left.left = new Node(5);
        t2.right.right = new Node(4);

        System.out.println("Test Case 1: " + isIsomorphic(t1, t2)); // Expected: true

        // Test Case 2: Not isomorphic (different structure)
        Node t3 = new Node(1);
        t3.left = new Node(2);

        Node t4 = new Node(1);
        t4.right = new Node(2);

        System.out.println("Test Case 2: " + isIsomorphic(t3, t4)); // Expected: true

        // Test Case 3: Not isomorphic (different values)
        Node t5 = new Node(1);
        t5.left = new Node(2);

        Node t6 = new Node(1);
        t6.left = new Node(3);

        System.out.println("Test Case 3: " + isIsomorphic(t5, t6)); // Expected: false

        // Test Case 4: Both trees empty
        System.out.println("Test Case 4: " + isIsomorphic(null, null)); // Expected: true

        // Test Case 5: One tree empty
        System.out.println("Test Case 5: " + isIsomorphic(t1, null)); // Expected: false
    }

    /**
     * Approach: Recursive DFS with Flip Check
     * 
     * Intuition:
     * - For each pair of nodes, check:
     * 1. If both are null, they are isomorphic.
     * 2. If only one is null, not isomorphic.
     * 3. If values differ, not isomorphic.
     * 4. Otherwise, check both:
     * a) No flip: left with left, right with right
     * b) Flip: left with right, right with left
     * - If either (a) or (b) is true, the trees are isomorphic at this node.
     * 
     * Time Complexity: O(min(n, m)), where n and m are the number of nodes in the
     * two trees.
     * Space Complexity: O(h), where h is the height of the trees (recursion stack).
     */
    public static boolean isIsomorphic(Node root1, Node root2) {
        if (root1 == null && root2 == null)
            return true;
        if (root1 == null || root2 == null)
            return false;
        if (root1.data != root2.data)
            return false;
        return (isIsomorphic(root1.left, root2.left) && isIsomorphic(root1.right, root2.right))
                || (isIsomorphic(root1.left, root2.right) && isIsomorphic(root1.right, root2.left));
    }

}
