import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class j28FindCommonChars {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) {
            strs[i] = in.next();
        }
        System.out.println(commonCharsHashMap(strs));
        System.out.println(commonCharsArrayHashEfficient(strs));
        in.close();
    }

    public static List<String> commonCharsHashMap(String[] words) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : words[0].toCharArray()) {
            if (!map.containsKey(c))
                map.put(c, 0);
            map.put(c, map.get(c) + 1);
        }

        for (int i = 1; i < words.length; i++) {
            HashMap<Character, Integer> tempMap = new HashMap<>();
            for (char c : words[i].toCharArray()) {
                if (!tempMap.containsKey(c))
                    tempMap.put(c, 0);
                tempMap.put(c, tempMap.get(c) + 1);
            }

            for (char c : map.keySet()) {
                if (tempMap.containsKey(c)) {
                    map.put(c, Math.min(map.get(c), tempMap.get(c)));
                } else {
                    map.put(c, 0);
                }
            }

        }

        ArrayList<String> out = new ArrayList<String>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            for (int k = 0; k < entry.getValue(); k++)
                out.add("" + entry.getKey());

        }
        return out;
    }

    public static List<String> commonCharsArrayHashEfficient(String[] words) {
        int[] map = new int[26];
        for (char c : words[0].toCharArray()) {
            map[c - 'a']++;
        }
        for (int i = 1; i < words.length; i++) {
            int[] tempMap = new int[26];
            for (char c : words[i].toCharArray()) {
                tempMap[c - 'a']++;
            }

            for (int j = 0; j < 26; j++) {
                if (map[j] != 0 && tempMap[j] < map[j]) {
                    map[j] = tempMap[j];
                }
            }
        }
        ArrayList<String> out = new ArrayList<String>();
        for (int i = 0; i < 26; i++) {
            if (map[i] > 0) {
                for (int k = 0; k < map[i]; k++)
                    out.add("" + (char) ('a' + i));
            }
        }
        return out;
    }

    public static List<String> commonChars(String[] words) {
        int[] last = count(words[0]);
        
        for (int i = 1; i < words.length; i++) {
            last = intersection(last, count(words[i]));
        }
        
        List<String> arr = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (last[i] != 0) {
                char a = (char) ('a' + i);
                String s = String.valueOf(a);
                while (last[i] > 0) {
                    arr.add(s);
                    last[i]--;
                }
            }
        }
        return arr;
    }

    private static int[] intersection(int[] a, int[] b) {
        int[] t = new int[26];
        for (int i = 0; i < 26; i++) {
            t[i] = Math.min(a[i], b[i]);
        }
        return t;
    }

    private static int[] count(String str) {
        int[] t = new int[26];
        for (char c : str.toCharArray()) {
            t[c - 'a']++;
        }
        return t;
    }
}
