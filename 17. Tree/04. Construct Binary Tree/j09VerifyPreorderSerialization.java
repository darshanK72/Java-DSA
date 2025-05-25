/**
 * LeetCode 331. Verify Preorder Serialization of a Binary Tree
 * 
 * Problem Statement:
 *     One way to serialize a binary tree is to use preorder traversal. When we 
 *     encounter a non-null node, we record the node's value. If it is a null node,
 *     we record using a sentinel value such as '#'
 * 
 * Input:
 *     - String preorder (comma-separated string of node values and '#')
 * 
 * Output:
 *     - boolean (true if string represents valid preorder serialization)
 * 
 * Example:
 *     Input: preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#"
 *     Output: true
 * 
 *     Explanation:
 *          9
 *         / \
 *        3   2
 *       / \   \
 *      4   1   6
 */

import java.util.Stack;

public class j09VerifyPreorderSerialization {
    
    /**
     * Helper class to track node state during traversal
     */
    static class Pair {
        int node;   // Node value
        int state;  // 0: no child visited, 1: left child visited
        
        Pair(int node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    /**
     * Approach 1: Stack-based Verification
     * 
     * Intuition:
     * - Use stack to track nodes and their states
     * - State indicates whether left child has been processed
     * - Empty stack at end means valid serialization
     * 
     * Explanation:
     * - Split input string into values array
     * - Process each value sequentially
     * - Track node states using Pair class
     * - Pop nodes when both children are processed
     * 
     * Time Complexity: O(n) where n is length of input string
     * Space Complexity: O(h) where h is height of tree
     * 
     * @param preorder   Comma-separated string of node values and '#'
     * @return          true if valid serialization, false otherwise
     */
    public static boolean isValidSerialization(String preorder) {
        // Handle single null node case
        if (preorder.equals("#")) 
            return true;
            
        // Initialize stack and split input
        Stack<Pair> stack = new Stack<>();
        String[] values = preorder.split(",");
        boolean rootFound = false;
        
        // Process each value in sequence
        for (int i = 0; i < values.length; i++) {
            // Handle empty stack case (root node)
            if (stack.isEmpty()) {
                if (rootFound) 
                    return false;
                rootFound = true;
                
                // First node must be non-null
                if (!values[i].equals("#")) {
                    stack.add(new Pair(Integer.parseInt(values[i]), 0));
                } else {
                    return false;
                }
            } else {
                // Get current top node state
                Pair pair = stack.peek();
                
                // Process left child
                if (pair.state == 0) {
                    pair.state = 1;
                    if (!values[i].equals("#")) {
                        stack.add(new Pair(Integer.parseInt(values[i]), 0));
                    }
                } 
                // Process right child
                else if (pair.state == 1) {
                    stack.pop();
                    if (!values[i].equals("#")) {
                        stack.add(new Pair(Integer.parseInt(values[i]), 0));
                    }
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * Approach 2: Slot counting (Optimized)
     * 
     * Intuition:
     * - Each non-null node provides 2 slots for children
     * - Each node (null or non-null) takes 1 slot
     * - Valid tree should use exactly all slots
     * 
     * Explanation:
     * - Initialize slots as 1 (for root)
     * - For each non-null node: slots += 1 (2 new - 1 used)
     * - For each null node: slots -= 1 (1 used)
     * - Valid if slots = 0 at end
     * 
     * Time Complexity: O(n) where n is length of input string
     * Space Complexity: O(1) constant space
     * 
     * @param preorder   Comma-separated string of node values and '#'
     * @return          true if valid serialization, false otherwise
     */
    public static boolean isValidSerializationOptimized(String preorder) {
        // Initialize available slots (1 for root)
        int slots = 1;
        
        // Process each value
        for (String val : preorder.split(",")) {
            // Each node takes one slot
            slots--;
            
            // If we run out of slots mid-way, invalid
            if (slots < 0) return false;
            
            // Non-null node adds two new slots
            if (!val.equals("#")) {
                slots += 2;
            }
        }
        
        // Valid if we used exactly all slots
        return slots == 0;
    }

    public static void main(String[] args) {
        // Test Case 1: Valid tree
        System.out.println("\nValid Tree Cases:");
        String test1 = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        System.out.println("Stack approach: " + isValidSerialization(test1));
        System.out.println("Optimized approach: " + isValidSerializationOptimized(test1));

        // Test Case 2: Single null node
        System.out.println("\nSingle Node Cases:");
        String test2 = "#";
        System.out.println("Stack approach: " + isValidSerialization(test2));
        System.out.println("Optimized approach: " + isValidSerializationOptimized(test2));

        // Test Case 3: Invalid sequences
        System.out.println("\nInvalid Cases:");
        String test3 = "1,#";
        System.out.println("Stack approach: " + isValidSerialization(test3));
        System.out.println("Optimized approach: " + isValidSerializationOptimized(test3));
    }
}
