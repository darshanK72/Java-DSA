/**
 * LeetCode 1136. Parallel Courses
 * 
 * Problem Statement:
 *     There are n courses labeled from 1 to n. Some courses have prerequisites, given as pairs [a, b], meaning to take course b you must first take course a.
 *     Return the minimum number of semesters required to complete all courses. If it is impossible, return -1.
 * 
 * Input:
 *     - n (1 <= n <= 5000): Number of courses
 *     - prerequisites (0 <= prerequisites.length <= 5000): int[][], each element is [a, b]
 * 
 * Output:
 *     - int: Minimum number of semesters to finish all courses, or -1 if impossible
 * 
 * Example:
 *     Input: n = 3, prerequisites = [[1,3],[2,3]]
 *     Output: 2
 *     Explanation: Take courses 1 and 2 in the first semester, then course 3 in the second.
 * 
 *     Input: n = 3, prerequisites = [[1,2],[2,3],[3,1]]
 *     Output: -1
 *     Explanation: There is a cycle, so it is impossible to finish all courses.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class j08ParallelCourses {
    /**
     * Approach: BFS Topological Sort (Kahn's Algorithm, Level Order)
     * 
     * Intuition:
     * - Model courses as a directed graph. Each semester, take all courses with no prerequisites (in-degree 0).
     * - The number of BFS levels is the minimum semesters needed.
     * - If a cycle exists, not all courses can be completed.
     * 
     * Explanation:
     * - Build adjacency list and in-degree array.
     * - Use a queue to process all nodes with in-degree 0 each semester.
     * - For each course taken, reduce in-degree of its neighbors.
     * - If all courses are processed, return the number of semesters; else, return -1.
     * 
     * Time Complexity: O(N + E), where N = n, E = prerequisites.length
     * Space Complexity: O(N + E), for adjacency list, in-degree array, and queue
     * 
     * @param n             Number of courses (1 <= n <= 5000)
     * @param prerequisites Prerequisite pairs (0 <= prerequisites.length <= 5000)
     * @return              Minimum semesters to finish all courses, or -1 if impossible
     */
    public static int parallelCourses(int n, int[][] prerequisites) {
        ArrayList<Integer>[] adj = new ArrayList[n + 1]; // Adjacency list for graph (1-based)
        for (int i = 0; i <= n; i++)
            adj[i] = new ArrayList<>(); // Initialize adjacency list for each course
        for (int[] pre : prerequisites) {
            int from = pre[0];
            int to = pre[1];
            adj[from].add(to); // Add directed edge from prerequisite to course
        }

        int[] inDegree = new int[n + 1]; // Array to store in-degree of each course
        for (ArrayList<Integer> list : adj) {
            for (int neb : list) {
                inDegree[neb]++; // Count incoming edges for each course
            }
        }

        Queue<Integer> queue = new LinkedList<>(); // Queue for BFS
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i); // Add courses with no prerequisites to queue
            }
        }

        int semesters = 0; // Number of semesters needed
        int processed = 0; // Number of courses processed
        while (!queue.isEmpty()) {
            int size = queue.size(); // Number of courses that can be taken this semester
            semesters++; // Increment semester count
            for (int i = 0; i < size; i++) {
                int node = queue.poll(); // Remove course from queue
                processed++; // Increment processed course count
                for (int neb : adj[node]) { // Traverse all dependent courses
                    inDegree[neb]--; // Remove edge from current course
                    if (inDegree[neb] == 0) {
                        queue.add(neb); // If in-degree becomes 0, add to queue
                    }
                }
            }
        }

        return (processed == n) ? semesters : -1; // If all courses processed, return semesters; else, -1
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: n=3, prerequisites=[[1,3],[2,3]], Output: " + parallelCourses(3, new int[][]{{1,3},{2,3}}));
        System.out.println("Input: n=3, prerequisites=[[1,2],[2,3],[3,1]], Output: " + parallelCourses(3, new int[][]{{1,2},{2,3},{3,1}}));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: n=1, prerequisites=[], Output: " + parallelCourses(1, new int[][]{}));
        System.out.println("Input: n=2, prerequisites=[], Output: " + parallelCourses(2, new int[][]{}));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: n=5000, prerequisites=[], Output: " + parallelCourses(5000, new int[][]{}));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: n=4, prerequisites=[[1,2],[2,3],[3,4]], Output: " + parallelCourses(4, new int[][]{{1,2},{2,3},{3,4}}));
        System.out.println("Input: n=4, prerequisites=[[1,2],[1,3],[3,4]], Output: " + parallelCourses(4, new int[][]{{1,2},{1,3},{3,4}}));
    }
}
