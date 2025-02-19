/*-
 * Problem Statement:
 * 
 *     Design your implementation of a singly linked list. A node in a singly linked list should have two attributes:
 *     an integer value and a reference to the next node. Implement the MySinglyLinkedList class with the following methods:
 * 
 *     - MySinglyLinkedList(): Initializes the MySinglyLinkedList object.
 *     - int get(int index): Returns the value of the index-th node in the linked list. If the index is invalid, return -1.
 *     - void addAtHead(int val): Adds a node of value val before the first element of the linked list. After the insertion,
 *       the new node becomes the head of the linked list.
 *     - void addAtTail(int val): Appends a node of value val as the last element of the linked list.
 *     - void addAtIndex(int index, int val): Adds a node of value val before the index-th node in the linked list. If index
 *       equals the length of the linked list, the node is appended to the end. If index is greater than the length, the node
 *       is not inserted.
 *     - void deleteAtIndex(int index): Deletes the index-th node in the linked list, if the index is valid.
 * 
 * Input:
 *     - The input consists of a series of method calls and their arguments, as described above.
 * 
 * Output:
 *     - For each call to the get method, return the value of the node at the specified index or -1 if the index is invalid.
 *       For all other methods, return void.
 * 
 * Example:
 *     Input:
 *         MySinglyLinkedList linkedList = new MySinglyLinkedList();
 *         linkedList.addAtHead(1);
 *         linkedList.addAtTail(3);
 *         linkedList.addAtIndex(1, 2);   // linked list becomes 1->2->3
 *         linkedList.get(1);             // returns 2
 *         linkedList.deleteAtIndex(1);   // now the linked list is 1->3
 *         linkedList.get(1);             // returns 3
 * 
 *     Output:
 *         2
 *         3
 * 
 *     Explanation:
 *         - After initializing the linked list, it is empty.
 *         - Adding 1 at the head results in the list: 1
 *         - Adding 3 at the tail results in the list: 1->3
 *         - Adding 2 at index 1 results in the list: 1->2->3
 *         - Getting the value at index 1 returns 2.
 *         - Deleting the node at index 1 results in the list: 1->3
 *         - Getting the value at index 1 now returns 3.
 */

public class j01DesignSinglyLinkedList {
    public static void main(String[] args) {
        MySinglyLinkedList obj = new MySinglyLinkedList();
        System.out.println("Adding 1 at head");
        obj.addAtHead(1);
        System.out.println("Adding 2 at head");
        obj.addAtHead(2);
        System.out.println("Adding 3 at head");
        obj.addAtHead(3);
        System.out.print("List after adding at head: ");
        obj.printList();
        System.out.println("Adding 1 at index 0");
        obj.addAtIndex(0, 1);
        System.out.println("Adding 2 at index 1");
        obj.addAtIndex(1, 2);
        System.out.print("List after adding at index: ");
        obj.printList();
        System.out.println("Adding 4 at tail");
        obj.addAtTail(4);
        System.out.println("Adding 5 at tail");
        obj.addAtTail(5);
        System.out.println("Adding 6 at tail");
        obj.addAtTail(6);
        System.out.print("List after adding at tail: ");
        obj.printList();
        System.out.println("Getting element at index 0: " + obj.get(0));
        System.out.println("Getting element at index 1: " + obj.get(1));
        System.out.println("Getting element at index 5: " + obj.get(5));
        System.out.println("Getting element at index 6: " + obj.get(6));
        System.out.println("Adding 7 at index 1");
        obj.addAtIndex(1, 7);
        System.out.print("List after adding 7 at index 1: ");
        obj.printList();
        System.out.println("Adding 8 at index 6");
        obj.addAtIndex(6, 8);
        System.out.print("List after adding 8 at index 6: ");
        obj.printList();
    }

