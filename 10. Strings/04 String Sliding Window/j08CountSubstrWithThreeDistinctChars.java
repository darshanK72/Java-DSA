import java.util.HashMap;
import java.util.Scanner;

public class j08CountSubstrWithThreeDistinctChars {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        System.out.println(numberOfSubstrings(s));
        System.out.println(numberOfSubstringsEfficient1(s));
        System.out.println(numberOfSubstringsEfficient2(s));
        in.close();
    }

    public static int numberOfSubstrings(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int[] hash = new int[3];
            for (int j = i; j < s.length(); j++) {
                hash[s.charAt(j) - 'a'] = 1;
                if (hash[0] + hash[1] + hash[2] == 3) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static int numberOfSubstringsEfficient1(String s) {
        int ans = 0;
        int[] hash = new int[] { -1, -1, -1 };
        for (int i = 0; i < s.length(); i++) {
            hash[s.charAt(i) - 'a'] = i;
            if (hash[0] != -1 && hash[1] != -1 && hash[2] != -1) {
                ans += Math.min(hash[0], Math.min(hash[1], hash[2])) + 1;
            }
        }
        return ans;
    }

    public static int numberOfSubstringsEfficient2(String s) {
        return getCountOfSubstringEfficient(3, s) - getCountOfSubstringEfficient(2, s);
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
