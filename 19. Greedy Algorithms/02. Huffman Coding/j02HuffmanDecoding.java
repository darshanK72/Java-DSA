/**
 * GeeksForGeeks - Huffman Decoding
 * 
 * Problem Statement:
 *     Given a Huffman tree root and a binary string, decode the binary string back
 *     to the original text. The binary string is encoded using the Huffman tree
 *     where '0' represents a left branch and '1' represents a right branch.
 * 
 * Input:
 *     - root: Root node of the Huffman tree
 *     - binaryString: String containing '0's and '1's representing the encoded text
 * 
 * Output:
 *     - String containing the decoded text
 * 
 * Example:
 *     Input: 
 *         root = Huffman tree with characters and their codes
 *         binaryString = "1001011"
 *     Output: "abc"
 * 
 *     Explanation:
 *     Following the Huffman tree:
 *     - '1' -> right -> 'a'
 *     - '0' -> left -> 'b'
 *     - '0' -> left -> 'c'
 */

public class j02HuffmanDecoding {

    /**
     * MinHeapNode class represents a node in the Huffman tree
     * - data: character stored in the node (if leaf node)
     * - freq: frequency of the character
     * - left, right: child nodes for binary tree structure
     * - top: parent node reference (used in tree construction)
     */
    static class MinHeapNode {
        char data;
        int freq;
        MinHeapNode left, right, top;

        MinHeapNode(char c, int freq) {
            left = right = null;
            this.data = c;
            this.freq = freq;
        }
    }

    /**
     * Approach: Tree Traversal
     * 
     * Intuition:
     * - Huffman decoding follows the same tree structure used for encoding
     * - '0' means go left, '1' means go right in the tree
     * - When we reach a leaf node (node with actual character), we've found a
     *   decoded character
     * 
     * Explanation:
     * 1. Start from root of Huffman tree
     * 2. For each bit in binary string:
     *    - If bit is '0', move to left child
     *    - If bit is '1', move to right child
     * 3. When we reach a leaf node (node with character):
     *    - Add character to output
     *    - Reset to root for next character
     * 4. Continue until all bits are processed
     * 
     * Time Complexity: O(N) where N is length of binary string
     * - Each bit requires one tree traversal step
     * - Tree traversal is O(1) as tree is balanced
     * 
     * Space Complexity: O(1) excluding output
     * - Only uses a single node pointer for traversal
     * - Output string space is O(M) where M is length of decoded text
     * 
     * @param root          Root node of the Huffman tree
     * @param binaryString  Encoded binary string to decode
     * @return             Decoded text string
     */
    public static String decodeHuffmanData(MinHeapNode root, String binaryString) {
        // Initialize output string builder
        StringBuilder out = new StringBuilder();
        
        // Start from root node
        MinHeapNode node = root;
        
        // Process each bit in the binary string
        for (char c : binaryString.toCharArray()) {
            // Traverse left or right based on bit value
            if (c == '0')
                node = node.left;
            else
                node = node.right;
                
            // If we reach a leaf node (has character)
            if (node.data != '$') {
                // Add character to output
                out.append(node.data);
                // Reset to root for next character
                node = root;
            }
        }
        return out.toString();
    }

    public static void main(String[] args) {
        // Test Case 1: Basic decoding
        System.out.println("\nBasic Test Case:");
        // Create a sample Huffman tree
        MinHeapNode root = new MinHeapNode('$', 0);
        root.left = new MinHeapNode('a', 1);
        root.right = new MinHeapNode('$', 0);
        root.right.left = new MinHeapNode('b', 1);
        root.right.right = new MinHeapNode('c', 1);
        
        String binaryString1 = "1001011";
        System.out.println("Input binary string: " + binaryString1);
        System.out.println("Decoded output: " + decodeHuffmanData(root, binaryString1));

        // Test Case 2: Single character
        System.out.println("\nSingle Character Test:");
        MinHeapNode root2 = new MinHeapNode('a', 1);
        String binaryString2 = "0";
        System.out.println("Input binary string: " + binaryString2);
        System.out.println("Decoded output: " + decodeHuffmanData(root2, binaryString2));

        // Test Case 3: Empty string
        System.out.println("\nEmpty String Test:");
        String binaryString3 = "";
        System.out.println("Input binary string: " + binaryString3);
        System.out.println("Decoded output: " + decodeHuffmanData(root, binaryString3));
    }
}
