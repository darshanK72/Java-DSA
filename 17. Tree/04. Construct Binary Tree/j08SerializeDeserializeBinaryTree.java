/**
 * LeetCode 297. Serialize and Deserialize Binary Tree
 * 
 * Problem Statement:
 *     Serialization is the process of converting a data structure or object into a 
 *     sequence of bits so that it can be stored in a file or memory buffer, or 
 *     transmitted across a network connection link to be reconstructed later in the
 *     same or another computer environment.
 * 
 * Input:
 *     - TreeNode root (Binary Tree root node, values in range [-1000, 1000])
 *     - String data (for deserialization)
 * 
 * Output:
 *     - String (serialized form of tree)
 *     - TreeNode (deserialized tree)
 * 
 * Example:
 *     Input: root = [1,2,3,null,null,4,5]
 *     Output: "1,2,3,N,N,4,5"
 * 
 *     Explanation:
 *          1
 *         / \
 *        2   3
 *           / \
 *          4   5
 */

import java.util.LinkedList;
import java.util.Queue;

public class j08SerializeDeserializeBinaryTree {
    
    /**
     * TreeNode class representing a node in the binary tree
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * Approach 1: DFS (Preorder Traversal)
     * 
     * Intuition:
     * - Use preorder traversal (root->left->right) to maintain tree structure
     * - Mark null nodes with 'N' to preserve structure information
     * - Use comma as delimiter for easy string splitting
     * 
     * Explanation:
     * - Step 1: Serialize using preorder traversal
     * - Step 2: Use StringBuilder for efficient string concatenation
     * - Step 3: Deserialize by reconstructing tree from preorder sequence
     * 
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(h) where h is height of tree (recursion stack)
     */
    static class BinaryTreeDFSSerializer {
        /**
         * Serializes a binary tree to a string.
         * 
         * Intuition:
         * - Use StringBuilder to build string efficiently
         * - Leverage recursive DFS to maintain tree structure
         * - Each node's value followed by its subtrees maintains preorder property
         * 
         * Explanation:
         * - Create StringBuilder to store serialized string
         * - Use helper method for recursive serialization
         * - Return final string with all nodes processed
         * 
         * Time Complexity: O(n) where n is number of nodes
         * Space Complexity: O(h) where h is height of tree (recursion stack)
         * 
         * @param root    Root node of binary tree (null allowed)
         * @return        Serialized string representation of tree
         */
        public String serialize(TreeNode root) {
            // Initialize StringBuilder for efficient string concatenation
            StringBuilder out = new StringBuilder();
            // Start recursive serialization from root
            ser(root, out);
            // Convert final StringBuilder to string
            return out.toString();
        }

        /**
         * Helper method for recursive DFS serialization
         * 
         * Intuition:
         * - Process current node first (preorder)
         * - Mark null nodes with 'N' to preserve tree structure
         * - Use comma as delimiter for easy parsing during deserialization
         * 
         * Explanation:
         * - Handle null nodes by appending 'N,' marker
         * - For non-null nodes: append value, then process left and right
         * - Recursive calls maintain preorder traversal order
         * 
         * Time Complexity: O(n) for visiting each node once
         * Space Complexity: O(h) for recursion stack depth
         * 
         * @param root    Current node being processed (can be null)
         * @param out     StringBuilder to accumulate the serialized string
         */
        private void ser(TreeNode root, StringBuilder out) {
            // Handle null node case by marking with 'N'
            if (root == null) {
                out.append("N,");
                return;
            }
            // Append current node's value followed by comma
            out.append(root.val + ",");
            // Recursively process left subtree
            ser(root.left, out);
            // Recursively process right subtree
            ser(root.right, out);
        }

        /**
         * Deserializes a binary tree from its string representation
         * 
         * Intuition:
         * - Split string into array of values
         * - Process values in preorder sequence
         * - Track current position using index array
         * 
         * Explanation:
         * - Convert comma-separated string to array
         * - Use helper method to reconstruct tree recursively
         * - Index array maintains position across recursive calls
         * 
         * Time Complexity: O(n) where n is number of nodes
         * Space Complexity: O(h) for recursion stack
         * 
         * @param data    Comma-separated string of node values
         * @return        Root node of reconstructed tree
         */
        public TreeNode deserialize(String data) {
            // Split input string into array using comma delimiter
            String[] values = data.split(",");
            // Start recursive deserialization with index tracker
            return des(values, new int[] { 0 });
        }

