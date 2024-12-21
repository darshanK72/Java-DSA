/**
 * Problem Statement:
 * 
 *     You are given an array answers of length n, where each element answers[i] represents the answer given by the ith rabbit
 *     in the forest. The answer answers[i] means that the ith rabbit saw answers[i] other rabbits with the same color as itself.
 *     We need to calculate the minimum number of rabbits that could be in the forest consistent with the given answers.
 *     A rabbit's answer tells us how many other rabbits with the same color exist, and we need to account for that when calculating
 *     the total number of rabbits.
 * 
 * Input:
 *     - An integer n (1 <= n <= 1000), the number of rabbits.
 *     - An array of n integers answers[] (0 <= answers[i] <= 1000) where each element represents the number of rabbits 
 *       a particular rabbit sees with the same color.
 * 
 * Output:
 *     - An integer representing the minimum number of rabbits in the forest consistent with the given answers.
 * 
 * Example:
 *     Input:
 *     5
 *     [1, 1, 2, 2, 2]
 *     
 *     Output:
 *     6
 * 
 *     Explanation:
 *     The first two rabbits answer "1", which means there are 2 rabbits of the same color, so 2 rabbits in total.
 *     The next three rabbits answer "2", meaning there are 3 rabbits with the same color, so 3 more rabbits in total.
 *     Hence, the minimum number of rabbits is 6.
 */

import java.util.HashMap;
import java.util.Scanner;

public class j04RabbitsInForest {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        // Output the result from multiple approaches
        System.out.println(numRabbits1(arr)); // Naive approach
        System.out.println(numRabbitsEfficient(arr)); // Optimized approach
        in.close();
    }

    /**
     * Approach 1: Naive Approach
     * 
     * Intuition:
     * - For each rabbit's answer, we track the count of how many rabbits have the same answer. 
     *   If a rabbit says "x", we need to ensure that there are exactly x+1 rabbits of the same color.
     * - We keep track of the groups of rabbits, ensuring the group size matches the answer given.
     * 
     * Time Complexity:
     * - O(N), where N is the size of the input array, as we loop through the entire array to calculate the answer.
     * 
     * Space Complexity:
     * - O(N), as we store the counts of the answers in a HashMap.
     * 
     * @param answers The array of answers from rabbits.
     * @return The total number of rabbits.
     */
    public static int numRabbits1(int[] answers) {
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == 0) {
                ans++;
            } else {
                if (map.containsKey(answers[i] + 1)) {
                    if (map.get(answers[i] + 1) <= answers[i]) {
                        map.put(answers[i] + 1, map.get(answers[i] + 1) + 1);
                    } else {
                        ans += answers[i] + 1;
                        map.put(answers[i] + 1, 1);
                    }
                } else {
                    map.put(answers[i] + 1, 1);
                }
            }
        }
        for (int key : map.keySet()) {
            ans += key;
        }
        return ans;
    }

    /**
     * Approach 2: Optimized Approach
     * 
     * Intuition:
     * - Instead of creating groups based on answers, we simply count how many times each answer appears.
     * - For each unique answer, we calculate how many full groups can be formed, and if there are leftover rabbits, 
     *   we account for them as a new group.
     * 
     * Time Complexity:
     * - O(N), where N is the size of the input array, as we loop through the entire array to calculate the answer.
     * 
     * Space Complexity:
     * - O(N), as we use a HashMap to store the count of occurrences for each answer.
     * 
     * @param answers The array of answers from rabbits.
     * @return The total number of rabbits.
     */
    public static int numRabbitsEfficient(int[] answers) {
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int answer : answers) {
            map.put(answer, map.getOrDefault(answer, 0) + 1);
        }

        for (int key : map.keySet()) {
            int size = key + 1;
            int rab = map.get(key);
            int grp = (int) Math.ceil((1.0 * rab) / (1.0 * size));
            ans += size * grp;
        }
        return ans;
    }
}
