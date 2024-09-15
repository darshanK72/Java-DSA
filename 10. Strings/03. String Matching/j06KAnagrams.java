import java.util.HashMap;
import java.util.Scanner;

public class j06KAnagrams {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        int k = in.nextInt();
        System.out.println(areKAnagrams(s1, s2, k));
        System.out.println(areKAnagramsHashMap(s1, s2, k));
        in.close();
    }

    public static boolean areKAnagrams(String s1, String s2, int k) {
        if (s1.length() != s2.length())
            return false;
        int hash1[] = new int[26];
        int hash2[] = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            hash1[s1.charAt(i) - 'a']++;
            hash2[s2.charAt(i) - 'a']++;
        }

        int changes = 0;
        for (int i = 0; i < 26; i++) {
            if (hash1[i] > hash2[i]) {
                changes += hash1[i] - hash2[i];
            }
        }
        return changes <= k;
    }

    public static boolean areKAnagramsHashMap(String s1, String s2, int k) {
        if (s1.length() != s2.length())
            return false;
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s1.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (char c : s2.toCharArray()) {
            if (map.getOrDefault(c, 0) > 0) {
                map.put(c, map.getOrDefault(c, 0) - 1);
            }
        }

        int count = 0;
        for (char key : map.keySet()) {
            count += map.get(key);
        }
        return count <= k;
    }
}