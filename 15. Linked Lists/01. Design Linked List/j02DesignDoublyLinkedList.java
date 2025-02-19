public class j02DesignDoublyLinkedList {
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

    }
}
