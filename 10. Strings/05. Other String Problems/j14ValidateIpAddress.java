/**
 * Problem Statement:
 * 
 *     Given a string `queryIP`, return "IPv4" if the IP is a valid IPv4 address, 
 *     "IPv6" if the IP is a valid IPv6 address, or "Neither" if the IP is not a 
 *     correct IP of any type.
 * 
 *     A valid IPv4 address is an IP in the form "x1.x2.x3.x4" where 0 <= xi <= 255 
 *     and xi cannot contain leading zeros. For example, "192.168.1.1" and "192.168.1.0" 
 *     are valid IPv4 addresses while "192.168.01.1", "192.168.1.00", and "192.168@1.1" 
 *     are invalid IPv4 addresses.
 * 
 *     A valid IPv6 address is an IP in the form "x1:x2:x3:x4:x5:x6:x7:x8" where:
 *     
 *     - 1 <= xi.length <= 4
 *     - xi is a hexadecimal string which may contain digits, lowercase English 
 *       letters ('a' to 'f'), and upper-case English letters ('A' to 'F').
 *     - Leading zeros are allowed in xi.
 * 
 *     For example, "2001:0db8:85a3:0000:0000:8a2e:0370:7334" and 
 *     "2001:db8:85a3:0:0:8A2E:0370:7334" are valid IPv6 addresses, while 
 *     "2001:0db8:85a3::8A2E:037j:7334" and "02001:0db8:85a3:0000:0000:8a2e:0370:7334" 
 *     are invalid IPv6 addresses.
 * 
 * Input:
 *     - A string `queryIP` representing the IP address.
 * 
 * Output:
 *     - Return "IPv4" if the IP is a valid IPv4 address, 
 *       "IPv6" if it is a valid IPv6 address, or "Neither" otherwise.
 * 
 * Example:
 * 
 *     Input:
 *     "192.168.1.1"
 *     Output:
 *     "IPv4"
 *     
 *     Explanation:
 *     The given IP address is a valid IPv4 address as it follows the form 
 *     "x1.x2.x3.x4" where 0 <= xi <= 255 and no part has leading zeros.
 *     
 *     Input:
 *     "2001:0db8:85a3:0000:0000:8a2e:0370:7334"
 *     Output:
 *     "IPv6"
 *     
 *     Explanation:
 *     The given IP address is a valid IPv6 address with the format "x1:x2:x3:x4:x5:x6:x7:x8".
 *     All parts are hexadecimal numbers with lengths between 1 and 4, so it's a valid IPv6.
 */

import java.util.Scanner;

public class j14ValidateIpAddress {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String s = in.nextLine(); // Input: IP address
        System.out.println(validIPAddress(s)); // Validate IP address and print the result
        in.close();
    }

    /**
     * Approach: 
     * The method `validIPAddress` checks if the given IP address is either IPv4, IPv6, or Neither. 
     * It does this by first checking if the address contains a '.' (IPv4) or ':' (IPv6). 
     * Based on that, it calls the respective helper methods (`isIPv4` and `isIPv6`) to validate the address.
     * 
     * Intuition:
     * - If the string contains a dot (.), we check if it's a valid IPv4 address.
     * - If the string contains a colon (:), we check if it's a valid IPv6 address.
     * - If neither condition is met, return "Neither".
     * 
     * Time Complexity:
     * - O(n), where n is the length of the IP address string.
     * 
     * Space Complexity:
     * - O(1), as we are using a constant amount of extra space for validation.
     * 
     * @param queryIp The IP address string.
     * @return The validation result ("IPv4", "IPv6", or "Neither").
     */
    public static String validIPAddress(String queryIp) {
        // If the string contains a '.', it's an IPv4 address
        if (queryIp.contains(".")) {
            return isIPv4(queryIp) ? "IPv4" : "Neither";
        }
        // If the string contains a ':', it's an IPv6 address
        else if (queryIp.contains(":")) {
            return isIPv6(queryIp) ? "IPv6" : "Neither";
        }
        // If the string doesn't contain any of these, it's neither
        return "Neither";
    }

    /**
     * Approach 1: Check if the IP is IPv4.
     * 
     * Intuition:
     * - An IPv4 address consists of four parts separated by periods.
     * - Each part must be a number between 0 and 255 and cannot have leading zeros.
     * - We split the string by '.' and validate each part.
     * 
     * Time Complexity:
     * - O(n), where n is the number of characters in the IPv4 address string.
     * 
     * Space Complexity:
     * - O(1), as we only use constant space for validation.
     * 
     * @param s The string representing the IP address.
     * @return True if the IP address is valid IPv4.
     */
    public static boolean isIPv4(String s) {
        // Check if the IP starts or ends with a '.' (invalid)
        if (s.startsWith(".") || s.endsWith(".")) {
            return false;
        }

        // Split the address by '.'
        String[] nums = s.split("\\.");
        // There should be exactly 4 parts
        if (nums.length != 4)
            return false;

        for (String n : nums) {
            // Each part must not be empty, not exceed 3 digits, and must not start with '0'
            // unless it's "0"
            if (n.length() == 0 || n.length() > 3 || (n.length() > 1 && n.charAt(0) == '0'))
                return false;
            for (char c : n.toCharArray()) {
                // Each character must be a digit
                if (!Character.isDigit(c))
                    return false;
            }

            // Parse the number and check if it's within the valid range
            int val = Integer.parseInt(n);
            if (val < 0 || val > 255)
                return false;
        }
        return true;
    }

    /**
     * Approach 2: Check if the IP is IPv6.
     * 
     * Intuition:
     * - An IPv6 address consists of eight parts separated by colons.
     * - Each part must be a hexadecimal string of length between 1 and 4 characters.
     * - We split the string by ':' and validate each part.
     * 
     * Time Complexity:
     * - O(n), where n is the number of characters in the IPv6 address string.
     * 
     * Space Complexity:
     * - O(1), as we only use constant space for validation.
     * 
     * @param s The string representing the IP address.
     * @return True if the IP address is valid IPv6.
     */
    public static boolean isIPv6(String s) {
        // Check if the IP starts or ends with a ':' (invalid)
        if (s.startsWith(":") || s.endsWith(":")) {
            return false;
        }

        // Split the address by ':'
        String[] nums = s.split(":");
        // There should be exactly 8 parts
        if (nums.length != 8)
            return false;

        for (String n : nums) {
            // Each part must not be empty and must be between 1 and 4 characters
            if (n.length() == 0 || n.length() > 4)
                return false;
            for (char c : n.toCharArray()) {
                // Each character must be a valid hexadecimal digit (0-9, a-f, A-F)
                if (!Character.isDigit(c) && !isHexLetter(c))
                    return false;
            }
        }
        return true;
    }

    /**
     * Helper method to check if a character is a valid hexadecimal letter.
     * 
     * Intuition:
     * - A valid hexadecimal character is a digit from 0-9 or a letter from a-f or A-F.
     * 
     * @param c The character to check.
     * @return True if the character is a valid hexadecimal letter.
     */
    public static boolean isHexLetter(char c) {
        return (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
    }
}
