import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class j02LongestSubstrWithoutRepeatingChars {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        System.out.println(lengthOfLongestSubstring(s));
        System.out.println(lengthOfLongestSubstringEfficient1(s));
        System.out.println(lengthOfLongestSubstringEfficient2(s));
        System.out.println(lengthOfLongestSubstringEfficient3(s));
        in.close();
    }

    public static int lengthOfLongestSubstring(String s) {
        int maxL = 0;
        for (int i = 0; i < s.length(); i++) {
            HashSet<Character> set = new HashSet<>();
            for (int j = i; j < s.length(); j++) {
                if (set.contains(s.charAt(j)))
                    break;
                else {
                    maxL = Math.max(maxL, j - i + 1);
                    set.add(s.charAt(j));
                }
            }
        }
        return maxL;
    }

    public static int lengthOfLongestSubstringEfficient1(String s) {
        int maxL = 0;
        int j = 0;
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            while (set.contains(s.charAt(i))) {
                set.remove(s.charAt(j));
                j++;
            }
            set.add(s.charAt(i));
            maxL = Math.max(maxL, i - j + 1);
        }
        return maxL;
    }

    public static int lengthOfLongestSubstringEfficient2(String s) {
        int maxL = 0;
        int j = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            while (map.get(ch) > 1) {
                char ch1 = s.charAt(j);
                map.put(ch1, map.getOrDefault(ch1, 0) - 1);
                j++;
            }
            maxL = Math.max(maxL, i - j + 1);
        }
        return maxL;
    }

    public static int lengthOfLongestSubstringEfficient3(String s) {
        int maxL = 0;
        int j = 0;
        int[] hash = new int[256];
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            hash[ch]++;
            while (hash[ch] > 1) {
                char ch1 = s.charAt(j);
                hash[ch1]--;
                j++;
            }
            maxL = Math.max(maxL, i - j + 1);
        }
        return maxL;
    }
}
