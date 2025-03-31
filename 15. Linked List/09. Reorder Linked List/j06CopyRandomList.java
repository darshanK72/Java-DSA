import java.util.HashMap;

/**
 * Problem Statement:
 * 
 *     Given a linked list where each node contains an additional random pointer that could point
 *     to any node in the list or null, create a deep copy of the list.
 * 
 * Input:
 *     - A singly linked list where each node contains:
 *         - `data`: The integer value stored in the node.
 *         - `next`: A reference to the next node in the list.
 *         - `random`: A reference to any node in the list or null.
 * 
 * Output:
 *     - The head of the deep-copied linked list.
 * 
 * Example:
 *     Input:
 *         head = [7 -> 13 -> 11 -> 10 -> 1], random = [null, 0, 4, 2, 0]
 *     Output:
 *         A deep copy of the list with the same structure and random pointers.
 */

public class j06CopyRandomList {

    /**
     * Node Class:
     * 
     * Represents a node in a singly linked list. Each node contains:
     * - `data`: The integer value stored in the node.
     * - `next`: A reference to the next node in the list.
     * - `random`: A reference to any node in the list or null.
     */
    static class Node {
        public int data;
        public Node next;
        public Node random;

        public Node(int data) {
            this.data = data;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * Approach 1: Using HashMap
     * 
     * Intuition:
     * - Use a HashMap to store the mapping between the original nodes and their corresponding
     *   copied nodes. This allows us to easily set the `random` pointers in the copied list.
     * 
     * Explanation:
     * 1. Traverse the original list and create a copy of each node. Store the mapping between
     *    the original node and the copied node in a HashMap.
     * 2. Traverse the original list again and use the HashMap to set the `random` pointers
     *    in the copied list.
     * 3. Return the head of the copied list.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of nodes in the list. The list is traversed twice.
     * 
     * Space Complexity:
     * - O(n), due to the HashMap used to store the mapping.
     * 
     * @param head The head of the original linked list.
     * @return The head of the deep-copied linked list.
     */
    public Node copyRandomListHashMap(Node head) {
        if (head == null) {
            return null; // Base case: empty list
        }

        HashMap<Node, Node> map = new HashMap<>();
        Node dummy = new Node(-1); // Dummy node for the copied list
        Node curr = head;
        Node out = dummy;

        // Step 1: Create a copy of each node and store the mapping
        while (curr != null) {
            out.next = new Node(curr.data);
            map.put(curr, out.next);
            out = out.next;
            curr = curr.next;
        }

        // Step 2: Set the `random` pointers in the copied list
        curr = head;
        out = dummy.next;
        while (curr != null) {
            out.random = map.get(curr.random);
            curr = curr.next;
            out = out.next;
        }

        return dummy.next; // Return the head of the copied list
    }

    /**
     * Approach 2: Efficient In-Place Copy
     * 
     * Intuition:
     * - Instead of using extra space for a HashMap, modify the original list temporarily to
     *   include the copied nodes. This allows us to set the `random` pointers in the copied
     *   list without additional space.
     * 
     * Explanation:
     * 1. Traverse the original list and insert a copied node after each original node.
     * 2. Traverse the modified list and set the `random` pointers for the copied nodes.
     * 3. Separate the copied list from the original list and restore the original list.
     * 4. Return the head of the copied list.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of nodes in the list. The list is traversed three times.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few pointers.
     * 
     * @param head The head of the original linked list.
     * @return The head of the deep-copied linked list.
     */
    public Node copyRandomListEfficient(Node head) {
        if (head == null) {
            return null; // Base case: empty list
        }

        // Step 1: Insert copied nodes after each original node
        Node curr = head;
        while (curr != null) {
            Node node = new Node(curr.data);
            node.next = curr.next;
            curr.next = node;
            curr = curr.next.next;
        }

        // Step 2: Set the `random` pointers for the copied nodes
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }

        // Step 3: Separate the copied list from the original list
        Node l1 = head; // Pointer for the original list
        Node l2 = head.next; // Pointer for the copied list
        Node out = l2; // Head of the copied list

        while (l2 != null) {
            l1.next = l1.next.next; // Restore the original list
            if (l2.next != null) {
                l2.next = l2.next.next; // Update the copied list
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        return out; // Return the head of the copied list
    }
}