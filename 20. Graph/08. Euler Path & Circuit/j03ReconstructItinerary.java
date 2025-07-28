/*-
 * LeetCode 332. Reconstruct Itinerary
 * 
 * Problem Statement:
 *     Given a list of airline tickets where tickets[i] = [fromi, toi], reconstruct
 *     the itinerary in order. All tickets belong to a person traveling from JFK.
 *     If multiple valid itineraries exist, return the one with the smallest
 *     lexical order when read as a single string.
 * 
 * Input:
 *     - tickets: List<List<String>> representing [from, to] airport pairs
 *     - 1 ≤ tickets.length ≤ 300
 *     - fromi, toi consist of three uppercase letters
 *     - Starting airport is always "JFK"
 * 
 * Output:
 *     - List<String>: Ordered list of airports forming valid itinerary
 * 
 * Example:
 *     Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"]]
 *     Output: ["JFK","ATL","JFK","SFO","ATL"]
 * 
 *     Explanation:
 *     One valid itinerary is JFK -> ATL -> JFK -> SFO -> ATL
 *     This has the smallest lexical order among all possible itineraries.
 */

import java.util.*;

public class j03ReconstructItinerary {
    /*-
     * Approach: Hierholzer's Algorithm (Eulerian Path)
     * 
     * Intuition:
     * - This is essentially finding an Eulerian path in a directed graph
     * - Use PriorityQueue to ensure lexicographically smallest path
     * - Build path backwards to handle dead ends properly
     * 
     * Explanation:
     * - Step 1: Build adjacency list using PriorityQueue for destinations
     * - Step 2: Start DFS from "JFK"
     * - Step 3: Recursively explore and remove edges (tickets)
     * - Step 4: Add airports to result in reverse order
     * - Step 5: Reverse final list to get correct order
     * 
     * Time Complexity: O(E * log E) where E = number of tickets
     * Space Complexity: O(V + E) where V = number of airports
     * 
     * @param tickets List of [from, to] airport pairs
     * @return       List of airports in itinerary order
     */
    public static List<String> findItinerary(List<List<String>> tickets) {
        // Create adjacency list using PriorityQueue for lexical ordering
        HashMap<String, PriorityQueue<String>> map = new HashMap<>();
        
        // Build graph from tickets
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);

            // Initialize PriorityQueues for airports if needed
            if (!map.containsKey(from))
                map.put(from, new PriorityQueue<>());
            if (!map.containsKey(to))
                map.put(to, new PriorityQueue<>());

            // Add destination to source's PriorityQueue
            map.get(from).add(to);
        }

        // Initialize result list and start DFS from JFK
        ArrayList<String> out = new ArrayList<>();
        dfs("JFK", map, out);
        
        // Reverse to get correct order (built backwards during DFS)
        Collections.reverse(out);
        return out;
    }

    /*-
     * Helper Method: DFS with Path Building
     * 
     * Intuition:
     * - Use post-order DFS to handle dead ends
     * - Add airports to path after exploring all options
     * 
     * Explanation:
     * - Step 1: While source has unvisited destinations:
     *     - Remove and visit next destination
     * - Step 2: Add source to path after all destinations visited
     * 
     * Time Complexity: O(E) for visiting each edge once
     * Space Complexity: O(V) for recursion stack
     */
    public static void dfs(String src, 
            HashMap<String, PriorityQueue<String>> map, 
            ArrayList<String> out) {
        // Visit all destinations from current airport
        while (map.get(src).size() > 0) {
            String neb = map.get(src).remove();  // Get next destination
            dfs(neb, map, out);                  // Recursively explore
        }
        out.add(src);  // Add current airport after exploring all options
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case with multiple valid paths
        System.out.println("\nBasic Test Case:");
        List<List<String>> tickets1 = Arrays.asList(
            Arrays.asList("JFK","SFO"),
            Arrays.asList("JFK","ATL"),
            Arrays.asList("SFO","ATL"),
            Arrays.asList("ATL","JFK")
        );
        System.out.println("Input: " + tickets1);
        System.out.println("Output: " + findItinerary(tickets1));
        System.out.println("Expected: [JFK, ATL, JFK, SFO, ATL]");

        // Test Case 2: Single valid path
        System.out.println("\nSingle Path Test Case:");
        List<List<String>> tickets2 = Arrays.asList(
            Arrays.asList("JFK","ATL"),
            Arrays.asList("ATL","LAX")
        );
        System.out.println("Input: " + tickets2);
        System.out.println("Output: " + findItinerary(tickets2));
        System.out.println("Expected: [JFK, ATL, LAX]");

        // Test Case 3: Circular path
        System.out.println("\nCircular Path Test Case:");
        List<List<String>> tickets3 = Arrays.asList(
            Arrays.asList("JFK","KUL"),
            Arrays.asList("KUL","JFK")
        );
        System.out.println("Input: " + tickets3);
        System.out.println("Output: " + findItinerary(tickets3));
        System.out.println("Expected: [JFK, KUL, JFK]");
    }
}
