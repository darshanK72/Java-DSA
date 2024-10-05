import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class j05CountSubstrWithAtMostKDistinctChars {
     public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int k = in.nextInt();
        System.out.println(getCountOfSubstring(k, s));
        System.out.println(getCountOfSubstringEfficient(k, s));
        in.close();
    }

    public static int getCountOfSubstring(int k, String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            HashSet<Character> set = new HashSet<>();
            for (int j = i; j < s.length(); j++) {
                char ch = s.charAt(j);
                set.add(ch);
                if (set.size() > k)
                    break;
                else {
                    count++;
                }
            }
        }
        return count;
    }

    public static int getCountOfSubstringEfficient(int k, String s) {
        int count = 0;
        int j = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            while (map.size() > k) {
                char ch1 = s.charAt(j);
                map.put(ch1, map.get(ch1) - 1);
                if (map.get(ch1) == 0) {
                    map.remove(ch1);
                }
                j++;
            }
            count += i - j + 1;
        }
        return count;
    }
}
