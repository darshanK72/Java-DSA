/**
 * Disjoint Set Union (Union-Find) - Design and Test
 *
 * Problem Statement:
 *     Design a Disjoint Set Union (Union-Find) data structure that supports
 *     efficient union and find operations. Test the DSU with various union/find
 *     operations and verify the number of connected components and root checks.
 *
 * Input:
 *     - n (1 <= n <= 10^5): Number of nodes
 *     - Sequence of union and find operations
 *
 * Output:
 *     - Results of find operations and number of connected components
 *
 * Example:
 *     Input: n = 5, unions = [(0,1),(1,2),(3,4)], finds = [0,2,3,4]
 *     Output: find(0)=root0, find(2)=root0, find(3)=root3, find(4)=root3,
 *             components=2
 *
 *     Explanation:
 *         After unions, {0,1,2} are connected, {3,4} are connected.
 */

import java.util.*;

public class j01DesignDisjointSetUnion {
    /**
     * Disjoint Set Union (Union-Find) data structure
     *
     * Intuition:
     * - The DSU efficiently keeps track of which nodes belong to the same
     *   component. Each node starts in its own set; as we process connections,
     *   we merge sets.
     * - Path compression ensures that repeated find operations are fast by
     *   flattening the tree structure, so every node points directly to the root
     *   after a find.
     * - Union by size (or rank) ensures that the smaller tree is always merged
     *   under the larger, keeping the tree shallow and operations efficient.
     * - Visual: If 0-1-2 are connected, after unions, all point to a single root.
     *
     * Explanation:
     * - parents[i]: If -1, i is a root; otherwise, points to its parent in the
     *   tree.
     * - sizes[i]: Number of nodes in the component rooted at i.
     * - find(x): Recursively finds the root of x, compressing the path for speed.
     * - union(x, y): Finds roots of x and y, merges the smaller tree under the
     *   larger.
     * - This structure allows us to merge and query components in nearly constant
     *   time.
     * - Example: For unions (0,1),(1,2),(3,4), after unions, 0,1,2 share a root,
     *   3,4 share a root, so there are 2 components.
     *
     * Time Complexity: O(α(n)) per operation (inverse Ackermann, nearly constant)
     * Space Complexity: O(n)
     *
     * @param n Number of nodes
     */
    public static class DisjointSetUnion {
        int[] parents;
        int[] sizes;
        public DisjointSetUnion(int n) {
            this.parents = new int[n];
            this.sizes = new int[n];
            Arrays.fill(parents, -1); // Each node is initially its own root
            Arrays.fill(sizes, 1);    // Each component starts with size 1
        }
        /**
         * Finds the root of the node with path compression.
         * If node is not a root, recursively finds and sets its parent to the root.
         * This flattens the tree for future queries.
         */
        public int find(int node) {
            // If node is a root (parent is -1), return node itself
            if (parents[node] == -1)
                return node;
            // Path compression: recursively find root and set parent directly to root
            // This flattens the tree, making future finds faster
            parents[node] = find(parents[node]);
            return parents[node]; // Return the root of the component
        }
        /**
         * Unites the sets containing node1 and node2 using union by size.
         * The smaller tree is merged under the larger to keep the tree shallow.
         */
        public void union(int node1, int node2) {
            // Find the root of node1 (with path compression)
            int root1 = find(node1);
            // Find the root of node2 (with path compression)
            int root2 = find(node2);
            // If both nodes have the same root, they are already in the same set
            if (root1 == root2)
                return;
            // Union by size: attach the smaller tree to the root of the larger tree
            if (sizes[root1] < sizes[root2]) {
                // Make root2 the parent of root1
                parents[root1] = root2;
                // Update the size of root2's component
                sizes[root2] += sizes[root1];
            } else {
                // Make root1 the parent of root2
                parents[root2] = root1;
                // Update the size of root1's component
                sizes[root1] += sizes[root2];
            }
        }
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        DisjointSetUnion dsu1 = new DisjointSetUnion(5);
        dsu1.union(0, 1);
        dsu1.union(1, 2);
        dsu1.union(3, 4);
        System.out.println("find(0): " + dsu1.find(0));
        System.out.println("find(2): " + dsu1.find(2));
        System.out.println("find(3): " + dsu1.find(3));
        System.out.println("find(4): " + dsu1.find(4));
        int components1 = 0;
        for (int i = 0; i < 5; i++) if (dsu1.find(i) == i) components1++;
        System.out.println("Expected components: 2, Output: " + components1);

        // Edge Cases
        System.out.println("\nEdge Cases:");
        DisjointSetUnion dsu2 = new DisjointSetUnion(1);
        System.out.println("find(0): " + dsu2.find(0));
        int components2 = 0;
        for (int i = 0; i < 1; i++) if (dsu2.find(i) == i) components2++;
        System.out.println("Expected components: 1, Output: " + components2);

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int n3 = 10000;
        DisjointSetUnion dsu3 = new DisjointSetUnion(n3);
        for (int i = 0; i < n3 - 1; i++) dsu3.union(i, i + 1);
        int components3 = 0;
        for (int i = 0; i < n3; i++) if (dsu3.find(i) == i) components3++;
        System.out.println("Expected components: 1, Output: " + components3);

        // Special Cases
        System.out.println("\nSpecial Cases:");
        DisjointSetUnion dsu4 = new DisjointSetUnion(6);
        dsu4.union(0, 1);
        dsu4.union(2, 3);
        dsu4.union(4, 5);
        dsu4.union(1, 2);
        System.out.println("find(0): " + dsu4.find(0));
        System.out.println("find(3): " + dsu4.find(3));
        System.out.println("find(4): " + dsu4.find(4));
        int components4 = 0;
        for (int i = 0; i < 6; i++) if (dsu4.find(i) == i) components4++;
        System.out.println("Expected components: 2, Output: " + components4);
    }
}
