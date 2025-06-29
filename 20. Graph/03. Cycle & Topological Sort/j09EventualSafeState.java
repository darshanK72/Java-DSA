/**
 * LeetCode 802. Find Eventual Safe States
 * 
 * Problem Statement:
 *     Given a directed graph with n nodes labeled from 0 to n-1, where graph[i] is a list of nodes j such that there is a directed edge from node i to node j.
 *     A node is a terminal node if it has no outgoing edges. A node is a safe node if every possible path starting from that node leads to a terminal node (i.e., no cycles).
 *     Return an array containing all the safe nodes in ascending order.
 * 
 * Input:
 *     - graph (int[][]): Adjacency list of the directed graph
 * 
 * Output:
 *     - List<Integer>: List of all safe nodes in ascending order
 * 
 * Example:
 *     Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 *     Output: [2,4,5,6]
 *     Explanation: Nodes 2, 4, 5, 6 are eventually safe.
 */

import java.util.*;

public class j09EventualSafeState {
    /**
     * Approach 1: DFS with Recursion Stack
     * 
     * Intuition:
     * - The core idea is to identify nodes that are not part of any cycle and from which all possible paths eventually lead to terminal nodes (nodes with no outgoing edges).
     * - We use DFS to traverse the graph and keep track of the current path using a recursion stack. If we revisit a node that is already in the current path, it means we've found a cycle.
     * - Any node that is part of a cycle, or can reach a cycle, is not safe. Conversely, if all paths from a node eventually terminate (do not revisit any node in the current path), that node is safe.
     * - We use three arrays:
     *   1. visited[]: Marks nodes that have been fully processed.
     *   2. recStack[]: Marks nodes currently in the recursion stack (current DFS path).
     *   3. isSafe[]: Marks nodes that are confirmed to be safe (not part of or leading to a cycle).
     * - By marking nodes as safe as soon as we confirm all their paths are cycle-free, we avoid redundant work and speed up the process for overlapping subproblems.
     * 
     * Explanation:
     * - For each node, if it hasn't been visited, start a DFS from that node.
     * - In the DFS:
     *   - Mark the node as visited and add it to the recursion stack.
     *   - For each neighbor, recursively perform DFS:
     *     - If a neighbor is already in the recursion stack, a cycle is detected, and the current node is not safe.
     *     - If a neighbor is not safe (already determined), the current node is also not safe.
     *   - After all neighbors are processed, remove the node from the recursion stack and mark it as safe.
     * - After processing all nodes, collect and return all nodes marked as safe.
     * - This approach ensures that each node is processed only once, and the recursion stack efficiently detects cycles.
     * - Example: For graph = [[1,2],[2,3],[5],[0],[5],[],[]], nodes 2, 4, 5, 6 are safe because all paths from them eventually terminate without cycles.
     * 
     * Time Complexity: O(V + E), where V = number of nodes, E = number of edges
     * Space Complexity: O(V), for visited, recStack, and isSafe arrays
     * 
     * @param graph   Adjacency list of the directed graph
     * @return        List of all safe nodes in ascending order
     */
    public static List<Integer> eventualSafeNodesDFS(int[][] graph) {
        boolean[] visited = new boolean[graph.length]; // Track visited nodes
        boolean[] recStack = new boolean[graph.length]; // Track nodes in current DFS path
        boolean[] isSafe = new boolean[graph.length]; // Mark safe nodes
        for(int i = 0; i < graph.length; i++){
            if(!visited[i]){
                dfs(graph,visited,recStack,isSafe,i); // Start DFS for unvisited nodes
            }
        }
        ArrayList<Integer> out = new ArrayList<>(); // Output list for safe nodes
        for(int i = 0; i < graph.length; i++){
            if(isSafe[i]) out.add(i); // Add safe nodes to output
        }
        return out;
    }

    /**
     * Helper Method: DFS Cycle Detection
     * 
     * Intuition:
     * - The helper method is responsible for the actual DFS traversal and cycle detection.
     * - By marking nodes in the recursion stack, we can efficiently detect cycles during traversal.
     * - If a node is revisited while still in the recursion stack, it means the current path forms a cycle.
     * - If a node is already marked as safe, we can skip further checks for that node, optimizing the process.
     * 
     * Explanation:
     * - For each neighbor of the current node:
     *   - If the neighbor is not visited, recursively check for cycles.
     *   - If the neighbor is already in the recursion stack or not safe, a cycle is detected.
     * - After all neighbors are checked, remove the node from the recursion stack and mark it as safe if no cycles are found.
     * - This method ensures that each node is only marked as safe if all paths from it are cycle-free.
     * 
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     * 
     * @param graph     Adjacency list
     * @param visited   Visited array
     * @param recStack  Recursion stack array
     * @param isSafe    Safe node array
     * @param src       Current node
     * @return          True if cycle detected, false otherwise
     */
    public static boolean dfs(int[][] graph,boolean[] visited,boolean[] recStack,boolean[] isSafe,int src){
        if(recStack[src]) return true; // Node is in recursion stack, cycle detected
        if(visited[src]) return false; // Node already processed, no cycle
        visited[src] = true; // Mark node as visited
        recStack[src] = true; // Add node to recursion stack
        for(int neb : graph[src]){
            if(!visited[neb]){
                if(dfs(graph,visited,recStack,isSafe,neb)) return true; // Recurse for neighbors
            }else if(!isSafe[neb]){
                return true; // Neighbor is not safe, cycle detected
            }
        }
        recStack[src] = false; // Remove node from recursion stack
        isSafe[src] = true; // Mark node as safe
        return false; // No cycle detected from this node
    }

