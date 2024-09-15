import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class j07FindAllAnagrams {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        System.out.println(findAllAnagrams(s1, s2));
        System.out.println(findAllAnagramsHashMap(s1, s2));
        in.close();
    }

    // Bruit Force
    // O(s1.length * s2.length)
    public static ArrayList<String> findAllAnagrams(String s, String p) {
        ArrayList<String> out = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (i + p.length() <= s.length()) {
                String str = s.substring(i, i + p.length());
                if (isAnagram(str, p)) {
                    out.add(str);
                }
            }
        }
        return out;
    }

    // O(s1.length + s2.length)
    public static boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;
        int[] hash1 = new int[26];
        int[] hash2 = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            hash1[s1.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s2.length(); i++) {
            hash2[s2.charAt(i) - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (hash1[i] != hash2[i])
                return false;
        }
        return true;
    }

    public static ArrayList<String> findAllAnagramsHashMap(String s, String p) {
        HashMap<Character, Integer> smap = new HashMap<>();
        HashMap<Character, Integer> pmap = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            pmap.put(p.charAt(i), pmap.getOrDefault(p.charAt(i), 0) + 1);
        }
        for (int i = 0; i < p.length(); i++) {
            smap.put(s.charAt(i), smap.getOrDefault(s.charAt(i), 0) + 1);
        }
        ArrayList<String> out = new ArrayList<>();
        int i = p.length();
        while (i < s.length()) {
            if (compare(pmap, smap)) {
                out.add(s.substring(i - p.length(), i));
            }
            char toAc = s.charAt(i);
            smap.put(toAc, smap.getOrDefault(toAc, 0) + 1);
            char toRe = s.charAt(i - p.length());
            smap.put(toRe, smap.get(toRe) - 1);
            if (smap.get(toRe) == 0) {
                smap.remove(toRe);
            }
            i++;
        }
        if (compare(pmap, smap)) {
            out.add(s.substring(i - p.length(), i));
        }
        return out;
    }

    public static boolean compare(HashMap<Character, Integer> map1, HashMap<Character, Integer> map2) {
        if (map1.size() != map2.size())
            return false;
        for (Character key : map1.keySet()) {
            if (!map2.containsKey(key))
                return false;
            else if (!map1.get(key).equals(map2.get(key)))
                return false;
        }
        return true;
    }
}
