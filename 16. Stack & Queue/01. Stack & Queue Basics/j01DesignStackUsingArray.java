public class j01DesignStackUsingArray {

    public static void main(String args[]) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop()); // 3
        System.out.println(stack.pop()); // 2
        System.out.println(stack.pop()); // 1
        System.out.println(stack.pop()); // -1 (stack is empty)
        stack.push(4);
        stack.push(5);
        System.out.println(stack.peek()); // 5 (top element)
        System.out.println(stack.size()); // 2 (size of stack)
        stack.print(); // 5 4 (print stack elements)
        stack.clear(); // clear stack
        System.out.println(stack.isEmpty()); // true (stack is empty)
    }

    static class MyStack {
        private int[] arr;
        private int top;

        public MyStack() {
            arr = new int[1000];
            top = -1;
        }

        public void push(int x) {
            if (top == 1000)
                throw new RuntimeException("Stack Overflow");
            arr[++top] = x;
        }

        public int pop() {
            if (top == -1)
                return -1;
            return arr[top--];
        }

        public int peek() {
            if (top == -1)
                return -1;
            return arr[top];
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public int size() {
            return top + 1;
        }

        public void clear() {
            top = -1;
        }

        public void print() {
            for (int i = top; i >= 0; i--) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }
}