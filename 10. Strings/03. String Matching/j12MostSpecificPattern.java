import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class j12MostSpecificPattern {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        ArrayList<String> dict = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            dict.add(in.next());
        }
        String pattern = in.next();
        System.out.println(findMatchedWords(n, dict, pattern));
        System.out.println(findMatchedWordsEfficient(n, dict, pattern));
        in.close();
    }

    public static ArrayList<String> findMatchedWords(int n, ArrayList<String> dict,
            String pattern) {
        ArrayList<String> out = new ArrayList<>();
        for (String s : dict) {
            if (isIsomorphic(s, pattern))
                out.add(s);
        }
        return out;
    }

    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length())
            return false;
        HashMap<Character, Character> cmap = new HashMap<>();
        HashMap<Character, Boolean> bmap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);

            if (cmap.containsKey(ch1)) {
                if (cmap.get(ch1) != ch2)
                    return false;
            } else {
                if (bmap.containsKey(ch2))
                    return false;
                else {
                    cmap.put(ch1, ch2);
                    bmap.put(ch2, true);
                }
            }
        }
        return true;
    }

    public static ArrayList<String> findMatchedWordsEfficient(int n, ArrayList<String> dict, String pattern) {
        ArrayList<String> out = new ArrayList<>();
        int[] lastIndex = new int[256];
        String encodedPattern = encodeString(pattern, lastIndex);
        System.out.println(encodedPattern);

        for (String word : dict) {
            if (word.length() == pattern.length() && encodeString(word, lastIndex).equals(encodedPattern)) {
                out.add(word);
            }
        }
        return out;
    }

    private static String encodeString(String str, int[] lastIndex) {
        Arrays.fill(lastIndex, -1);
        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (lastIndex[c] == -1) {
                lastIndex[c] = i;
            }
            encoded.append(lastIndex[c]);
        }
        return encoded.toString();
    }

}