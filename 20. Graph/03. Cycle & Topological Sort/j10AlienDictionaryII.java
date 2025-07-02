/**
 * LeetCode 269. Alien Dictionary (Topological Sort)
 * 
 * Problem Statement:
 *     There is a new alien language that uses the English alphabet. However, the order among letters is unknown to you. You are given a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
 * 
 * Input:
 *     - words (1 <= words.length <= 100)
 *     - Each word consists of lowercase English letters (a-z)
 *     - The words are sorted lexicographically by the alien dictionary
 * 
 * Output:
 *     - A string representing the characters in the correct order. If there are multiple valid orders, return any. If it is impossible, return an empty string.
 * 
 * Example:
 *     Input: ["wrt", "wrf", "er", "ett", "rftt"]
 *     Output: "wertf"
 * 
 *     Explanation:
 *     The correct order is: w -> e -> r -> t -> f
 *     Step by step:
 *     - 'w' comes before 'e' (from "wrt" vs "er")
 *     - 'e' comes before 'r' (from "er" vs "ett")
 *     - 'r' comes before 't' (from "er" vs "ett")
 *     - 't' comes before 'f' (from "rftt" vs "wrf")
 */

import java.util.*;

public class j10AlienDictionaryII {

    /**
     * Approach 1: BFS (Kahn's Algorithm for Topological Sort)
     * 
     * Intuition:
     * - Build a directed graph where an edge u->v means u comes before v.
     * - Use BFS to process nodes with zero indegree, ensuring lexicographical order.
     * 
     * Explanation:
     * - Step 1: Build the graph from the word list by comparing adjacent words.
     * - Step 2: Compute indegrees for each character.
     * - Step 3: Use a queue to process nodes with zero indegree.
     * - Step 4: Append processed nodes to the result string.
     * - Step 5: If all nodes are processed, return the result; else, return "" (cycle detected).
     * 
     * Time Complexity: O(N + K) where N is the total number of characters in all words, K is the number of unique characters.
     * Space Complexity: O(K^2) for the adjacency list and indegree map.
     * 
     * @param words    Array of words sorted by alien dictionary (1 <= words.length <= 100)
     * @return        String representing the order of characters, or "" if impossible
     */
    public static String findOrderBFS(String[] words) {
        // Build the graph from the list of words
        HashMap<Character, ArrayList<Character>> graph = generateGraph(words);
        if (graph == null)
            return ""; // Invalid order due to prefix issue
        return alienDictionaryBFS(graph);
    }

    /**
     * Approach 2: DFS (Topological Sort)
     * 
     * Intuition:
     * - Build a directed graph as before.
     * - Use DFS to perform topological sort, appending nodes post-visit.
     * 
     * Explanation:
     * - Step 1: Build the graph from the word list.
     * - Step 2: Use DFS to visit each node, marking visited.
     * - Step 3: Append nodes to result after visiting all neighbors.
     * - Step 4: Reverse the result to get the correct order.
     * - Step 5: If a cycle is detected, return "".
     * 
     * Time Complexity: O(N + K)
     * Space Complexity: O(K^2)
     * 
     * @param words    Array of words sorted by alien dictionary
     * @return        String representing the order of characters, or "" if impossible
     */
    public static String findOrderDFS(String[] words) {
        // Build the graph from the list of words
        HashMap<Character, ArrayList<Character>> graph = generateGraph(words);
        if (graph == null)
            return ""; // Invalid order due to prefix issue
        return alienDictionaryDFS(graph);
    }

