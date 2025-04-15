public class j04NStackUsingArray {
    public static class NStack {
        int[] arr;
        int[] tops;
        int N;

        public NStack(int N, int S) {
            this.arr = new int[10000];
            this.tops = new int[N];
            for (int i = 0; i < N; i++) {
                tops[i] = (-1 * N) + i;
            }
            this.N = N;
        }

        public boolean push(int x, int m) {
            --m;
            int top = tops[m] + this.N;
            if (top > this.arr.length)
                return false;
            this.arr[top] = x;
            this.tops[m] = top;
            // printStack(this.arr);
            return true;
        }

        public int pop(int m) {
            --m;
            int top = tops[m];
            if (top < 0)
                return -1;
            int out = this.arr[top];
            tops[m] = top - this.N;
            return out;
        }

        public void printStack(int arr[]) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }
}