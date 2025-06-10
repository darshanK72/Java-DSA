/*-
 * GeeksForGeeks - Huffman Encoding
 * 
 * Problem Statement:
 *     Given a string S and an array of frequencies f[] of size N, find the Huffman
 *     encoding for each character in S. Huffman encoding is a variable-length
 *     encoding scheme where frequently occurring characters are assigned shorter
 *     codes.
 * 
 * Input:
 *     - S: String containing N distinct characters
 *     - f[]: Array of size N containing frequency of each character
 *     - N: Length of string S and array f[]
 * 
 * Output:
 *     - ArrayList<String> containing Huffman codes for each character in S
 * 
 * Example:
 *     Input: S = "abcdef", f[] = {5, 9, 12, 13, 16, 45}
 *     Output: ["1100", "1101", "100", "101", "111", "0"]
 * 
 *     Explanation:
 *     Character 'a' appears 5 times -> code "1100"
 *     Character 'b' appears 9 times -> code "1101"
 *     Character 'c' appears 12 times -> code "100"
 *     Character 'd' appears 13 times -> code "101"
 *     Character 'e' appears 16 times -> code "111"
 *     Character 'f' appears 45 times -> code "0"
 */

import java.util.ArrayList;
import java.util.PriorityQueue;

public class j01HuffmanEncoding {
    /*-
     * Node class represents a node in the Huffman tree
     * - c: character stored in the node (if leaf node)
     * - freq: frequency of the character
     * - left, right: child nodes
     * - compareTo: compares nodes based on frequency for PriorityQueue
     */
    static class Node implements Comparable<Node> {
        char c;
        int freq;
        Node left, right;

        Node() {
            this.c = '#';
            this.freq = 0;
        }

        Node(int freq) {
            this.c = '#';
            this.freq = freq;
        }

        Node(char c, int freq) {
            this.c = c;
            this.freq = freq;
        }

        @Override
        public int compareTo(Node other) {
            if (this.freq < other.freq)
                return -1;
            else
                return 1;
        }
    }

    /*-
     * Approach: Greedy Algorithm using Min-Heap
     * 
     * Intuition:
     * - Huffman coding assigns shorter codes to more frequent characters
     * - Uses a binary tree where left edges represent '0' and right edges '1'
     * - Builds tree by repeatedly combining two nodes with lowest frequencies
     * 
     * Explanation:
     * 1. Create a min-heap (PriorityQueue) of nodes containing characters and frequencies
     * 2. While heap has more than one node:
     *    - Remove two nodes with lowest frequencies
     *    - Create new node with sum of frequencies
     *    - Add new node back to heap
     * 3. Last remaining node is root of Huffman tree
     * 4. Traverse tree to generate codes (0 for left, 1 for right)
     * 
     * Time Complexity: O(N log N) where N is number of characters
     * - Building heap: O(N)
     * - Each heap operation: O(log N)
     * - Total operations: O(N log N)
     * 
     * Space Complexity: O(N)
     * - PriorityQueue: O(N)
     * - Huffman tree: O(N)
     * - Output array: O(N)
     * 
     * @param S    Input string containing characters
     * @param f    Array of frequencies for each character
     * @param N    Length of string and frequency array
     * @return     ArrayList of Huffman codes for each character
     */
    public static ArrayList<String> huffmanCodes(String S, int f[], int N) {
        // Initialize min-heap with character nodes
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.add(new Node(S.charAt(i), f[i]));
        }

        // Build Huffman tree by combining nodes
        while (pq.size() > 1) {
            Node root = new Node();
            root.left = pq.remove();
            root.right = pq.remove();
            root.freq = root.left.freq + root.right.freq;
            pq.add(root);
        }

        // Get root of Huffman tree
        Node root = pq.remove();

        // Generate codes by traversing tree
        ArrayList<String> out = new ArrayList<>();
        encodeHuffmanTree(out, root, "");
        return out;
    }

    /*-
     * Helper Method: encodeHuffmanTree
     * 
     * Intuition:
     * - Recursively traverses Huffman tree to generate binary codes
     * - Left edges add '0', right edges add '1' to current code
     * - Leaf nodes contain actual characters and their codes
     * 
     * Explanation:
     * - Base case: If node is leaf (has character), add current code to output
     * - Recursive case: 
     *   * Traverse left subtree adding '0' to current code
     *   * Traverse right subtree adding '1' to current code
     * 
     * Time Complexity: O(N) where N is number of nodes in tree
     * Space Complexity: O(H) where H is height of tree (recursion stack)
     * 
     * @param out    ArrayList to store generated codes
     * @param root   Current node in Huffman tree
     * @param curr   Current binary code being built
     */
    public static void encodeHuffmanTree(ArrayList<String> out, Node root, String curr) {
        if (root.c != '#') {
            out.add(curr);
            return;
        }
        encodeHuffmanTree(out, root.left, curr + "0");
        encodeHuffmanTree(out, root.right, curr + "1");
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        String S1 = "abcdef";
        int[] f1 = {5, 9, 12, 13, 16, 45};
        System.out.println("Input: S = " + S1 + ", f = " + java.util.Arrays.toString(f1));
        System.out.println("Output: " + huffmanCodes(S1, f1, S1.length()));

        // Test Case 2: Single character
        System.out.println("\nSingle Character Test:");
        String S2 = "a";
        int[] f2 = {1};
        System.out.println("Input: S = " + S2 + ", f = " + java.util.Arrays.toString(f2));
        System.out.println("Output: " + huffmanCodes(S2, f2, S2.length()));

        // Test Case 3: Equal frequencies
        System.out.println("\nEqual Frequencies Test:");
        String S3 = "abc";
        int[] f3 = {1, 1, 1};
        System.out.println("Input: S = " + S3 + ", f = " + java.util.Arrays.toString(f3));
        System.out.println("Output: " + huffmanCodes(S3, f3, S3.length()));
    }
}