    /**
     * Helper Method: BFS Topological Sort
     * 
     * Intuition:
     * - Use Kahn's algorithm to process nodes with zero indegree.
     * - Ensures lexicographical order by always processing available nodes with zero indegree.
     * 
     * Explanation:
     * - Compute indegrees for all nodes.
     * - Use a queue to process nodes with zero indegree, appending to result.
     * - For each processed node, decrement indegree of its neighbors.
     * - If a neighbor's indegree becomes zero, add it to the queue.
     * - If all nodes are processed, return the result; otherwise, a cycle exists.
     * 
     * Time Complexity: O(K + E), where K is the number of unique characters, E is the number of edges.
     * Space Complexity: O(K^2)
     * 
     * @param graph    Adjacency list of the alien dictionary
     * @return        String representing the order of characters, or "" if impossible
     */
    private static String alienDictionaryBFS(HashMap<Character, ArrayList<Character>> graph) {
        // Initialize indegree map for all unique characters
        HashMap<Character, Integer> indegrees = new HashMap<>();
        for (char key : graph.keySet()) {
            indegrees.put(key, 0); // Set initial indegree to 0
        }
        // Compute indegrees by traversing adjacency list
        for (char key : graph.keySet()) {
            for (char neighbor : graph.get(key)) {
                indegrees.put(neighbor, indegrees.getOrDefault(neighbor, 0) + 1); // Increment indegree for each neighbor
            }
        }
        // Initialize queue for BFS
        Queue<Character> queue = new LinkedList<>();
        StringBuilder out = new StringBuilder();

        // Add all nodes with zero indegree to the queue
        for (char key : indegrees.keySet()) {
            if (indegrees.get(key) == 0) {
                queue.add(key);
            }
        }

        // Process nodes in BFS order
        while (!queue.isEmpty()) {
            char c = queue.poll(); // Remove node from queue
            out.append(c); // Append to result
            for (char neb : graph.get(c)) {
                // Decrement indegree for each neighbor
                indegrees.put(neb, indegrees.getOrDefault(neb, 1) - 1);
                if (indegrees.get(neb) == 0) {
                    queue.add(neb); // Add neighbor to queue if indegree is now zero
                }
            }
        }

        // If not all nodes are processed, a cycle exists
        if (out.length() < graph.size()) {
            return "";
        }

        return out.toString();
    }

    /**
     * Helper Method: DFS Topological Sort with Cycle Detection
     * 
     * Intuition:
     * - Use DFS to visit each node, appending nodes post-visit for topological order.
     * - Use a 'visiting' set to detect cycles (back edges).
     * 
     * Explanation:
     * - For each unvisited node, start DFS.
     * - If a node is already in 'visiting', a cycle is detected.
     * - After visiting all neighbors, move node from 'visiting' to 'visited' and append to result.
     * - Reverse the result to get the correct order.
     * 
     * Time Complexity: O(K + E)
     * Space Complexity: O(K)
     * 
     * @param graph      Adjacency list
     * @return           String representing the order of characters, or "" if impossible
     */
    private static String alienDictionaryDFS(HashMap<Character, ArrayList<Character>> graph) {
        HashSet<Character> visited = new HashSet<>(); // Set of fully visited nodes
        HashSet<Character> visiting = new HashSet<>(); // Set of nodes in current DFS path
        StringBuilder out = new StringBuilder(); // Stores topological order
        for (char c : graph.keySet()) {
            if (!visited.contains(c)) {
                // Start DFS for unvisited node
                if (!dfs(graph, visited, visiting, c, out)) {
                    return ""; // Cycle detected
                }
            }
        }
        return out.reverse().toString(); // Reverse to get correct order
    }

    /**
     * Helper Method: DFS with cycle detection
     * 
     * Intuition:
     * - Standard DFS with an extra set to detect cycles (nodes currently in the stack).
     * 
     * Explanation:
     * - If a node is in 'visiting', a cycle is found.
     * - After visiting all neighbors, move node from 'visiting' to 'visited'.
     * 
     * Time Complexity: O(K + E)
     * Space Complexity: O(K)
     * 
     * @param graph      Adjacency list
     * @param visited    Set of fully visited nodes
     * @param visiting   Set of nodes in current path
     * @param c          Current node
     * @param out        Output builder
     * @return           True if no cycle, false if cycle detected
     */
    private static boolean dfs(HashMap<Character, ArrayList<Character>> graph, HashSet<Character> visited, HashSet<Character> visiting, char c, StringBuilder out) {
        // If node is in current path, a cycle is detected
        if (visiting.contains(c)) return false;
        // If node is already fully visited, skip
        if (visited.contains(c)) return true;
        visiting.add(c); // Mark node as visiting
        for (char neb : graph.get(c)) {
            // Visit all neighbors recursively
            if (!dfs(graph, visited, visiting, neb, out)) {
                return false; // Cycle detected in recursion
            }
        }
        visiting.remove(c); // Remove from current path
        visited.add(c); // Mark as fully visited
        out.append(c); // Append to result after visiting all neighbors
        return true;
    }

