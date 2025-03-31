/**
 * Problem Statement:
 * 
 *     Given the head of a singly linked list and an integer `k`, split the linked list into `k` consecutive parts.
 *     The length of each part should be as equal as possible. If the number of nodes is not divisible by `k`,
 *     the first few parts should have one more node than the others.
 * 
 * Input:
 *     - A singly linked list where each node contains an integer value.
 *     - An integer `k` representing the number of parts to split the list into.
 * 
 * Output:
 *     - An array of `k` linked list heads, where each head represents one part of the split list.
 * 
 * Example:
 *     Input:
 *         head = [1, 2, 3, 4, 5], k = 3
 *     Output:
 *         [[1, 2], [3, 4], [5]]
 * 
 *     Explanation:
 *         The list is split into 3 parts. The first part has 2 nodes, the second part has 2 nodes,
 *         and the third part has 1 node.
 */

public class j05SplitListInKParts {

    /**
     * Node Class:
     * 
     * Represents a node in a singly linked list. Each node contains:
     * - `data`: The integer value stored in the node.
     * - `next`: A reference to the next node in the list.
     */
    static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Approach: Split List into K Parts
     * 
     * Intuition:
     * - To split the list into `k` parts, calculate the size of each part and distribute the nodes accordingly.
     * - If the size of the list is not divisible by `k`, the first few parts should have one extra node.
     * 
     * Explanation:
     * 1. Calculate the total size of the list by traversing it.
     * 2. Determine the size of each part (`partSize`) and the number of parts that should have one extra node (`remaining`).
     * 3. Traverse the list and split it into `k` parts:
     *     - For each part, determine its size (`thisPartSize`).
     *     - Traverse the nodes for the current part and disconnect it from the rest of the list.
     * 4. Store the head of each part in an array and return the array.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of nodes in the linked list. The list is traversed twice: once to calculate its size
     *   and once to split it into parts.
     * 
     * Space Complexity:
     * - O(k), where `k` is the number of parts. The output array requires space for `k` references.
     * 
     * @param head The head of the singly linked list.
     * @param k The number of parts to split the list into.
     * @return An array of `k` linked list heads, where each head represents one part of the split list.
     */
    public Node[] splitListToParts(Node head, int k) {
        Node[] out = new Node[k]; // Array to store the heads of the parts
        int size = 0;
        Node curr = head;

        // Step 1: Calculate the total size of the list
        while (curr != null) {
            size++;
            curr = curr.next;
        }

        // Step 2: Determine the size of each part and the number of extra nodes
        int partSize = size / k;
        int remaining = size % k;

        curr = head;

        // Step 3: Split the list into k parts
        for (int i = 0; i < k; i++) {
            Node part = curr; // Start of the current part
            int thisPartSize = partSize;

            // Add one extra node to the first `remaining` parts
            if (remaining > 0) {
                thisPartSize++;
                remaining--;
            }

            // Traverse the nodes for the current part
            int j = 1;
            while (j < thisPartSize && curr != null) {
                curr = curr.next;
                j++;
            }

            // Disconnect the current part from the rest of the list
            if (curr != null) {
                Node next = curr.next;
                curr.next = null;
                curr = next;
            }

            // Store the head of the current part in the output array
            out[i] = part;
        }

        return out; // Return the array of parts
    }
}