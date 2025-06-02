/**
 * LeetCode 973: K Closest Points to Origin
 * 
 * Problem Statement:
 *     Given an array of points where points[i] = [xi, yi] represents a point on
 *     the X-Y plane and an integer k, return the k closest points to the origin
 *     (0, 0). The distance between two points on the X-Y plane is the Euclidean
 *     distance.
 * 
 * Input:
 *     - points[][]: Array of points where points[i] = [xi, yi]
 *     - k: Number of closest points to find (1 <= k <= points.length)
 * 
 * Output:
 *     - int[][]: K closest points to origin, sorted by distance
 * 
 * Example:
 *     Input: points = [[1,3],[-2,2]], k = 1
 *     Output: [[-2,2]]
 * 
 *     Explanation:
 *     The distance between (1,3) and the origin is sqrt(10).
 *     The distance between (-2,2) and the origin is sqrt(8).
 *     Since sqrt(8) < sqrt(10), (-2,2) is closer to the origin.
 */

import java.util.PriorityQueue;

public class j05KClosestPointsToOrigin {

    /**
     * Helper Class: Point
     * 
     * Represents a point in 2D space with x and y coordinates.
     * Implements Comparable to enable sorting by distance from origin.
     */
    static class Point implements Comparable<Point> {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Compares this point with another point based on their distances
         * from the origin (0,0).
         * 
         * @param b    Point to compare with
         * @return     -1 if this point is closer, 1 if other point is closer,
         *             0 if equidistant
         */
        @Override
        public int compareTo(Point b) {
            // Calculate Euclidean distance from origin for both points
            double dist1 = Math.sqrt((0 - this.x) * (0 - this.x) + (0 - this.y) * (0 - this.y));
            double dist2 = Math.sqrt((0 - b.x) * (0 - b.x) + (0 - b.y) * (0 - b.y));
            
            // Compare distances
            if (dist1 > dist2)
                return 1;
            if (dist1 < dist2)
                return -1;
            return 0;
        }
    }

    /**
     * Approach: Min Heap with Custom Comparator
     * 
     * Intuition:
     * - Use a min heap to maintain points sorted by distance from origin
     * - Custom Point class implements Comparable for distance-based comparison
     * - Extract k closest points from heap
     * 
     * Explanation:
     * 1. Create Point objects for each input point
     * 2. Add points to min heap (automatically sorted by distance)
     * 3. Extract k closest points from heap
     * 
     * Time Complexity: O(n log n) where n is number of points
     *                  - O(n) to create points
     *                  - O(n log n) for heap operations
     * Space Complexity: O(n) for storing points in heap
     * 
     * @param points    Array of points [x,y]
     * @param k         Number of closest points to find
     * @return          K closest points to origin
     */
    public static int[][] kClosest(int[][] points, int k) {
        // Initialize min heap with custom Point comparator
        PriorityQueue<Point> pq = new PriorityQueue<>();
        
        // Create Point objects and add to heap
        for (int[] point : points) {
            Point pt = new Point(point[0], point[1]);
            pq.add(pt);
        }

        // Extract k closest points
        int[][] out = new int[k][2];
        for (int i = 0; i < k; i++) {
            Point point = pq.remove();
            out[i][0] = point.x;
            out[i][1] = point.y;
        }
        return out;
    }

    /**
     * Approach: Min Heap with Lambda Comparator
     * 
     * Intuition:
     * - Use a min heap with lambda comparator to avoid creating Point objects
     * - Directly compare distances using array coordinates
     * - More memory efficient as it doesn't create additional objects
     * 
     * Explanation:
     * 1. Create min heap with custom lambda comparator for distance comparison
     * 2. Add points directly to heap without object creation
     * 3. Extract k closest points from heap
     * 
     * Time Complexity: O(n log n) where n is number of points
     *                  - O(n log n) for heap operations
     * Space Complexity: O(n) for storing points in heap
     * 
     * @param points    Array of points [x,y]
     * @param k         Number of closest points to find
     * @return          K closest points to origin
     */
    public static int[][] kClosestEfficient(int[][] points, int k) {
        // Initialize min heap with lambda comparator for distance comparison
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
            // Calculate Euclidean distance from origin for both points
            double d1 = Math.sqrt((0 - a[0]) * (0 - a[0]) + (0 - a[1]) * (0 - a[1]));
            double d2 = Math.sqrt((0 - b[0]) * (0 - b[0]) + (0 - b[1]) * (0 - b[1]));
            
            // Compare distances
            if(d1 > d2) return 1;
            if(d1 < d2) return -1;
            else return 0;
        });

        // Add points directly to heap
        for(int[] point : points){
            pq.add(point);
        }

        // Extract k closest points
        int[][] out = new int[k][2];
        for(int i = 0; i < k; i++){
            out[i] = pq.remove();
        }
        return out;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[][] points1 = {{1, 3}, {-2, 2}};
        System.out.println("Input: [[1,3], [-2,2]], k = 1");
        int[][] result1 = kClosest(points1, 1);
        System.out.println("Output: [" + result1[0][0] + "," + result1[0][1] + "]");

        // Test Case 2: Multiple points
        System.out.println("\nMultiple Points Test Case:");
        int[][] points2 = {{3, 3}, {5, -1}, {-2, 4}};
        System.out.println("Input: [[3,3], [5,-1], [-2,4]], k = 2");
        int[][] result2 = kClosest(points2, 2);
        System.out.println("Output: [" + result2[0][0] + "," + result2[0][1] + "], [" + 
                         result2[1][0] + "," + result2[1][1] + "]");

        // Test Case 3: Points on same distance
        System.out.println("\nEqual Distance Test Case:");
        int[][] points3 = {{1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
        System.out.println("Input: [[1,1], [-1,-1], [1,-1], [-1,1]], k = 2");
        int[][] result3 = kClosest(points3, 2);
        System.out.println("Output: [" + result3[0][0] + "," + result3[0][1] + "], [" + 
                         result3[1][0] + "," + result3[1][1] + "]");

        // Test Case 4: Edge case - k equals array length
        System.out.println("\nEdge Case - k equals array length:");
        int[][] points4 = {{1, 0}, {0, 1}};
        System.out.println("Input: [[1,0], [0,1]], k = 2");
        int[][] result4 = kClosest(points4, 2);
        System.out.println("Output: [" + result4[0][0] + "," + result4[0][1] + "], [" + 
                         result4[1][0] + "," + result4[1][1] + "]");
    }
}