    /**
     * Approach 2: Kahn's Algorithm (BFS on Reverse Graph)
     * 
     * Intuition:
     * - The main insight is that a node is safe if it cannot reach any cycle, i.e., all paths from it eventually terminate.
     * - By reversing the graph, we can use Kahn's algorithm (BFS for topological sorting) to identify all nodes that can reach terminal nodes.
     * - In the reversed graph, terminal nodes in the original graph become sources (in-degree 0), and we can propagate safety backward.
     * - As we remove nodes with in-degree 0, we mark their predecessors as safe if they become sources as well.
     * - This process continues until all safe nodes are identified.
     * 
     * Explanation:
     * - Build the reversed adjacency list: for each edge u -> v in the original graph, add v -> u in the reversed graph.
     * - Compute the in-degree for each node in the reversed graph.
     * - Initialize a queue with all nodes having in-degree 0 (terminal nodes in the original graph).
     * - While the queue is not empty:
     *   - Remove a node from the queue and add it to the list of safe nodes.
     *   - For each neighbor in the reversed graph, decrement its in-degree.
     *   - If a neighbor's in-degree becomes 0, add it to the queue.
     * - After processing, sort the list of safe nodes in ascending order and return it.
     * - This approach ensures that only nodes that can reach terminal nodes (and not cycles) are marked as safe.
     * - Example: For graph = [[1,2],[2,3],[5],[0],[5],[],[]], nodes 2, 4, 5, 6 are safe because they can reach terminal nodes without cycles.
     * 
     * Time Complexity: O(V + E)
     * Space Complexity: O(V + E)
     * 
     * @param graph   Adjacency list of the directed graph
     * @return        List of all safe nodes in ascending order
     */
    public static List<Integer> eventualSafeNodesKahnBFS(int[][] graph) {
        int n = graph.length;
        ArrayList<Integer>[] adj = new ArrayList[n]; // Reversed adjacency list
        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>(); // Initialize reversed adjacency list
        for(int i = 0; i < n; i++){
            for(int to : graph[i]){
                adj[to].add(i); // Reverse the edge direction
            } 
        }

        int[] indegrees = new int[n]; // In-degree array for reversed graph
        for(ArrayList<Integer> list : adj){
            for(int neb : list){
                indegrees[neb]++; // Count incoming edges for each node
            }
        }

        Queue<Integer> queue = new LinkedList<>(); // Queue for BFS
        for(int i = 0; i < n; i++){
            if(indegrees[i] == 0){
                queue.add(i); // Add nodes with in-degree 0 (terminal nodes)
            }
        }
        ArrayList<Integer> out = new ArrayList<>(); // Output list for safe nodes
        while(!queue.isEmpty()){
            int node = queue.poll(); // Remove node from queue
            out.add(node); // Add node to output
            for(int neb : adj[node]){
                indegrees[neb]--; // Remove edge from current node
                if(indegrees[neb] == 0){
                    queue.add(neb); // If in-degree becomes 0, add to queue
                }
            }
        }

        Collections.sort(out); // Sort output in ascending order
        return out;
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: graph=[[1,2],[2,3],[5],[0],[5],[],[]], Output: " + eventualSafeNodesDFS(new int[][]{{1,2},{2,3},{5},{0},{5},{}, {}}));
        System.out.println("Input: graph=[[1,2,3,4],[1,2],[3,4],[0,4],[]], Output: " + eventualSafeNodesDFS(new int[][]{{1,2,3,4},{1,2},{3,4},{0,4},{}}));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: graph=[[]], Output: " + eventualSafeNodesDFS(new int[][]{{}}));
        System.out.println("Input: graph=[[0]], Output: " + eventualSafeNodesDFS(new int[][]{{0}}));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: graph with 1000 isolated nodes, Output: " + eventualSafeNodesDFS(new int[1000][0]).size());

        // Special Cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: graph=[[1],[2],[3],[4],[5],[6],[4]], Output: " + eventualSafeNodesDFS(new int[][]{{1},{2},{3},{4},{5},{6},{4}}));
        System.out.println("Input: graph=[[1],[2],[0]], Output: " + eventualSafeNodesDFS(new int[][]{{1},{2},{0}}));
    }
}
