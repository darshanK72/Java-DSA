/**
 * Problem Statement:
 * 
 *     Given a binary number and its corresponding Gray code, convert between the two representations.
 * 
 *     1. Convert a binary number to its Gray code representation.
 *     2. Convert a Gray code to its binary representation.
 * 
 * Input:
 *     - A binary number as a string (e.g., "1010").
 *     - A Gray code as a string (e.g., "1111").
 * 
 * Output:
 *     - The corresponding Gray code for the binary input and vice versa.
 * 
 * Example:
 *     Input:
 *     "1010"
 *     "1111"
 * 
 *     Output:
 *     "1111"
 *     "1010"
 */

import java.util.Scanner;

public class j02BinaryGray {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String binary = in.next(); // Input binary string
        String gray = in.next(); // Input Gray code string
        System.out.println(binaryToGray(binary)); // Convert binary to Gray code
        System.out.println(grayToBinary(gray)); // Convert Gray code to binary
        in.close();
    }

    /**
     * Method: binaryToGray
     * 
     * Description: 
     * - Converts a binary number to its corresponding Gray code.
     * - The first bit is the same as the binary's first bit.
     * - For each subsequent bit, the Gray code bit is determined by XOR-ing the current 
     *   binary bit with the previous one.
     * 
     * Time Complexity: O(n), where n is the length of the binary string.
     * Space Complexity: O(n), as we store the Gray code result.
     * 
     * @param binary A string representing the binary number.
     * @return A string representing the corresponding Gray code.
     */
    public static String binaryToGray(String binary) {
        StringBuilder gray = new StringBuilder();

        // First bit is same as binary's first bit
        gray.append(binary.charAt(0));

        // Compute the remaining bits
        for (int i = 1; i < binary.length(); i++) {
            char prev = binary.charAt(i - 1);
            char curr = binary.charAt(i);
            gray.append(prev == curr ? '0' : '1'); // XOR between previous and current bit
        }

        return gray.toString();
    }

    /**
     * Method: grayToBinary
     * 
     * Description: 
     * - Converts a Gray code to its corresponding binary number.
     * - The first bit is the same as the Gray code's first bit.
     * - For each subsequent bit, the binary bit is determined by XOR-ing the Gray code bit with 
     *   the previously computed binary bit.
     * 
     * Time Complexity: O(n), where n is the length of the Gray code string.
     * Space Complexity: O(n), as we store the binary number result.
     * 
     * @param gray A string representing the Gray code.
     * @return A string representing the corresponding binary number.
     */
    public static String grayToBinary(String gray) {
        StringBuilder binary = new StringBuilder();

        // First bit is same as Gray code's first bit
        binary.append(gray.charAt(0));

        // Compute the remaining bits
        for (int i = 1; i < gray.length(); i++) {
            char prev = binary.charAt(i - 1);
            char curr = gray.charAt(i);
            binary.append(prev == curr ? '0' : '1'); // XOR between previous binary bit and current gray code bit
        }

        return binary.toString();
    }

    /**
     * Method: binaryToGray (Integer version)
     * 
     * Description: 
     * - Converts a binary number to its corresponding Gray code using bitwise operations.
     * - The result is obtained by XOR-ing the number with a right-shifted version of itself.
     * 
     * Time Complexity: O(1) (constant time for integer operations).
     * Space Complexity: O(1) (constant space for integer variables).
     * 
     * @param n The binary number (as an integer).
     * @return The corresponding Gray code (as an integer).
     */
    public static int binaryToGray(int n) {
        return n ^ (n >> 1);
    }

    /**
     * Method: grayToBinary (Integer version)
     * 
     * Description: 
     * - Converts a Gray code to its corresponding binary number using bitwise operations.
     * - The Gray code is iteratively shifted, and XOR-ed with the current result.
     * 
     * Time Complexity: O(log n), where n is the number of bits in the Gray code.
     * Space Complexity: O(1) (constant space for integer variables).
     * 
     * @param gray The Gray code (as an integer).
     * @return The corresponding binary number (as an integer).
     */
    public static int grayToBinary(int gray) {
        int binary = gray;
        while ((gray >>= 1) != 0) {
            binary ^= gray;
        }
        return binary;
    }
}
