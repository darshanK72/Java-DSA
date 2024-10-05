import java.util.HashMap;
import java.util.Scanner;

public class j03CountSubstrWithoutRepeatingChars {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        System.out.println(countSubstringEfficient(s));
        in.close();
    }

    public static int countSubstringEfficient(String s) {
        int count = 0;
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
            count += (i - j + 1);
        }
        return count;
    }
}
