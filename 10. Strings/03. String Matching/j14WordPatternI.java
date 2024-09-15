import java.util.HashMap;
import java.util.Scanner;

public class j14WordPatternI {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        System.out.println(wordPattern(s1, s2));
        in.close();
    }

    public static boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (pattern.length() != words.length)
            return false;
        HashMap<Character, String> map = new HashMap<>();
        HashMap<String, Boolean> bmap = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            if (map.containsKey(ch)) {
                if (!map.get(ch).equals(words[i]))
                    return false;
            } else {
                if (bmap.containsKey(words[i]))
                    return false;
                else {
                    map.put(ch, words[i]);
                    bmap.put(words[i], true);
                }
            }
        }
        return true;
    }
}