        /**
         * Helper method for recursive DFS deserialization
         * 
         * Intuition:
         * - Process values in preorder sequence
         * - Use shared index array to track position
         * - Build tree by connecting nodes recursively
         * 
         * Explanation:
         * - Check for end of values array
         * - Handle null markers ('N')
         * - Create nodes and connect children recursively
         * - Return constructed subtree
         * 
         * Time Complexity: O(n) for processing each value once
         * Space Complexity: O(h) for recursion stack depth
         * 
         * @param values    Array of string values from split input
         * @param index    Single-element array to track current position
         * @return         Root of current subtree
         */
        private TreeNode des(String[] values, int[] index) {
            // Check if we've processed all values
            if (index[0] == values.length)
                return null;
            // Handle null node marker
            if (values[index[0]].equals("N")) {
                index[0]++;
                return null;
            }
            // Parse current value and increment index
            int val = Integer.parseInt(values[index[0]++]);
            // Create new node with current value
            TreeNode node = new TreeNode(val);
            // Recursively construct left subtree
            node.left = des(values, index);
            // Recursively construct right subtree
            node.right = des(values, index);
            return node;
        }
    }

    /**
     * Approach 2: BFS (Level Order Traversal)
     * 
     * Intuition:
     * - Use level order traversal to serialize tree level by level
     * - Queue helps in maintaining level order of nodes
     * - Null nodes are marked to preserve structure
     * 
     * Explanation:
     * - Step 1: Use queue for level order traversal
     * - Step 2: Process each level and append to string
     * - Step 3: Reconstruct tree level by level using queue
     * 
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(w) where w is max width of tree
     */
    static class BinaryTreeBFSSerializer {
        /**
         * Serializes binary tree using BFS approach
         * 
         * Intuition:
         * - Process tree level by level using queue
         * - Include null children to preserve structure
         * - Append values in level order
         * 
         * Explanation:
         * - Use queue to track nodes at each level
         * - Process each level entirely before moving to next
         * - Mark null nodes with 'N'
         * 
         * Time Complexity: O(n) where n is number of nodes
         * Space Complexity: O(w) where w is max width of tree
         * 
         * @param root    Root node of binary tree
         * @return        Level-order serialized string
         */
        public String serialize(TreeNode root) {
            // Handle empty tree case
            if (root == null)
                return "N";

            // Initialize StringBuilder and queue
            StringBuilder out = new StringBuilder();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            // Process nodes level by level
            while (!queue.isEmpty()) {
                int size = queue.size();
                // Process all nodes at current level
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    if (node == null) {
                        // Mark null nodes with 'N'
                        out.append("N,");
                    } else {
                        // Append node value and add children to queue
                        out.append(node.val + ",");
                        queue.add(node.left);
                        queue.add(node.right);
                    }
                }
            }
            return out.toString();
        }

        /**
         * Deserializes string back to binary tree using BFS
         * 
         * Intuition:
         * - Process values level by level
         * - Use queue to track parent nodes
         * - Build tree by connecting children to parents
         * 
         * Explanation:
         * - Parse input string into values array
         * - Use queue to process nodes in level order
         * - Connect children to current parent node
         * 
         * Time Complexity: O(n) where n is number of nodes
         * Space Complexity: O(w) where w is max width of tree
         * 
         * @param data    Serialized string representation
         * @return        Root of reconstructed tree
         */
        public TreeNode deserialize(String data) {
            // Split input string into values array
            String[] values = data.split(",");
            // Handle empty tree case
            if (values[0].equals("N"))
                return null;

            // Initialize queue and create root node
            Queue<TreeNode> queue = new LinkedList<>();
            int index = 0;
            int value = Integer.parseInt(values[index++]);
            TreeNode root = new TreeNode(value);
            queue.add(root);

            // Process nodes level by level
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    // Get current parent node
                    TreeNode node = queue.poll();
                    
                    // Process left child
                    if (!values[index].equals("N")) {
                        value = Integer.parseInt(values[index]);
                        node.left = new TreeNode(value);
                        queue.add(node.left);
                    }
                    index++;
                    
                    // Process right child
                    if (!values[index].equals("N")) {
                        value = Integer.parseInt(values[index]);
                        node.right = new TreeNode(value);
                        queue.add(node.right);
                    }
                    index++;
                }
            }
            return root;
        }
    }

    public static void main(String[] args) {
        
        // Example tree for testing
        // Constructing the following binary tree:
        //          1
        //         / \
        //        2   3
        //           / \
        //          4   5
        
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        
        // Initialize serializers
        BinaryTreeDFSSerializer dfs = new BinaryTreeDFSSerializer();
        BinaryTreeBFSSerializer bfs = new BinaryTreeBFSSerializer();
        
        // Test basic tree serialization
        System.out.println("\nBasic Test Cases:");
        System.out.println("DFS Serialization: " + dfs.serialize(root));
        System.out.println("BFS Serialization: " + bfs.serialize(root));

        // Test edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Null tree (DFS): " + dfs.serialize(null));
        System.out.println("Single node (DFS): " + dfs.serialize(new TreeNode(1)));

        // Test special case: skewed tree
        System.out.println("\nSpecial Cases:");
        TreeNode skewedTree = new TreeNode(1);
        skewedTree.right = new TreeNode(2);
        skewedTree.right.right = new TreeNode(3);
        System.out.println("Skewed tree (DFS): " + dfs.serialize(skewedTree));
    }
}