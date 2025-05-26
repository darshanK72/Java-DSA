/**
 * Custom Problem: Serialize and Deserialize N-ary Tree
 * 
 * Problem Statement:
 *     Design an algorithm to serialize and deserialize an N-ary tree. An N-ary 
 *     tree is a tree in which each node has at most N children. Each node 
 *     contains an integer value and a list of its children.
 * 
 * Input:
 *     - TreeNode<Integer> root (root node of N-ary tree)
 *     - Each node has data and ArrayList of children
 *     - Node values are integers
 * 
 * Output:
 *     - String (for serialization)
 *     - TreeNode<Integer> (for deserialization)
 * 
 * Example:
 *     Input Tree:
 *          1
 *        / | \
 *       2  3  4
 *      /
 *     5
 *     
 *     Serialized: "1,2,5,N,N,3,N,4,N"
 */

import java.util.ArrayList;
import java.util.Stack;

public class j06SerializeDeserializeNAryTree {

    /**
     * Generic TreeNode class for N-ary tree
     */
    static class TreeNode<T> {
        public T data;
        public ArrayList<TreeNode<T>> child;

        TreeNode(T data) {
            this.data = data;
            child = new ArrayList<TreeNode<T>>();
        }
    }

    /**
     * Main method to test serialization and deserialization
     * 
     * Intuition:
     * - First serialize the tree to string
     * - Then deserialize back to tree structure
     * 
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(n) for StringBuilder storage
     * 
     * @param root    Root of N-ary tree
     * @return       Root of deserialized tree
     */
    public static TreeNode<Integer> SerDeser(TreeNode<Integer> root) {
        // Create StringBuilder for serialization
        StringBuilder serialized = new StringBuilder();
        
        // Serialize the tree
        seralize(root, serialized);
        
        // Deserialize back to tree
        TreeNode<Integer> deserialized = deserialize(serialized.toString());
        return deserialized;
    }

    /**
     * Serializes N-ary tree to string
     * 
     * Intuition:
     * - Use preorder traversal (root, children)
     * - Mark end of children list with 'N'
     * - Use comma as delimiter
     * 
     * Explanation:
     * - Append current node value
     * - If leaf node, append 'N'
     * - Otherwise, recursively serialize children and append 'N'
     * 
     * Time Complexity: O(n) for visiting each node
     * Space Complexity: O(h) for recursion stack
     * 
     * @param root    Current node being processed
     * @param out     StringBuilder to store serialized string
     */
    public static void seralize(TreeNode<Integer> root, StringBuilder out) {
        // Append current node value
        out.append(root.data + ",");
        
        // Handle leaf node case
        if (root.child.size() == 0) {
            out.append("N,");
        } else {
            // Recursively serialize all children
            for (TreeNode<Integer> node : root.child) {
                seralize(node, out);
            }
            // Mark end of children list
            out.append("N,");
        }
    }

    /**
     * Deserializes string back to N-ary tree
     * 
     * Intuition:
     * - Use stack to track parent nodes
     * - 'N' marks completion of current node's children
     * 
     * Explanation:
     * - Split input string into values
     * - Use stack to maintain parent-child relationships
     * - Pop from stack when 'N' is encountered
     * 
     * Time Complexity: O(n) where n is length of input string
     * Space Complexity: O(h) for stack storage
     * 
     * @param str    Serialized string representation
     * @return      Root of deserialized tree
     */
    public static TreeNode<Integer> deserialize(String str) {
        // Initialize stack and split input string
        Stack<TreeNode<Integer>> stack = new Stack<>();
        String[] values = str.split(",");
        
        // Create root node
        int value = Integer.parseInt(values[0]);
        TreeNode<Integer> root = new TreeNode<Integer>(value);
        stack.push(root);
        
        // Process remaining values
        for (int i = 1; i < values.length; i++) {
            if (values[i].equals("N")) {
                // End of current node's children
                stack.pop();
            } else {
                // Create new node and add to parent's children
                value = Integer.parseInt(values[i]);
                TreeNode<Integer> node = new TreeNode<Integer>(value);
                stack.peek().child.add(node);
                stack.add(node);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic tree
        System.out.println("\nBasic Tree Test:");
        TreeNode<Integer> root1 = new TreeNode<>(1);
        root1.child.add(new TreeNode<>(2));
        root1.child.add(new TreeNode<>(3));
        root1.child.add(new TreeNode<>(4));
        root1.child.get(0).child.add(new TreeNode<>(5));
        TreeNode<Integer> result1 = SerDeser(root1);
        StringBuilder serializedResult1 = new StringBuilder();
        seralize(result1, serializedResult1);
        System.out.println("Serialized: " + serializedResult1.toString());

        // Test Case 2: Single node
        System.out.println("\nSingle Node Test:");
        TreeNode<Integer> root2 = new TreeNode<>(1);
        TreeNode<Integer> result2 = SerDeser(root2);

         StringBuilder serializedResult2 = new StringBuilder();
        seralize(result2, serializedResult2);
        System.out.println("Serialized: " + serializedResult2.toString());

        // Test Case 3: Linear tree
        System.out.println("\nLinear Tree Test:");
        TreeNode<Integer> root3 = new TreeNode<>(1);
        root3.child.add(new TreeNode<>(2));
        root3.child.get(0).child.add(new TreeNode<>(3));
        TreeNode<Integer> result3 = SerDeser(root3);

         StringBuilder serializedResult3 = new StringBuilder();
        seralize(result3, serializedResult3);
        System.out.println("Serialized: " + serializedResult3.toString());
    }
}
