import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class j08GroupAnagrams {
    public static void main(String args[]) {
        String[] strs = new String[] { "eat", "tea", "tan", "ate", "nat", "bat" };
        System.out.println(groupAnagrams(strs));
        System.out.println(groupAnagramsHashMap(strs));
        System.out.println(groupAnagramsHashMapEfficient(strs));
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        ArrayList<List<String>> out = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            if (strs[i] == null)
                continue;
            ArrayList<String> temp = new ArrayList<>();
            temp.add(strs[i]);
            for (int j = i + 1; j < strs.length; j++) {
                if (strs[j] != null && isAnagram(strs[i], strs[j])) {
                    temp.add(strs[j]);
                    strs[j] = null;
                }
            }
            out.add(temp);
        }
        return out;
    }

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

    public static List<List<String>> groupAnagramsHashMap(String[] strs) {
        ArrayList<List<String>> out = new ArrayList<>();
        HashMap<HashMap<Character, Integer>, ArrayList<String>> map = new HashMap<>();
        for (String s : strs) {
            HashMap<Character, Integer> cmap = new HashMap<>();
            for (char c : s.toCharArray()) {
                cmap.put(c, cmap.getOrDefault(c, 0) + 1);
            }
            if (map.containsKey(cmap)) {
                ArrayList<String> list = map.get(cmap);
                list.add(s);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(s);
                map.put(cmap, list);
            }
        }

        for (HashMap<Character, Integer> key : map.keySet()) {
            out.add(map.get(key));
        }
        return out;
    }

    public static List<List<String>> groupAnagramsHashMapEfficient(String[] strs) {
        ArrayList<List<String>> out = new ArrayList<>();
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] cars = s.toCharArray();
            Arrays.sort(cars);
            String sorts = new String(cars);
            if (map.containsKey(sorts)) {
                ArrayList<String> list = map.get(sorts);
                list.add(s);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(s);
                map.put(sorts, list);
            }
        }

        for (String key : map.keySet()) {
            out.add(map.get(key));
        }
        return out;
    }
}
