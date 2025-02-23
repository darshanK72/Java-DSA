/**
 * Problem Statement:
 * 
 *     Given a JSON string `s`, return a pretty-printed version of it with the 
 *     proper indentations. The input is a valid JSON string consisting of curly 
 *     braces `{}`, square brackets `[]`, and key-value pairs. Your task is to 
 *     return the JSON string in a more readable format, where:
 * 
 *     - Each nested structure is indented with a tab character.
 *     - The JSON should preserve its structure (keys, values, arrays, etc.).
 * 
 * Input:
 *     - A string `s` representing the JSON data.
 * 
 * Output:
 *     - An ArrayList of strings representing the formatted JSON, where each 
 *       string is a line in the formatted JSON.
 * 
 * Example:
 * 
 *     Input:
 *     "{\"name\":\"John\", \"age\":30, \"city\":\"New York\"}"
 *     Output:
 *     [
 *         "{\"name\":\"John\",",
 *         "\"age\":30,",
 *         "\"city\":\"New York\"}"
 *     ]
 * 
 *     Explanation:
 *     The given JSON string is a single-level object. The formatted version 
 *     has each key-value pair on a new line with proper indentation for readability.
 *     
 *     Input:
 *     "{\"person\":{\"name\":\"John\",\"age\":30}, \"location\":\"New York\"}"
 *     Output:
 *     [
 *         "{\"person\":{",
 *         "\t\"name\":\"John\",",
 *         "\t\"age\":30},",
 *         "\"location\":\"New York\"}"
 *     ]
 * 
 *     Explanation:
 *     This JSON contains nested objects, which are properly indented.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class j15PrettyJSON {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String s = in.nextLine(); // Input: JSON string
        System.out.print(prettyJSON(s)); // Format and print the JSON string
        in.close();
    }

    /**
     * Approach: 
     * The method `prettyJSON` formats the JSON string by adding appropriate 
     * indentation. It keeps track of the current level of nesting using the 
     * `tabs` variable. For each character in the string, we perform the following:
     * - When encountering `{` or `[`, we increase the indentation level.
     * - When encountering `}` or `]`, we decrease the indentation level.
     * - For other characters, we append them with the current level of indentation.
     * - We collect each line of the formatted string in the `output` list.
     * 
     * Intuition:
     * - JSON structures can be nested, and proper indentation improves readability.
     * - By maintaining a count of the current indentation level (`tabs`), we can ensure 
     *   that each nested level of the JSON has the appropriate amount of indentation.
     * 
     * Time Complexity:
     * - O(n), where n is the length of the JSON string, as we iterate over the string once.
     * 
     * Space Complexity:
     * - O(n), where n is the number of characters in the input string, as we store the formatted output.
     * 
     * @param s The JSON string to be formatted.
     * @return A list of strings representing the pretty-printed JSON.
     */
    public static ArrayList<String> prettyJSON(String s) {
        int tabs = 0; // To keep track of the current indentation level
        StringBuilder out = new StringBuilder();
        ArrayList<String> output = new ArrayList<>();

        // Iterate through the string
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // If we encounter an opening brace or bracket, increase indentation
            if (ch == '{' || ch == '[') {
                if (out.length() > 0) {
                    output.add(out.toString());
                    out = new StringBuilder();
                }
                addIndents(out, tabs); // Add the correct indentation
                out.append(ch); // Add the character
                output.add(out.toString()); // Add the current line to the output
                out = new StringBuilder(); // Reset the current line
                tabs++; // Increase indentation level
            }
            // If we encounter a closing brace or bracket, decrease indentation
            else if (ch == '}' || ch == ']') {
                if (out.length() > 0) {
                    output.add(out.toString());
                    out = new StringBuilder();
                }
                tabs--; // Decrease indentation level
                addIndents(out, tabs); // Add the correct indentation
                out.append(ch); // Add the character
            }
            // If we encounter a comma, just append it to the current line
            else if (ch == ',') {
                out.append(ch);
                output.add(out.toString());
                out = new StringBuilder(); // Reset the current line
            }
            // For other characters, add them with the current indentation
            else {
                if (out.length() == 0) {
                    addIndents(out, tabs); // Add indentation at the beginning of a new line
                }
                out.append(ch); // Add the character
            }
        }

        // Add any remaining content to the output
        if (out.length() > 0) {
            output.add(out.toString());
        }

        return output; // Return the formatted JSON
    }

    /**
     * Helper method to add the correct amount of indentation (tabs).
     * 
     * Intuition:
     * - Indentation helps make the JSON more readable by indicating the structure.
     * - Each nested level of JSON requires one more tab for proper formatting.
     * 
     * @param sb The StringBuilder object to append indentation to.
     * @param count The number of tabs to add.
     */
    private static void addIndents(StringBuilder sb, int count) {
        for (int i = 0; i < count; i++) {
            sb.append('\t'); // Append a tab character
        }
    }
}
