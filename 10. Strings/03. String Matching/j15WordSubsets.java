import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class j15WordSubsets {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n1 = in.nextInt();
        String[] words1 = new String[n1];
        for (int i = 0; i < n1; i++) {
            words1[i] = in.next();
        }
        int n2 = in.nextInt();
        String[] words2 = new String[n2];
        for (int i = 0; i < n2; i++) {
            words2[i] = in.next();
        }
        System.out.println(wordSubsets(words1, words2));
        System.out.println(wordSubsetsEfficient(words1, words2));
        in.close();
    }

    public static List<String> wordSubsets(String[] words1, String[] words2) {
        ArrayList<HashMap<Character, Integer>> mapList = new ArrayList<>();
        for (String word : words2) {
            HashMap<Character, Integer> map = new HashMap<>();
            for (char c : word.toCharArray()) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
            mapList.add(map);
        }

        ArrayList<String> out = new ArrayList<>();
        for (String word : words1) {
            HashMap<Character, Integer> tmap = new HashMap<>();
            for (char c : word.toCharArray()) {
                tmap.put(c, tmap.getOrDefault(c, 0) + 1);
            }
            boolean isUni = true;
            for (HashMap<Character, Integer> map : mapList) {
                if (!isUniversal(tmap, map)) {
                    isUni = false;
                    break;
                }
            }
            if (isUni)
                out.add(word);
        }
        return out;
    }

    public static boolean isUniversal(HashMap<Character, Integer> tmap, HashMap<Character, Integer> map) {
        for (char key : map.keySet()) {
            if (!tmap.containsKey(key) || tmap.get(key) < map.get(key)) {
                return false;
            }
        }
        return true;
    }

    public static List<String> wordSubsetsEfficient(String[] words1, String[] words2) {
        int[] maxFreq = new int[26];
        for (String s : words2) {
            int[] freq = new int[26];
            for (char c : s.toCharArray()) {
                freq[c - 'a']++;
            }

            for (int i = 0; i < 26; i++) {
                maxFreq[i] = Math.max(maxFreq[i], freq[i]);
            }
        }

        ArrayList<String> out = new ArrayList<>();
        for (String word : words1) {
            int[] freq = new int[26];
            for (char c : word.toCharArray()) {
                freq[c - 'a']++;
            }

            boolean isUniversal = true;
            for (int i = 0; i < 26; i++) {
                if (freq[i] < maxFreq[i]) {
                    isUniversal = false;
                    break;
                }
            }

            if (isUniversal) {
                out.add(word);
            }
        }
        return out;
    }
}
