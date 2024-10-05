import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class j04LongestSubstrWithAtMostKDistinctChars {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int k = in.nextInt();
        System.out.println(getLengthofLongestSubstring(k, s));
        System.out.println(getLengthofLongestSubstringEfficient(k, s));
        in.close();
    }

    public static int getLengthofLongestSubstring(int k, String s) {
        int maxL = 0;
        for (int i = 0; i < s.length(); i++) {
            HashSet<Character> set = new HashSet<>();
            for (int j = i; j < s.length(); j++) {
                char ch = s.charAt(j);
                set.add(ch);
                if (set.size() > k)
                    break;
                else {
                    maxL = Math.max(maxL, j - i + 1);
                }
            }
        }
        return maxL;
    }

    public static int getLengthofLongestSubstringEfficient(int k, String s) {
        int maxL = 0;
        int j = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            while (map.size() > k) {
                char ch1 = s.charAt(j);
                map.put(ch1, map.get(ch1) - 1);
                if (map.get(ch1) == 0) {
                    map.remove(ch1);
                }
                j++;
            }
            maxL = Math.max(maxL, i - j + 1);
        }
        return maxL;
    }
}
