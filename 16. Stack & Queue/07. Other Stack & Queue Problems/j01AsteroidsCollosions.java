/**
 * Problem Statement:
 *     LeetCode 735. Asteroid Collision
 * 
 *     We are given an array asteroids of integers representing asteroids in a row.
 *     For each asteroid, the absolute value represents its size, and the sign
 *     represents its direction (positive = right, negative = left). Each asteroid
 *     moves at the same speed. Find out the state of the asteroids after all
 *     collisions.
 * 
 * Input:
 *     - Array asteroids where 2 <= asteroids.length <= 10^4
 *     - -1000 <= asteroids[i] <= 1000
 *     - asteroids[i] != 0
 * 
 * Output:
 *     - Array representing the remaining asteroids after all collisions
 * 
 * Example 1:
 *     Input: asteroids = [5,10,-5]
 *     Output: [5,10]
 *     
 *     Explanation:
 *     - 10 and -5 collide => -5 is destroyed
 *     - 5 remains as it's moving right
 *     - 10 remains as it's moving right
 * 
 * Example 2:
 *     Input: asteroids = [8,-8]
 *     Output: []
 *     
 *     Explanation:
 *     - 8 and -8 collide and both are destroyed
 */

import java.util.Stack;

public class j01AsteroidsCollosions {


    public static void main(String args[]) {
        int[] asteroids = { 5, 10, -5 };
        int[] result = asteroidCollision(asteroids);
        for (int i : result) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    /**
     * Approach: Using Stack to Track Collisions
     * 
     * Intuition:
     * - Stack stores asteroids moving right (positive)
     * - For each left-moving asteroid (negative):
     *   * If stack empty or top moving left: add current
     *   * If equal size: both destroy
     *   * If current larger: pop and recheck
     *   * If current smaller: discard current
     * 
     * Time Complexity: O(n)
     * - Single pass through array
     * - Each asteroid pushed/popped at most once
     * 
     * Space Complexity: O(n)
     * - Stack can store up to n asteroids
     * - Output array of size up to n
     */
    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        
        // Process each asteroid
        for (int i = 0; i < asteroids.length; i++) {
            // Right-moving asteroid always gets added
            if (asteroids[i] > 0) {
                stack.push(asteroids[i]);
            } else {
                // Handle left-moving asteroid collision cases
                if (stack.isEmpty() || stack.peek() < 0) {
                    // Add if stack empty or top moving left
                    stack.push(asteroids[i]);
                } else if (stack.peek() == -asteroids[i]) {
                    // Equal size: both asteroids destroy each other
                    stack.pop();
                } else if (stack.peek() < -asteroids[i]) {
                    // Current asteroid is larger: destroy top and recheck
                    stack.pop();
                    i--;
                }
                // Implicit else: current asteroid is smaller and gets destroyed
            }
        }

        // Convert stack to array for result
        int[] out = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            out[i] = stack.pop();
        }
        return out;
    }
}