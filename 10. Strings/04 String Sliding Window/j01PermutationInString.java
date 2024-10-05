import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class j01PermutationInString {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s1 = in.next();
        String s2 = in.next();
        System.out.println(checkInclusion(s1, s2));
        System.out.println(checkInclusionEfficient(s1, s2));
        System.out.println(checkInclusionMoreEfficient(s1, s2));
        in.close();
    }

    public static boolean checkInclusion(String s1, String s2) {
        int windowSize = s1.length();
        char[] s = s1.toCharArray();
        Arrays.sort(s);
        s1 = new String(s);
        for (int i = 0; i <= s2.length() - windowSize; i++) {
            char[] ts = s2.substring(i, i + windowSize).toCharArray();
            System.out.println(ts);
            Arrays.sort(ts);
            if (s1.equals(new String(ts))) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkInclusionEfficient(String s1, String s2) {
        int windowSize = s1.length();
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        for (char c : s1.toCharArray()) {
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }

        if (windowSize > s2.length())
            return false;

        for (int i = 0; i < windowSize; i++) {
            char c = s2.charAt(i);
            map2.put(c, map2.getOrDefault(c, 0) + 1);
        }

        if (map1.equals(map2))
            return true;

        for (int i = windowSize; i < s2.length(); i++) {
            char c1 = s2.charAt(i - windowSize);
            int val = map2.get(c1);
            if (val == 1)
                map2.remove(c1);
            else
                map2.put(c1, val - 1);
            char c2 = s2.charAt(i);
            map2.put(c2, map2.getOrDefault(c2, 0) + 1);
            if (map1.equals(map2))
                return true;
        }
        return false;
    }

    public static boolean checkInclusionMoreEfficient(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        if (n1 > n2)
            return false;
        int[] map1 = new int[26];
        for (int i = 0; i < n1; i++) {
            map1[s1.charAt(i) - 'a']++;
        }

        int[] map2 = new int[26];
        for (int i = 0; i < n1; i++) {
            map2[s2.charAt(i) - 'a']++;
        }

        if (isEqual(map1, map2))
            return true;

        for (int i = n1; i < n2; i++) {
            map2[s2.charAt(i - n1) - 'a']--;
            map2[s2.charAt(i) - 'a']++;
            if (isEqual(map1, map2))
                return true;
        }
        return false;
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i])
                return false;
        }
        return true;
    }
}
