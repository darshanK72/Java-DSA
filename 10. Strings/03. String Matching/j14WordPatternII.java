import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class j14WordPatternII {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        System.out.println(wordPatternII(s1, s2));
        in.close();
    }

    public static boolean wordPatternII(String s, String pattern) {
        ArrayList<String> words = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int j = i;
            while (j + 1 < s.length() && !Character.isUpperCase(s.charAt(j + 1))) {
                j++;
            }
            words.add(s.substring(i, j));
            i = j + 1;
        }
        if (pattern.length() != words.size())
            return false;
        HashMap<Character, String> map = new HashMap<>();
        HashMap<String, Boolean> bmap = new HashMap<>();
        for (int k = 0; k < pattern.length(); k++) {
            char ch = pattern.charAt(k);
            if (map.containsKey(ch)) {
                if (!map.get(ch).equals(words.get(k)))
                    return false;
            } else {
                if (bmap.containsKey(words.get(k)))
                    return false;
                else {
                    map.put(ch, words.get(k));
                    bmap.put(words.get(k), true);
                }
            }
        }
        return true;
    }

}
