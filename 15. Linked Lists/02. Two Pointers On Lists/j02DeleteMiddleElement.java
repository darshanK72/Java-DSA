public class j02DeleteMiddleElement {
    static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public static Node deleteMiddle(Node head) {
        Node slow = head;
        Node fast = head;
        Node prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (prev == null) {
            head = null;
        } else if (slow.next == null) {
            prev.next = null;
        } else {
            prev.next = slow.next;
        }
        return head;
    }
}
