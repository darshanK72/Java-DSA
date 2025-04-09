public class j02DesignStackUsingLinkedList {

    

    static class MyStack {
        class StackNode {
            int data;
            StackNode next;

            StackNode(int a) {
                data = a;
                next = null;
            }
        }

        StackNode top;
        public int size = 0;

        void push(int a) {
            StackNode node = new StackNode(a);
            node.next = top;
            top = node;
            this.size++;
        }

        int pop() {
            if (top == null)
                return -1;
            int value = top.data;
            top = top.next;
            return value;
        }

        public int peek() {
            if (top == null)
                return -1;
            return top.data;
        }

        public boolean isEmpty() {
            return top == null;
        }

        public int size() {
            return this.size;
        }

        public void clear() {
            top = null;
        }

        public void print() {
            StackNode current = top;
            while (current != null) {
                System.out.print(current.data + " ");
                current = current.next;
            }
            System.out.println();
        }

    }
}
