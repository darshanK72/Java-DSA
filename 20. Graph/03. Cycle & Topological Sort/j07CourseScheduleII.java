/**
 * LeetCode 210. Course Schedule II
 * 
 * Problem Statement:
 *     There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 *     Some courses may have prerequisites, given as pairs [a, b], meaning to take course a you must first take course b.
 *     Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
 * 
 * Input:
 *     - numCourses (1 <= numCourses <= 2000): Total number of courses
 *     - prerequisites (0 <= prerequisites.length <= 5000): int[][], each element is [a, b]
 * 
 * Output:
 *     - int[]: A valid order to finish all courses, or empty array if impossible
 * 
 * Example:
 *     Input: numCourses = 2, prerequisites = [[1,0]]
 *     Output: [0,1]
 *     Explanation: Take course 0 first, then course 1.
 * 
 *     Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 *     Output: [0,2,1,3] or [0,1,2,3]
 *     Explanation: There are multiple valid orders.
 */

import java.util.*;

public class j07CourseScheduleII {
    /**
     * Approach: Kahn's Algorithm (BFS Topological Sort)
     * 
     * Intuition:
     * - Model courses as a directed graph. A valid topological order exists if and only if there is no cycle.
     * - Use in-degree array and process nodes with in-degree 0 to build the order.
     * 
     * Explanation:
     * - Build adjacency list and in-degree array from prerequisites.
     * - Add all nodes with in-degree 0 to the output array.
     * - Remove nodes from output, reduce in-degree of neighbors, and add new in-degree 0 nodes.
     * - If all nodes are processed, return the order; else, return empty array.
     * 
     * Time Complexity: O(V + E), where V = numCourses, E = prerequisites.length
     * Space Complexity: O(V + E), for adjacency list, in-degree array, and output array
     * 
     * @param numCourses    Number of courses (1 <= numCourses <= 2000)
     * @param prerequisites Prerequisite pairs (0 <= prerequisites.length <= 5000)
     * @return              Valid order to finish all courses, or empty array if impossible
     */
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] adj = new ArrayList[numCourses]; // Adjacency list for graph
        for (int i = 0; i < numCourses; i++)
            adj[i] = new ArrayList<>(); // Initialize adjacency list for each course
        for (int[] pre : prerequisites) {
            int from = pre[1];
            int to = pre[0];
            adj[from].add(to); // Add directed edge from prerequisite to course
        }

        int[] inDegree = new int[numCourses]; // Array to store in-degree of each course
        for (ArrayList<Integer> list : adj) {
            for (int neb : list) {
                inDegree[neb]++; // Count incoming edges for each course
            }
        }

        int[] order = new int[numCourses]; // Output array for course order
        int push = 0; // Index to insert next course in order
        int pop = 0;  // Index to process next course in order
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                order[push++] = i; // Add courses with no prerequisites to order
            }
        }

        while (pop < push && push < numCourses) {
            int node = order[pop++]; // Process next course in order
            for (int neb : adj[node]) {
                inDegree[neb]--; // Remove edge from current course
                if (inDegree[neb] == 0) {
                    order[push++] = neb; // If in-degree becomes 0, add to order
                }
            }
        }

        if (push == numCourses)
            return order; // All courses processed, return valid order
        return new int[0]; // Cycle detected, return empty array
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: numCourses=2, prerequisites=[[1,0]], Output: " + Arrays.toString(findOrder(2, new int[][]{{1,0}})));
        System.out.println("Input: numCourses=4, prerequisites=[[1,0],[2,0],[3,1],[3,2]], Output: " + Arrays.toString(findOrder(4, new int[][]{{1,0},{2,0},{3,1},{3,2}})));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: numCourses=1, prerequisites=[], Output: " + Arrays.toString(findOrder(1, new int[][]{})));
        System.out.println("Input: numCourses=0, prerequisites=[], Output: " + Arrays.toString(findOrder(0, new int[][]{})));
        System.out.println("Input: numCourses=2, prerequisites=[[0,1],[1,0]], Output: " + Arrays.toString(findOrder(2, new int[][]{{0,1},{1,0}})));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: numCourses=2000, prerequisites=[], Output: " + (findOrder(2000, new int[][]{}).length == 2000 ? "Valid order of length 2000" : "Invalid"));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: numCourses=3, prerequisites=[[1,0],[2,1]], Output: " + Arrays.toString(findOrder(3, new int[][]{{1,0},{2,1}})));
        System.out.println("Input: numCourses=3, prerequisites=[[1,0],[0,2],[2,1]], Output: " + Arrays.toString(findOrder(3, new int[][]{{1,0},{0,2},{2,1}})));
    }
}
