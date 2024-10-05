import java.util.HashMap;
import java.util.Scanner;

public class j07CountSubstrWithExactKDistinctChars {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int k = in.nextInt();
        System.out.println(countSubstrWithExactKDistinct(s, k));
        in.close();
    }

    public static int countSubstrWithExactKDistinct(String s, int k) {
        return countAtMostK(k, s) - countAtMostK(k - 1, s);
    }

    public static int countAtMostK(int k, String s) {
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
