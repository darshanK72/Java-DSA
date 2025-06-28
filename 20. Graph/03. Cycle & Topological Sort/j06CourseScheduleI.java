/**
 * LeetCode 207. Course Schedule
 * 
 * Problem Statement:
 *     There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 *     Some courses may have prerequisites, given as pairs [a, b], meaning to take course a you must first take course b.
 *     Return true if it is possible to finish all courses. Otherwise, return false.
 * 
 * Input:
 *     - numCourses (1 <= numCourses <= 2000): Total number of courses
 *     - prerequisites (0 <= prerequisites.length <= 5000): int[][], each element is [a, b]
 * 
 * Output:
 *     - boolean: true if all courses can be finished, false otherwise
 * 
 * Example:
 *     Input: numCourses = 2, prerequisites = [[1,0]]
 *     Output: true
 *     Explanation: Take course 0 first, then course 1.
 * 
 *     Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 *     Output: false
 *     Explanation: There is a cycle (0->1->0), so not all courses can be finished.
 */

import java.util.*;

public class j06CourseScheduleI {

    /**
     * Approach 1: DFS Cycle Detection
     * 
     * Intuition:
     * - Model courses as a directed graph. If there is a cycle, not all courses can be finished.
     * - Use DFS to detect cycles in the graph.
     * 
     * Explanation:
     * - Build adjacency list from prerequisites.
     * - Use a visited array: 0 = unvisited, 1 = visiting, 2 = visited.
     * - For each node, if DFS finds a back edge (visiting a node already marked as 1), a cycle exists.
     * - If no cycles are found, all courses can be finished.
     * 
     * Time Complexity: O(V + E), where V = numCourses, E = prerequisites.length
     * Space Complexity: O(V + E), for adjacency list and visited array
     * 
     * @param numCourses    Number of courses (1 <= numCourses <= 2000)
     * @param prerequisites Prerequisite pairs (0 <= prerequisites.length <= 5000)
     * @return              True if all courses can be finished, false otherwise
     */
    public static boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        // Build adjacency list for the graph
        ArrayList<Integer>[] adj = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++)
            adj[i] = new ArrayList<>(); // Initialize adjacency list for each course
        for (int[] pre : prerequisites) {
            int to = pre[0];
            int from = pre[1];
            adj[from].add(to); // Add directed edge from prerequisite to course
        }

        int[] visited = new int[numCourses]; // 0=unvisited, 1=visiting, 2=visited
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) { // If course is unvisited
                if (dfsCycle(adj, visited, i)) // Start DFS to detect cycle
                    return false; // Cycle detected, cannot finish all courses
            }
        }
        return true; // No cycles found, all courses can be finished
    }

    /**
     * Helper Method: DFS Cycle Detection
     * 
     * Intuition:
     * - Recursively visit nodes, marking them as visiting (1).
     * - If a node is revisited while visiting, a cycle exists.
     * 
     * Explanation:
     * - Mark node as visiting (1).
     * - For each neighbor, recursively check for cycles.
     * - After visiting all neighbors, mark as visited (2).
     * 
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     * 
     * @param adj      Adjacency list
     * @param visited  Visited state array
     * @param src      Current node
     * @return         True if cycle detected, false otherwise
     */
    private static boolean dfsCycle(ArrayList<Integer>[] adj, int[] visited, int src) {
        if (visited[src] == 1)
            return true; // Node is being visited, cycle detected
        if (visited[src] == 2)
            return false; // Node already fully processed, no cycle here
        visited[src] = 1; // Mark node as visiting
        for (int neb : adj[src]) { // Traverse all neighbors (dependent courses)
            if (dfsCycle(adj, visited, neb)) // Recursively check for cycles
                return true; // Cycle detected in recursion
        }
        visited[src] = 2; // Mark node as fully processed
        return false; // No cycle detected from this node
    }

    /**
     * Approach 2: Kahn's Algorithm (BFS Topological Sort)
     * 
     * Intuition:
     * - If a topological ordering exists, all courses can be finished.
     * - Use in-degree array and process nodes with in-degree 0.
     * 
     * Explanation:
     * - Build adjacency list and in-degree array.
     * - Add all nodes with in-degree 0 to queue.
     * - Remove nodes from queue, reduce in-degree of neighbors.
     * - If all nodes are processed, return true; else, cycle exists.
     * 
     * Time Complexity: O(V + E)
     * Space Complexity: O(V + E)
     * 
     * @param numCourses    Number of courses
     * @param prerequisites Prerequisite pairs
     * @return              True if all courses can be finished, false otherwise
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] adj = new ArrayList[numCourses];
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

        Queue<Integer> queue = new LinkedList<>(); // Queue for BFS
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i); // Add courses with no prerequisites to queue
            }
        }

        int count = 0; // Counter for number of courses processed
        while (!queue.isEmpty()) {
            int node = queue.poll(); // Remove course from queue
            count++; // Increment processed course count
            for (int neb : adj[node]) { // Traverse all dependent courses
                inDegree[neb]--; // Remove edge from current course
                if (inDegree[neb] == 0) {
                    queue.offer(neb); // If in-degree becomes 0, add to queue
                }
            }
        }
        return count == numCourses; // If all courses processed, return true
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: numCourses=2, prerequisites=[[1,0]], Expected: true, Output: " + canFinish(2, new int[][]{{1,0}}));
        System.out.println("Input: numCourses=2, prerequisites=[[1,0],[0,1]], Expected: false, Output: " + canFinish(2, new int[][]{{1,0},{0,1}}));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: numCourses=1, prerequisites=[], Expected: true, Output: " + canFinish(1, new int[][]{}));
        System.out.println("Input: numCourses=0, prerequisites=[], Expected: true, Output: " + canFinish(0, new int[][]{}));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: numCourses=2000, prerequisites=[], Output: " + canFinish(2000, new int[][]{}));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: numCourses=3, prerequisites=[[1,0],[2,1]], Output: " + canFinish(3, new int[][]{{1,0},{2,1}}));
        System.out.println("Input: numCourses=3, prerequisites=[[1,0],[0,2],[2,1]], Output: " + canFinish(3, new int[][]{{1,0},{0,2},{2,1}}));
    }
}