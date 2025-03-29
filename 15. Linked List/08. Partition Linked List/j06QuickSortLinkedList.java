/**
 * Problem Statement:
 * 
 *     Implement quick sort for a singly linked list.
 * 
 * Input:
 *     - A singly linked list where each node contains an integer value.
 * 
 * Output:
 *     - The head of the sorted linked list.
 * 
 * Example:
 *     Input:
 *         head = [4, 2, 1, 3]
 *     Output:
 *         [1, 2, 3, 4]
 * 
 *     Explanation:
 *         The input list is sorted using the quick sort algorithm, which partitions the list
 *         around a pivot and recursively sorts the partitions.
 */

import java.util.Random;

public class j06QuickSortLinkedList {

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
     * Approach: Quick Sort
     * 
     * Intuition:
     * - Quick sort is a divide-and-conquer algorithm that partitions the list around a pivot.
     * - The pivot is chosen randomly to improve performance on average.
     * - The list is divided into two partitions: nodes less than the pivot and nodes greater than or equal to the pivot.
     * - The partitions are recursively sorted, and the results are combined to form the sorted list.
     * 
     * Explanation:
     * 1. If the list is empty or contains only one node, it is already sorted. Return the head.
     * 2. Randomize the pivot to improve performance.
     * 3. Partition the list into two sublists: nodes less than the pivot and nodes greater than or equal to the pivot.
     * 4. Recursively apply quick sort to the left and right partitions.
     * 5. Combine the sorted partitions and the pivot to form the final sorted list.
     * 
     * Time Complexity:
     * - O(n * log(n)) on average, where `n` is the number of nodes in the list.
     * - O(n^2) in the worst case, if the pivot is poorly chosen.
     * 
     * Space Complexity:
     * - O(log(n)) due to the recursive stack.
     * 
     * @param head The head of the singly linked list.
     * @return The head of the sorted linked list.
     */
    public static Node quickSort(Node head) {
        if (head == null || head.next == null) {
            return head; // Base case: list is empty or has only one node
        }

        randomize(head); // Randomize the pivot to improve performance
        Node left = quickSort(partition(head, head.data)); // Sort the left partition
        Node right = quickSort(head.next); // Sort the right partition
        head.next = right; // Connect the pivot to the right partition

        if (left == null) {
            return head; // If the left partition is empty, return the pivot and right partition
        }

        Node tail = left; // Find the tail of the left partition
        while (tail.next != null) {
            tail = tail.next;
        }

        tail.next = head; // Connect the left partition to the pivot
        return left; // Return the head of the sorted list
    }

    /**
     * Helper Method: Partition
     * 
     * Intuition:
     * - Partition the list into two sublists based on a pivot value.
     * - Nodes with values less than the pivot are placed in one sublist, and nodes with values
     *   greater than or equal to the pivot are placed in another sublist.
     * 
     * Explanation:
     * 1. Create two dummy nodes to represent the heads of the two partitions.
     * 2. Traverse the list, comparing each node's value to the pivot:
     *     - If the value is less than the pivot, append the node to the first partition.
     *     - Otherwise, append the node to the second partition.
     * 3. Disconnect the partitions by setting the `next` pointers of the last nodes to null.
     * 4. Return the head of the first partition.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of nodes in the list.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few pointers.
     * 
     * @param head The head of the singly linked list.
     * @param x The pivot value.
     * @return The head of the first partition.
     */
    public static Node partition(Node head, int x) {
        Node one = new Node(-1); // Dummy node for the first partition
        Node two = new Node(-1); // Dummy node for the second partition
        Node temp1 = one;
        Node temp2 = two;
        Node curr = head;

        while (curr != null) {
            if (curr.data < x) { // If the value is less than the pivot
                temp1.next = curr; // Append to the first partition
                temp1 = temp1.next;
            } else { // If the value is greater than or equal to the pivot
                temp2.next = curr; // Append to the second partition
                temp2 = temp2.next;
            }
            curr = curr.next; // Move to the next node
        }

        temp1.next = null; // Disconnect the first partition
        temp2.next = null; // Disconnect the second partition

        return one.next; // Return the head of the first partition
    }

    /**
     * Helper Method: Randomize
     * 
     * Intuition:
     * - Randomizing the pivot improves the performance of quick sort on average by reducing
     *   the likelihood of encountering the worst-case scenario.
     * 
     * Explanation:
     * 1. Calculate the size of the list by traversing it.
     * 2. Generate a random index within the range of the list size.
     * 3. Traverse the list to the random index and swap the value of the node at that index
     *    with the value of the head node.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of nodes in the list.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few pointers.
     * 
     * @param head The head of the singly linked list.
     */
    public static void randomize(Node head) {
        Node temp = head;
        int size = 0;

        // Calculate the size of the list
        while (temp != null) {
            size++;
            temp = temp.next;
        }

        // Generate a random index
        int randomInt = (new Random()).nextInt(size);

        // Traverse to the random index
        temp = head;
        for (int i = 0; i < randomInt; i++) {
            temp = temp.next;
        }

        // Swap the value of the node at the random index with the head node
        int val = temp.data;
        temp.data = head.data;
        head.data = val;
    }
}