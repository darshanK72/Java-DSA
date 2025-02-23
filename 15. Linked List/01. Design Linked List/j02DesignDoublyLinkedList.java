/**
 * Problem Statement:
 * 
 *     Design your implementation of a doubly linked list. A node in a doubly linked list should have three attributes:
 *     an integer value, a reference to the next node, and a reference to the previous node. Implement the MyDoublyLinkedList 
 *     class with the following methods:
 * 
 *     - MyDoublyLinkedList(): Initializes the MyDoublyLinkedList object.
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
 *         MyDoublyLinkedList linkedList = new MyDoublyLinkedList();
 *         linkedList.addAtHead(1);
 *         linkedList.addAtTail(3);
 *         linkedList.addAtIndex(1, 2);   // linked list becomes 1 <-> 2 <-> 3
 *         linkedList.get(1);             // returns 2
 *         linkedList.deleteAtIndex(1);   // now the linked list is 1 <-> 3
 *         linkedList.get(1);             // returns 3
 * 
 *     Output:
 *         2
 *         3
 * 
 *     Explanation:
 *         - After initializing the linked list, it is empty.
 *         - Adding 1 at the head results in the list: 1
 *         - Adding 3 at the tail results in the list: 1 <-> 3
 *         - Adding 2 at index 1 results in the list: 1 <-> 2 <-> 3
 *         - Getting the value at index 1 returns 2.
 *         - Deleting the node at index 1 results in the list: 1 <-> 3
 *         - Getting the value at index 1 now returns 3.
 */

public class j02DesignDoublyLinkedList {

    public static void main(String[] args) {
        MyDoublyLinkedList linkedList = new MyDoublyLinkedList();

        System.out.println("Adding 1 at head");
        linkedList.addAtHead(1);
        System.out.println("Adding 3 at tail");
        linkedList.addAtTail(3);
        System.out.println("Adding 2 at index 1");
        linkedList.addAtIndex(1, 2); // List becomes 1 <-> 2 <-> 3

        System.out.print("Current List: ");
        linkedList.printList(); // Expected: 1 2 3

        System.out.println("Getting element at index 1: " + linkedList.get(1)); // Expected: 2

        System.out.println("Deleting node at index 1");
        linkedList.deleteAtIndex(1); // List becomes 1 <-> 3
        System.out.print("List after deletion: ");
        linkedList.printList(); // Expected: 1 3

        System.out.println("Getting element at index 1: " + linkedList.get(1)); // Expected: 3

        System.out.println("Adding 4 at index 1");
        linkedList.addAtIndex(1, 4); // List becomes 1 <-> 4 <-> 3
        System.out.print("List after adding 4 at index 1: ");
        linkedList.printList(); // Expected: 1 4 3

        System.out.println("Adding 5 at head");
        linkedList.addAtHead(5); // List becomes 5 <-> 1 <-> 4 <-> 3
        System.out.print("List after adding 5 at head: ");
        linkedList.printList(); // Expected: 5 1 4 3

        System.out.println("Adding 6 at tail");
        linkedList.addAtTail(6); // List becomes 5 <-> 1 <-> 4 <-> 3 <-> 6
        System.out.print("List after adding 6 at tail: ");
        linkedList.printList(); // Expected: 5 1 4 3 6

        System.out.println("Deleting node at index 0");
        linkedList.deleteAtIndex(0); // List becomes 1 <-> 4 <-> 3 <-> 6
        System.out.print("List after deleting head: ");
        linkedList.printList(); // Expected: 1 4 3 6

        System.out.println("Deleting node at last index");
        linkedList.deleteAtIndex(linkedList.size - 1); // List becomes 1 <-> 4 <-> 3
        System.out.print("List after deleting tail: ");
        linkedList.printList(); // Expected: 1 4 3
    }

    /**
     * Node class: Represents a single node in the doubly linked list.
     * Each node contains:
     *  - `data`: Stores the integer value.
     *  - `next`: Points to the next node.
     *  - `prev`: Points to the previous node.
     */
    static class Node {
        public int data;
        public Node next;
        public Node prev;