    /**
     * Helper Method: Build the graph from the word list
     * 
     * Intuition:
     * - Add all unique characters as nodes.
     * - For each adjacent pair of words, add an edge from the first differing character.
     * 
     * Explanation:
     * - If a prefix issue is found (e.g., ["abc", "ab"]), return null.
     * 
     * Time Complexity: O(N)
     * Space Complexity: O(K^2)
     * 
     * @param words    Array of words
     * @return        Adjacency list or null if invalid
     */
    private static HashMap<Character, ArrayList<Character>> generateGraph(String[] words) {
        // Initialize graph with all unique characters as nodes
        HashMap<Character, ArrayList<Character>> graph = new HashMap<>();
        for (String s : words) {
            for (char c : s.toCharArray()) {
                graph.putIfAbsent(c, new ArrayList<>()); // Add node if not present
            }
        }
        // Add edges based on first differing character between adjacent words
        for (int i = 0; i < words.length - 1; i++) {
            if (!addEdge(graph, words[i], words[i + 1]))
                return null; // Invalid order due to prefix issue
        }
        return graph;
    }

    /**
     * Helper Method: Add edge between first differing characters
     * 
     * Intuition:
     * - For each pair of adjacent words, find the first differing character and add an edge.
     * 
     * Explanation:
     * - If word a is longer than b and b is a prefix, return false (invalid).
     * 
     * Time Complexity: O(L) where L is min length of a, b
     * Space Complexity: O(1)
     * 
     * @param graph    Adjacency list
     * @param a        First word
     * @param b        Second word
     * @return         True if edge added or valid, false if invalid
     */
    private static boolean addEdge(HashMap<Character, ArrayList<Character>> graph, String a, String b) {
        // Compare characters of both words
        for (int i = 0; i < Math.min(a.length(), b.length()); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                // Add edge from first differing character in a to b
                graph.get(a.charAt(i)).add(b.charAt(i));
                return true;
            }
        }
        // If a is longer than b and b is a prefix, invalid order
        if (a.length() > b.length()) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        String[] words1 = {"wrt", "wrf", "er", "ett", "rftt"};
        System.out.println("Input: " + Arrays.toString(words1) + ", Expected: wertf, Output (BFS): " + findOrderBFS(words1));
        System.out.println("Input: " + Arrays.toString(words1) + ", Output (DFS): " + findOrderDFS(words1));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        String[] words2 = {"z", "x"};
        System.out.println("Input: " + Arrays.toString(words2) + ", Output (BFS): " + findOrderBFS(words2));
        System.out.println("Input: " + Arrays.toString(words2) + ", Output (DFS): " + findOrderDFS(words2));

        String[] words3 = {"z", "x", "z"}; // Cycle
        System.out.println("Input: " + Arrays.toString(words3) + ", Expected: '', Output (BFS): " + findOrderBFS(words3));
        System.out.println("Input: " + Arrays.toString(words3) + ", Output (DFS): " + findOrderDFS(words3));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        String[] words4 = {"abc", "ab"}; // Invalid prefix
        System.out.println("Input: " + Arrays.toString(words4) + ", Expected: '', Output (BFS): " + findOrderBFS(words4));
        System.out.println("Input: " + Arrays.toString(words4) + ", Output (DFS): " + findOrderDFS(words4));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        String[] words5 = {"a", "b", "c"};
        System.out.println("Input: " + Arrays.toString(words5) + ", Output (BFS): " + findOrderBFS(words5));
        System.out.println("Input: " + Arrays.toString(words5) + ", Output (DFS): " + findOrderDFS(words5));
    }
}