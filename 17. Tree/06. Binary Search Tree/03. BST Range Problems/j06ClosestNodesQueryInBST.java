/**
 * LeetCode 2476. Closest Nodes Queries in a Binary Search Tree
 * 
 * Problem Statement:
 *     Given a binary search tree and an array of queries, for each query find
 *     the floor and ceiling values in the BST. Return a list of lists where each
 *     inner list contains [floor, ceil] for each query.
 * 
 * Input:
 *     - root: Root node of BST
 *     - queries: List of integers to find floor and ceil values for
 * 
 * Output:
 *     - List<List<Integer>> where each inner list contains [floor, ceil]
 *     - If floor doesn't exist, use -1
 *     - If ceil doesn't exist, use -1
 * 
 * Example:
 *     Input: 
 *              6
 *            /   \
 *           2     13
 *          / \    /
 *         1   4  9
 *     queries = [2,5,16]
 *     
 *     Output: [[2,2], [4,6], [13,-1]]
 *     
 *     Explanation:
 *     - For 2: floor=2, ceil=2 (exact match)
 *     - For 5: floor=4, ceil=6 (closest values)
 *     - For 16: floor=13, ceil=-1 (no larger value exists)
 */

import java.util.ArrayList;
import java.util.List;

public class j06ClosestNodesQueryInBST {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int item) {
            val = item;
            left = right = null;
        }
    }

    /**
     * Approach 1: Direct BST Search
     * 
     * Explanation:
     * - For each query value, we traverse the BST twice:
     *   1. First traversal finds floor:
     *      - Keep track of last smaller value seen
     *      - If current node > target, go left
     *      - If current node < target, update floor and go right
     *      - If exact match found, that's the floor
     *   
     *   2. Second traversal finds ceil:
     *      - Keep track of last larger value seen
     *      - If current node < target, go right
     *      - If current node > target, update ceil and go left
     *      - If exact match found, that's the ceil
     * 
     * Time Complexity: O(Q * H) 
     * - For Q queries, we do 2 traversals each
     * - Each traversal takes O(H) where H is height
     * 
     * Space Complexity: O(1) 
     * - Only storing constant extra space
     * - Output space not counted as per convention
     * 
     * @param root     Root node of BST
     * @param queries  List of query values
     * @return         List of [floor, ceil] pairs for each query
     */
    public static List<List<Integer>> closestNodesFloorCeil(TreeNode root, List<Integer> queries) {
        List<List<Integer>> out = new ArrayList<>();
        for (int q : queries) {
            ArrayList<Integer> res = new ArrayList<>();
            res.add(floor(root, q));
            res.add(ceil(root, q));
            out.add(res);
        }
        return out;
    }

    /**
     * Helper Method: Find Floor Value
     * 
     * Intuition:
     * - Track largest value seen that's smaller than or equal to target
     * 
     * Time Complexity: O(H)
     * Space Complexity: O(1)
     */
    private static int floor(TreeNode root, int k) {
        if (root == null)
            return -1;
        int floor = Integer.MIN_VALUE;
        while (root != null) {
            if (root.val == k) {
                floor = k;
                break;
            } else if (root.val > k) {
                root = root.left;
            } else if (root.val < k) {
                floor = root.val;
                root = root.right;
            }
        }
        return (floor != Integer.MIN_VALUE) ? floor : -1;
    }

    /**
     * Helper Method: Find Ceil Value
     * 
     * Intuition:
     * - Track smallest value seen that's larger than or equal to target
     * 
     * Time Complexity: O(H)
     * Space Complexity: O(1)
     */
    private static int ceil(TreeNode root, int k) {
        if (root == null)
            return -1;
        int ceil = Integer.MAX_VALUE;
        while (root != null) {
            if (root.val == k) {
                ceil = k;
                break;
            } else if (root.val > k) {
                ceil = root.val;
                root = root.left;
            } else if (root.val < k) {
                root = root.right;
            }
        }
        return (ceil != Integer.MAX_VALUE) ? ceil : -1;
    }

    /**
     * Approach 2: Binary Search on Inorder Array
     * 
     * Explanation:
     * - Convert BST to sorted array using inorder traversal:
     *   1. Traverse left subtree
     *   2. Add current node value
     *   3. Traverse right subtree
     * 
     * - For each query:
     *   1. Use binary search to find position where target should be
     *   2. When mid element < target:
     *      - It's a candidate for floor
     *      - Search in right half
     *   3. When mid element > target:
     *      - It's a candidate for ceil
     *      - Search in left half
     *   4. If exact match found, both floor and ceil are same
     * 
     * Time Complexity: O(N + Q * log N)
     * - O(N) for inorder traversal
     * - O(log N) binary search for each of Q queries
     * 
     * Space Complexity: O(N)
     * - O(N) to store inorder array
     * - O(H) recursion stack for inorder traversal
     */
    public static List<List<Integer>> closestNodesEfficient(TreeNode root, List<Integer> queries) {
        List<List<Integer>> out = new ArrayList<>();
        ArrayList<Integer> arr = new ArrayList<>();
        getInorderOfBST(root, arr);
        for (int q : queries) {
            int[] res = floorAndCeil(arr, q);
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(res[0]);
            temp.add(res[1]);
            out.add(temp);
        }
        return out;
    }

    /**
     * Helper Method: Inorder Traversal of BST
     * 
     * Explanation:
     * - Performs inorder traversal to get sorted array:
     *   1. Base case: return if node is null
     *   2. Recursively process left subtree first
     *   3. Add current node's value to array
     *   4. Recursively process right subtree
     * - BST property ensures values are added in sorted order
     * 
     * Time Complexity: O(N) where N is number of nodes
     * - Visits each node exactly once
     * 
     * Space Complexity: O(H) for recursion stack
     * - H is height of tree
     * - In worst case (skewed tree), O(N)
     * 
     * @param root Root node of BST subtree being processed
     * @param arr  ArrayList to store inorder traversal result
     */
    public static void getInorderOfBST(TreeNode root, ArrayList<Integer> arr) {
        if (root == null)
            return;
        getInorderOfBST(root.left, arr);  // Process left subtree
        arr.add(root.val);                 // Add current node
        getInorderOfBST(root.right, arr);  // Process right subtree
    }

    /**
     * Helper Method: Binary Search for Floor and Ceil
     * 
     * Explanation:
     * - Binary search on sorted array to find floor and ceil:
     *   1. Initialize floor and ceil as -1
     *   2. For each mid element:
     *      - If equal to target: both floor and ceil are target
     *      - If less than target: update floor, search right half
     *      - If more than target: update ceil, search left half
     *   3. Return [floor, ceil] pair
     * 
     * Algorithm Steps:
     * 1. Initialize start = 0, end = array size - 1
     * 2. While start <= end:
     *    - Calculate mid = start + (end - start) / 2
     *    - If mid element == target:
     *      * Both floor and ceil are target
     *      * Return immediately
     *    - If mid element < target:
     *      * Update floor to mid element
     *      * Search in right half
     *    - If mid element > target:
     *      * Update ceil to mid element
     *      * Search in left half
     * 
     * Time Complexity: O(log N) where N is array size
     * - Standard binary search complexity
     * 
     * Space Complexity: O(1)
     * - Only uses constant extra space
     * 
     * @param arr Sorted array from inorder traversal
     * @param k   Target value to find floor and ceil for
     * @return    Array where [0] is floor and [1] is ceil
     */
    public static int[] floorAndCeil(ArrayList<Integer> arr, int k) {
        int s = 0;
        int e = arr.size() - 1;
        int[] out = new int[2];
        out[0] = -1;  // Initialize floor
        out[1] = -1;  // Initialize ceil
        
        while (s <= e) {
            int mid = s + (e - s) / 2;  // Calculate mid point
            int val = arr.get(mid);
            
            if (val == k) {  // Exact match found
                out[0] = k;
                out[1] = k;
                return out;
            } else if (val < k) {  // Current value is floor candidate
                out[0] = val;
                s = mid + 1;
            } else {  // Current value is ceil candidate
                out[1] = val;
                e = mid - 1;
            }
        }
        return out;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        TreeNode root1 = new TreeNode(6);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(13);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(4);
        root1.right.left = new TreeNode(9);
        
        List<Integer> queries1 = new ArrayList<>();
        queries1.add(2);
        queries1.add(5);
        queries1.add(16);
        
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: [2,5,16]");
        System.out.println("Expected: [[2,2],[4,6],[13,-1]]");
        System.out.println("Output: " + closestNodesFloorCeil(root1, queries1));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: null tree");
        System.out.println("Output: " + closestNodesFloorCeil(null, queries1));

        // Test Case 3: Boundary cases
        List<Integer> queries2 = new ArrayList<>();
        queries2.add(Integer.MIN_VALUE);
        queries2.add(Integer.MAX_VALUE);
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: MIN_VALUE, MAX_VALUE");
        System.out.println("Output: " + closestNodesFloorCeil(root1, queries2));
    }
}