        public Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    static class MyDoublyLinkedList {
        public Node head;
        public Node tail;
        public int size;

        public MyDoublyLinkedList() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        /**
         * Method: get(int index)
         * 
         * Intuition:
         * - Traverse the list to fetch the value at the given index.
         * 
         * Explanation:
         * - If index is invalid, return -1.
         * - Start from the head and traverse `index` times.
         * - Return the data of the node at that index.
         * 
         * Time Complexity: O(n) (worst case traversal to the end).
         * Space Complexity: O(1) (no extra space used).
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

        /**
         * Method: addAtHead(int val)
         * 
         * Intuition:
         * - Insert a new node at the beginning of the list.
         * 
         * Explanation:
         * - Create a new node.
         * - If the list is empty, set both head and tail to the new node.
         * - Otherwise, adjust `prev` and `next` pointers accordingly.
         * - Update the head reference.
         * 
         * Time Complexity: O(1).
         * Space Complexity: O(1).
         */
        public void addAtHead(int val) {
            Node node = new Node(val);
            this.size++;
            if (this.head == null) { // If list is empty
                this.head = node;
                this.tail = node;
            } else {
                node.next = this.head;
                this.head.prev = node;
                this.head = node;
            }
        }

        /**
         * Method: addAtTail(int val)
         * 
         * Intuition:
         * - Insert a new node at the end of the list.
         * 
         * Explanation:
         * - Create a new node.
         * - If the list is empty, set both head and tail to the new node.
         * - Otherwise, adjust `prev` and `next` pointers accordingly.
         * - Update the tail reference.
         * 
         * Time Complexity: O(1).
         * Space Complexity: O(1).
         */
        public void addAtTail(int val) {
            Node node = new Node(val);
            this.size++;
            if (this.tail == null) { // If list is empty
                this.head = node;
                this.tail = node;
            } else {
                node.prev = this.tail;
                this.tail.next = node;
                this.tail = node;
            }
        }

        /**
         * Method: addAtIndex(int index, int val)
         * 
         * Intuition:
         * - Insert a node at the given index.
         * - If index is 0, call `addAtHead()`.
         * - If index equals `size`, call `addAtTail()`.
         * - Otherwise, traverse and insert at the position.
         * 
         * Explanation:
         * - If index is invalid, return.
         * - If inserting at head or tail, call respective methods.
         * - Otherwise, traverse to index-1 position and adjust pointers.
         * 
         * Time Complexity: O(n) (insertion at middle requires traversal).
         * Space Complexity: O(1).
         */
        public void addAtIndex(int index, int val) {
            if (index < 0 || index > this.size)
                return;
            if (index == 0) {
                addAtHead(val);
            } else if (index == this.size) {
                addAtTail(val);
            } else {
                Node node = new Node(val);
                Node temp = this.head;
                for (int i = 0; i < index - 1; i++) {
                    temp = temp.next;
                }
                // Adjusting pointers
                node.next = temp.next;
                node.prev = temp;
                temp.next.prev = node;
                temp.next = node;
                this.size++;
            }
        }

        /**
         * Method: deleteAtIndex(int index)
         * 
         * Intuition:
         * - Remove a node at the given index.
         * - If index is 0, update `head`.
         * - If index is last, update `tail`.
         * - Otherwise, adjust pointers accordingly.
         * 
         * Explanation:
         * - If index is invalid, return.
         * - If deleting head, update `head` reference.
         * - If deleting tail, update `tail` reference.
         * - Otherwise, find the node and adjust pointers.
         * 
         * Time Complexity: O(n) (traversal required).
         * Space Complexity: O(1).
         */
        public void deleteAtIndex(int index) {
            if (index < 0 || index >= this.size)
                return;

            this.size--;
            if (index == 0) { // Deleting head
                this.head = this.head.next;
                if (this.head != null) {
                    this.head.prev = null;
                } else { // If the list becomes empty
                    this.tail = null;
                }
            } else if (index == this.size) { // Deleting tail
                this.tail = this.tail.prev;
                if (this.tail != null) {
                    this.tail.next = null;
                } else { // If the list becomes empty
                    this.head = null;
                }
            } else { // Deleting a node in the middle
                Node temp = this.head;
                for (int i = 0; i < index; i++) {
                    temp = temp.next;
                }
                temp.prev.next = temp.next;
                temp.next.prev = temp.prev;
            }
        }

        /**
         * Method: printList()
         * 
         * Intuition:
         * - Traverse the list and print each node's value.
         * 
         * Explanation:
         * - Start from head and traverse until reaching `null`.
         * - Print each node's data.
         * 
         * Time Complexity: O(n).
         * Space Complexity: O(1).
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
