/**
 * Problem Statement:
 * 
 *     Given a number n, generate the n-bit Gray code sequence. In a Gray code sequence, two successive 
 *     numbers differ in only one bit.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 16), representing the number of bits.
 * 
 * Output:
 *     - A list of Gray codes in either binary string format or integer format.
 * 
 * Example:
 *     Input:
 *     2
 *     Output:
 *     [0, 1, 3, 2]
 * 
 *     Explanation:
 *     The Gray code sequence for 2 bits is [0, 1, 3, 2], which corresponds to the binary representations
 *     ["00", "01", "11", "10"].
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class j01GenerateGrayCode {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(generateGrayCode1(n));
        System.out.println(generateGrayCode2(n));
        in.close();
    }

    /**
    * Approach 1: Iterative Method
    * 
    * Intuition:
    * - Start with an empty string and iteratively build the Gray code sequence.
    * - For each bit position, prepend '0' to the existing sequence and '1' to the reversed sequence.
    * - Combine both sequences to form the new Gray code sequence.
    * 
    * Time Complexity:
    * - O(2^n * n), where n is the number of bits. We generate 2^n Gray codes, each of length n.
    * 
    * Space Complexity:
    * - O(2^n), for storing the sequence of Gray codes.
    * 
    * @param n The number of bits for the Gray code sequence.
    * @return A list of binary strings representing the Gray code sequence.
    */
    public static List<String> generateGrayCodeIterative(int n) {
        List<String> result = new ArrayList<String>();
        result.add("");
        for (int i = 0; i < n; i++) {
            List<String> tempList = new ArrayList<String>();
            for (int j = result.size() - 1; j >= 0; j--) {
                tempList.add(result.get(j));
            }

            for (int j = 0; j < result.size(); j++) {
                result.set(j, "0" + result.get(j));
            }

            for (int j = 0; j < result.size(); j++) {
                tempList.set(j, "1" + tempList.get(j));
            }
            result.addAll(tempList);
        }
        return result;
    }

    /**
     * Approach 2: Bitwise Method
     * 
     * Intuition:
     * - Use bitwise operations to generate the Gray code sequence.
     * - The nth Gray code is calculated as n ^ (n >> 1).
     * - Convert each Gray code to its binary string representation.
     * 
     * Time Complexity:
     * - O(2^n * n), where n is the number of bits. We generate 2^n Gray codes, each of length n.
     * 
     * Space Complexity:
     * - O(2^n), for storing the sequence of Gray codes.
     * 
     * @param n The number of bits for the Gray code sequence.
     * @return A list of binary strings representing the Gray code sequence.
     */
    public static List<String> generateGrayCodeBitwise(int n) {
        List<String> result = new ArrayList<>();
        int numOfGrayCodes = 1 << n; // 2^n

        for (int i = 0; i < numOfGrayCodes; i++) {
            int grayCode = i ^ (i >> 1);
            result.add(toBinaryString(grayCode, n));
        }

        return result;
    }

    /**
     * Helper Method: Convert an integer to a binary string with leading zeros
     * 
     * Intuition:
     * - Convert the integer to its binary string representation.
     * - Add leading zeros to match the desired length.
     * 
     * Time Complexity:
     * - O(n), where n is the length of the binary string.
     * 
     * Space Complexity:
     * - O(n), for storing the binary string.
     * 
     * @param number The integer to convert.
     * @param length The desired length of the binary string.
     * @return The binary string representation of the integer with leading zeros.
     */
    private static String toBinaryString(int number, int length) {
        String binaryString = Integer.toBinaryString(number);
        int paddingLength = length - binaryString.length();
        StringBuilder padding = new StringBuilder();
        for (int i = 0; i < paddingLength; i++) {
            padding.append("0");
        }
        return padding + binaryString;
    }

    /**
     * Approach 3: Recursion with Binary Strings
     * 
     * Intuition:
     * - The Gray code sequence can be constructed recursively. For a given n, the sequence for n bits 
     *   can be built from the sequence for n-1 bits.
     * - Prepend "0" to the first half of the sequence and "1" to the second half (in reverse order).
     * 
     * Time Complexity:
     * - O(2^n), where n is the number of bits. This is because there are 2^n Gray codes to generate.
     * 
     * Space Complexity:
     * - O(2^n), for storing the sequence of Gray codes.
     * 
     * @param n The number of bits for the Gray code sequence.
     * @return A list of binary strings representing the Gray code sequence.
     */
    public static ArrayList<String> generateGrayCode1(int n) {
        if (n == 1)
            return new ArrayList<String>(Arrays.asList("0", "1"));

        ArrayList<String> temp = generateGrayCode1(n - 1);
        ArrayList<String> out = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            out.add("0" + temp.get(i));
        }

        for (int i = temp.size() - 1; i >= 0; i--) {
            out.add("1" + temp.get(i));
        }
        return out;
    }

    /**
     * Approach 4: Recursion with Integers
     * 
     * Intuition:
     * - The Gray code sequence for integers can be generated using bitwise operations. The nth Gray code 
     *   is calculated as n ^ (n >> 1).
     * - For each recursive step, the sequence for n-1 is generated and then adjusted using bitwise 
     *   operations.
     * 
     * Time Complexity:
     * - O(2^n), where n is the number of bits. The time to generate each Gray code is constant.
     * 
     * Space Complexity:
     * - O(2^n), for storing the sequence of Gray codes.
     * 
     * @param n The number of bits for the Gray code sequence.
     * @return A list of integers representing the Gray code sequence.
     */
    public static ArrayList<Integer> generateGrayCode2(int n) {
        if (n == 1) {
            return new ArrayList<>(Arrays.asList(0, 1));
        } else {

            ArrayList<Integer> out = generateGrayCode2(n - 1);
            for (int i = out.size() - 1; i >= 0; i--) {
                out.add(out.get(i) | (1 << (n - 1)));
            }
            return out;
        }
    }
}