    /*-
     * Node class: Represents a single node in the linked list.
     * Each node contains an integer `data` and a reference to the next node.
     */
    static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    static class MySinglyLinkedList {
        public Node head;
        public Node tail;
        public int size;

        public MySinglyLinkedList() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        /*-
         * Method: get(int index)
         * 
         * Intuition:
         * - Traverse the linked list until reaching the given index.
         * - If the index is out of bounds, return -1.
         * 
         * Explanation:
         * - Start from the head and iterate until reaching the desired index.
         * - Return the data at that index.
         * 
         * Time Complexity: O(n), since we traverse at most `n` elements.
         * Space Complexity: O(1), as no extra space is used.
         */
        public int get(int index) {
            if (index < 0 || index >= this.size)
                return -1;
            Node temp = this.head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            return temp.data;
        }

        /*-
         * Method: addAtHead(int val)
         * 
         * Intuition:
         * - Insert a new node at the beginning of the list.
         * - If the list is empty, update both head and tail.
         * 
         * Explanation:
         * - Create a new node and set its `next` pointer to the current head.
         * - Update the head to point to this new node.
         * - If this is the first node, update tail to point to the same node.
         * 
         * Time Complexity: O(1), as we modify only a few references.
         * Space Complexity: O(1), as we don't use extra memory.
         */
        public void addAtHead(int val) {
            Node node = new Node(val);
            this.size++;
            if (this.head == null) {
                this.head = node;
                this.tail = node;
            } else {
                node.next = this.head;
                this.head = node;
            }
        }

        /*-
         * Method: addAtTail(int val)
         * 
         * Intuition:
         * - Append a new node at the end of the list.
         * - If the list is empty, set the node as both head and tail.
         * 
         * Explanation:
         * - Create a new node.
         * - Update the current tail's `next` to point to the new node.
         * - Update the tail reference to point to the new node.
         * 
         * Time Complexity: O(1), as we directly update the tail reference.
         * Space Complexity: O(1), as no extra space is used.
         */
        public void addAtTail(int val) {
            Node node = new Node(val);
            this.size++;
            if (this.head == null) {
                this.head = node;
                this.tail = node;
            } else {
                this.tail.next = node;
                this.tail = node;
            }
        }

        /*-
         * Method: addAtIndex(int index, int val)
         * 
         * Intuition:
         * - Insert a node at the given index.
         * - If index is 0, call `addAtHead()`.
         * - If index is equal to size, call `addAtTail()`.
         * - Otherwise, traverse the list and insert at the given index.
         * 
         * Explanation:
         * - If the index is invalid, do nothing.
         * - If adding at head or tail, call the respective methods.
         * - Otherwise, find the node before the target position.
         * - Update pointers to insert the new node at the correct index.
         * 
         * Time Complexity: O(n), as we may traverse the list up to `n` times.
         * Space Complexity: O(1), as we only modify pointers.
         */
        public void addAtIndex(int index, int val) {
            if (index < 0 || index > this.size)
                return;
            if (index == 0) {
                this.addAtHead(val);
            } else if (index == this.size) {
                this.addAtTail(val);
            } else {
                Node node = new Node(val);
                Node temp = this.head;
                for (int i = 0; i < index - 1; i++) {
                    temp = temp.next;
                }
                node.next = temp.next;
                temp.next = node;
                this.size++;
            }
        }

        /*-
         * Method: deleteAtIndex(int index)
         * 
         * Intuition:
         * - Remove a node from the given index.
         * - If index is 0, update head reference.
         * - If index is last element, update tail reference.
         * - Otherwise, find the previous node and adjust pointers.
         * 
         * Explanation:
         * - If index is invalid, do nothing.
         * - If deleting the head, move head to the next node.
         * - If deleting the last node, update tail reference.
         * - Otherwise, traverse to the node before the target and update pointers.
         * 
         * Time Complexity: O(n), since we may need to traverse up to `n` elements.
         * Space Complexity: O(1), as we only modify existing nodes.
         */
        public void deleteAtIndex(int index) {
            if (index < 0 || index >= this.size)
                return;
            if (index == 0) {
                this.head = this.head.next;
                if (this.head == null) {
                    this.tail = null;
                }
            } else {
                Node temp = this.head;
                for (int i = 0; i < index - 1; i++) {
                    temp = temp.next;
                }
                temp.next = temp.next.next;
                if (index == this.size - 1) {
                    this.tail = temp;
                }
            }
            this.size--;
        }

        /*-
         * Method: printList()
         * 
         * Intuition:
         * - Traverse the list and print all elements.
         * 
         * Explanation:
         * - Start from the head and print each element until reaching the end.
         * 
         * Time Complexity: O(n), as we traverse the entire list.
         * Space Complexity: O(1), as no extra space is used.
         */
        public void printList() {
            Node temp = head;
            while (temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
            System.out.println();
        }
    }
}
