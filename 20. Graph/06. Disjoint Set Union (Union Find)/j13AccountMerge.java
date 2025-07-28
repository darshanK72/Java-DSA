/**
 * LeetCode 721. Accounts Merge
 * 
 * Problem Statement:
 *     Given a list of accounts where each account is a list of strings with first 
 *     element being the name and rest being emails. Merge accounts if they belong 
 *     to the same person (have common emails). Return merged accounts with emails 
 *     in sorted order.
 * 
 * Input:
 *     - accounts: List<List<String>> where first string is name, rest are emails
 *     - 1 ≤ accounts.length ≤ 1000
 *     - 2 ≤ accounts[i].length ≤ 10
 *     - 1 ≤ accounts[i][j].length ≤ 30
 * 
 * Output:
 *     - List<List<String>>: Merged accounts with sorted emails
 * 
 * Example:
 *     Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],
 *                       ["John","johnsmith@mail.com","john00@mail.com"],
 *                       ["Mary","mary@mail.com"],
 *                       ["John","johnnybravo@mail.com"]]
 *     Output: [["John","john00@mail.com","johnsmith@mail.com","john_newyork@mail.com"],
 *              ["Mary","mary@mail.com"],
 *              ["John","johnnybravo@mail.com"]]
 * 
 *     Explanation:
 *     First and second John accounts are merged due to common email johnsmith@mail.com
 *     Third Mary account remains separate
 *     Fourth John account remains separate as no common emails
 */

import java.util.*;

public class j13AccountMerge {
    /**
     * Helper class for Disjoint Set Union data structure
     */
    public static class DisjointSetUnion {
        int[] parents;
        int[] ranks;

        /**
         * Initialize DSU with n nodes
         * Time Complexity: O(n)
         * Space Complexity: O(n)
         */
        public DisjointSetUnion(int n) {
            this.parents = new int[n];
            this.ranks = new int[n];
            Arrays.fill(parents, -1);
            Arrays.fill(ranks, 1);
        }

        /**
         * Find parent of node with path compression
         * 
         * Intuition:
         * - Use path compression to optimize future lookups
         * - Each find operation updates parent pointers to root
         * 
         * Explanation:
         * - Step 1: If node is root (parent = -1), return node
         * - Step 2: Recursively find true parent and update parent pointer
         * - Step 3: Return compressed path parent
         * 
         * Time Complexity: O(α(n)) ≈ O(1)
         * Space Complexity: O(1)
         */
        public int find(int node) {
            if (parents[node] == -1)
                return node;
            parents[node] = find(parents[node]);
            return parents[node];
        }

        /**
         * Union two nodes by rank
         * 
         * Intuition:
         * - Use union by rank to keep tree balanced
         * - Attach smaller tree to larger tree's root
         * 
         * Explanation:
         * - Step 1: Find parents of both nodes
         * - Step 2: If same parent, nodes already connected
         * - Step 3: Compare ranks and merge smaller into larger
         * - Step 4: Update ranks for merged trees
         * 
         * Time Complexity: O(α(n)) ≈ O(1)
         * Space Complexity: O(1)
         */
        public void union(int node1, int node2) {
            int node1Parent = find(node1);
            int node2Parent = find(node2);
            if (node1Parent == node2Parent)
                return;
            if (ranks[node1Parent] < ranks[node2Parent]) {
                parents[node1Parent] = node2Parent;
                ranks[node2Parent] += ranks[node1Parent];
            } else {
                parents[node2Parent] = node1Parent;
                ranks[node1Parent] += ranks[node2Parent];
            }
        }
    }

    /**
     * Approach: Union Find with Email Mapping
     * 
     * Intuition:
     * - Use emails as edges to connect accounts
     * - Map each email to an account index
     * - Use DSU to merge connected accounts
     * 
     * Explanation:
     * - Step 1: Create DSU with size = number of accounts
     * - Step 2: Map each email to first account index it appears in
     * - Step 3: For each email:
     *     - If email already exists, union current account with mapped account
     *     - Else add email to map with current account index
     * - Step 4: Group emails by their parent account
     * - Step 5: Create final list with account names and sorted emails
     * 
     * Time Complexity: O(NK * log(NK)) where N = accounts.length, K = max emails per account
     * Space Complexity: O(NK) for storing email groups
     * 
     * @param accounts List of accounts where each account is [name, email1, email2,...]
     * @return        List of merged accounts with sorted emails
     */
    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        // Initialize DSU with number of accounts
        DisjointSetUnion dsu = new DisjointSetUnion(accounts.size());
        
        // Map emails to account indices
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String mail = accounts.get(i).get(j);
                if (map.containsKey(mail)) {
                    dsu.union(map.get(mail), i);
                } else {
                    map.put(mail, i);
                }
            }
        }

        // Group emails by parent account
        ArrayList[] result = new ArrayList[accounts.size()];
        for (int i = 0; i < accounts.size(); i++)
            result[i] = new ArrayList<>();

        for (String key : map.keySet()) {
            int parent = dsu.find(map.get(key));
            result[parent].add(key);
        }

        // Create final merged accounts list
        List<List<String>> out = new ArrayList<>();
        for (int i = 0; i < accounts.size(); i++) {
            if (result[i].size() > 0) {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(accounts.get(i).get(0));
                Collections.sort(result[i]);
                temp.addAll(result[i]);
                out.add(temp);
            }
        }

        return out;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        List<List<String>> accounts1 = Arrays.asList(
            Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"),
            Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"),
            Arrays.asList("Mary", "mary@mail.com"),
            Arrays.asList("John", "johnnybravo@mail.com")
        );
        System.out.println("Test Case 1:");
        System.out.println("Input: " + accounts1);
        System.out.println("Output: " + accountsMerge(accounts1));

        // Test Case 2: Single account
        List<List<String>> accounts2 = Arrays.asList(
            Arrays.asList("Alex", "alex@mail.com")
        );
        System.out.println("\nTest Case 2:");
        System.out.println("Input: " + accounts2);
        System.out.println("Output: " + accountsMerge(accounts2));

        // Test Case 3: Multiple merges
        List<List<String>> accounts3 = Arrays.asList(
            Arrays.asList("David", "d1@mail.com", "d2@mail.com"),
            Arrays.asList("David", "d2@mail.com", "d3@mail.com"),
            Arrays.asList("David", "d3@mail.com", "d4@mail.com")
        );
        System.out.println("\nTest Case 3:");
        System.out.println("Input: " + accounts3);
        System.out.println("Output: " + accountsMerge(accounts3));
    }
}
