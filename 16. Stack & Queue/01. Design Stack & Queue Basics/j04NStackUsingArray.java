/*-
 * Problem Statement:
 *     Implement N stacks in a single array efficiently.
 *     https://www.codingninjas.com/studio/problems/n-stacks-in-an-array_1164271
 * 
 * Input Operations:
 *     - push(x, m): Push element x into stack m (1 <= m <= N)
 *     - pop(m): Pop element from stack m
 * 
 * Output:
 *     - For push: Return true if successful, false if stack is full
 *     - For pop: Return popped element if exists, -1 if stack empty
 * 
 * Example:
 *     Input: N = 3, S = 6 (array size)
 *     push(10, 1), push(20, 1), push(30, 2)
 *     pop(1), pop(2)
 *     
 *     Output:
 *     true, true, true
 *     20, 30
 * 
 *     Explanation:
 *     Push 10,20 to stack1 and 30 to stack2
 *     Pop from stack1 returns 20 (last inserted)
 *     Pop from stack2 returns 30
 */

public class j04NStackUsingArray {

    public static void main(String args[]){
        // Example usage of NStack class
        NStack nStack = new NStack(3, 6); // 3 stacks, array size 6
        System.out.println(nStack.push(10, 1)); // true
        System.out.println(nStack.push(20, 1)); // true
        System.out.println(nStack.push(30, 2)); // true
        System.out.println(nStack.pop(1));       // 20
        System.out.println(nStack.pop(2));       // 30
        System.out.println(nStack.pop(1));       // 10
    }
    
    public static class NStack {
        /*-
         * Approach: Free List Implementation
         * 
         * Intuition:
         * - Use three arrays: arr (data), tops (top of each stack), next (for free list management)
         * - Maintain a free list using next array to track available spaces
         * - For each stack element, store:
         *   - Value in arr[]
         *   - Link to previous element of same stack in next[]
         * - tops[] stores the index of top element for each stack
         * - nextFree tracks the next available position
         * 
         * Time Complexity:
         * - Push operations: O(1)
         * - Pop operations: O(1)
         * 
         * Space Complexity:
         * - O(S + N) where S is array size and N is number of stacks
         */
        int[] arr;    // Array to store actual values
        int[] tops;   // Array to store top indices of each stack
        int[] next;   // Array to implement free list and stack linking
        int S;        // Size of array
        int N;        // Number of stacks
        int nextFree = 0;    // Index of next free slot
        int currFree = -1;   // Current free slot being used

        /**
         * Initialize N stacks with array of size S.
         * Time Complexity: O(S)
         * Space Complexity: O(S + N)
         * 
         * @param N Number of stacks
         * @param S Size of array
         */
        public NStack(int N, int S) {
            this.S = S;
            this.N = N;
            this.arr = new int[S];    // Data array
            this.tops = new int[N];   // Top indices
            this.next = new int[S];   // Free list/links
            
            // Initialize all stacks as empty
            for (int i = 0; i < N; i++) {
                tops[i] = -1;
            }
            
            // Initialize free list
            for (int i = 0; i < S; i++) {
                next[i] = (i + 1);
            }
            next[S - 1] = -1;  // End of free list
        }

        /**
         * Push element x into stack m.
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @param x Element to push
         * @param m Stack number (1-based indexing)
         * @return true if push successful, false if stack full
         */
        public boolean push(int x, int m) {
            currFree = nextFree;
            if (currFree == -1)  // Array is full
                return false;
            
            nextFree = next[currFree];  // Update next free slot
            arr[currFree] = x;          // Store value
            next[currFree] = tops[m-1]; // Link to previous top
            tops[m-1] = currFree;       // Update top
            return true;
        }

        /**
         * Pop element from stack m.
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @param m Stack number (1-based indexing)
         * @return Popped element if exists, -1 if stack empty
         */
        public int pop(int m) {
            int top = tops[m-1];
            if (top == -1)  // Stack empty
                return -1;
            
            int out = arr[top];         // Get top value
            tops[m-1] = next[top];      // Update top to previous element
            next[top] = nextFree;       // Add to free list
            nextFree = top;             // Update free list head
            return out;
        }

        /**
         * Print array contents.
         * Time Complexity: O(n)
         * Space Complexity: O(1)
         * 
         * @param arr Array to print
         */
        public void printStack(int arr[]) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }
}