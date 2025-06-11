/**
 * LeetCode 860 - Lemonade Change
 * 
 * Problem Statement:
 *     At a lemonade stand, each lemonade costs $5. Customers are standing in a 
 *     queue to buy from you and order one at a time (in the order specified by 
 *     the bills array). Each customer will only buy one lemonade and pay with 
 *     either a $5, $10, or $20 bill. You must provide the correct change to 
 *     each customer so that the net transaction is that the customer pays $5.
 *     Return true if and only if you can provide every customer with the 
 *     correct change.
 * 
 * Input:
 *     - bills[] (int[]): Array of bills where bills[i] is the bill the ith 
 *       customer pays with (5, 10, or 20)
 * 
 * Output:
 *     - boolean: true if we can provide change to all customers, false otherwise
 * 
 * Example:
 *     Input: bills = [5,5,5,10,20]
 *     Output: true
 * 
 *     Explanation:
 *     - First 3 customers: Pay with $5, no change needed
 *     - 4th customer: Pay with $10, give $5 change
 *     - 5th customer: Pay with $20, give $10 + $5 change
 */

public class j04LemonadeChange {
    
    /**
     * Approach: Greedy
     * 
     * Intuition:
     * - We need to give change using the smallest number of bills possible
     * - For $10 change, we should use one $5 bill
     * - For $15 change, we should prefer using one $10 and one $5 over three $5
     *   bills to preserve $5 bills for future transactions
     * - Keep track of available $5 and $10 bills
     * 
     * Explanation:
     * 1. Initialize counters for $5 and $10 bills
     * 2. For each customer:
     *    - If they pay with $5: increment $5 counter
     *    - If they pay with $10: 
     *      * Need one $5 bill for change
     *      * If no $5 available, return false
     *    - If they pay with $20:
     *      * Prefer using one $10 and one $5
     *      * If not possible, use three $5
     *      * If neither possible, return false
     * 
     * Time Complexity: O(N) where N is the number of customers
     *                  - Single pass through the bills array
     * Space Complexity: O(1) as we only use two counters
     * 
     * @param bills    Array of bills where bills[i] is the bill the ith 
     *                 customer pays with
     * @return        true if we can provide change to all customers
     */
    public static boolean lemonadeChange(int[] bills) {
        int fives = 0;  // Count of $5 bills
        int tens = 0;   // Count of $10 bills
        
        // Process each customer's payment
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) {
                // Customer pays with $5, no change needed
                fives++;
            } else if (bills[i] == 10) {
                // Customer pays with $10, need one $5 change
                if (fives > 0) {
                    fives--;
                    tens++;
                } else {
                    // No $5 bills available for change
                    return false;
                }
            } else {
                // Customer pays with $20, need $15 change
                if (fives > 0 && tens > 0) {
                    // Prefer using one $10 and one $5
                    fives--;
                    tens--;
                } else if (fives >= 3) {
                    // Use three $5 bills if no $10 available
                    fives -= 3;
                } else {
                    // Not enough bills for change
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] bills1 = {5, 5, 5, 10, 20};
        System.out.println("Input: bills=" + java.util.Arrays.toString(bills1));
        System.out.println("Output: " + lemonadeChange(bills1));

        // Test Case 2: Cannot provide change
        System.out.println("\nCannot Provide Change:");
        int[] bills2 = {5, 5, 10, 10, 20};
        System.out.println("Input: bills=" + java.util.Arrays.toString(bills2));
        System.out.println("Output: " + lemonadeChange(bills2));

        // Test Case 3: All $5 bills
        System.out.println("\nAll $5 Bills:");
        int[] bills3 = {5, 5, 5, 5, 5};
        System.out.println("Input: bills=" + java.util.Arrays.toString(bills3));
        System.out.println("Output: " + lemonadeChange(bills3));

        // Test Case 4: Edge case - empty array
        System.out.println("\nEdge Case - Empty Array:");
        int[] bills4 = {};
        System.out.println("Input: bills=" + java.util.Arrays.toString(bills4));
        System.out.println("Output: " + lemonadeChange(bills4));

        // Test Case 5: Complex case
        System.out.println("\nComplex Case:");
        int[] bills5 = {5, 5, 10, 20, 5, 5, 5, 5, 5, 5, 20, 20};
        System.out.println("Input: bills=" + java.util.Arrays.toString(bills5));
        System.out.println("Output: " + lemonadeChange(bills5));
    }
}
