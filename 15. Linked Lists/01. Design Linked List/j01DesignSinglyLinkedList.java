public class j01DesignSinglyLinkedList {
    public static void main(String[] args) {
        MyLinkedList obj = new MyLinkedList();
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

    static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    static class MyLinkedList {
        public Node head;
        public Node tail;
        public int size;

        public MyLinkedList() {

        }

        public int get(int index) {
            if (index < 0 || index >= this.size)
                return -1;
            Node temp = this.head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            return temp.data;
        }

        public void addAtHead(int val) {
            Node node = new Node(val);
            this.size++;
            if (this.head == null) {
                this.head = node;
                this.tail = node;
            } else {
                node.next = head;
                this.head = node;
            }
        }

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

        public void addAtIndex(int index, int val) {
            if (index < 0 || index > this.size)
                return;
            if (index == 0)
                this.addAtHead(val);
            else if (index == this.size)
                this.addAtTail(val);
            else {
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