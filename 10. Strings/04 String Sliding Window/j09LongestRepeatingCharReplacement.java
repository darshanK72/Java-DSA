import java.util.Arrays;
import java.util.Scanner;

public class j09LongestRepeatingCharReplacement {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int k = in.nextInt();
        System.out.println(characterReplacement(s, k));
        System.out.println(characterReplacementEfficient(s, k));
        System.out.println(characterReplacementMoreEfficient(s, k));
        in.close();
    }

    public static int characterReplacement(String s, int k) {
        int maxL = 0;
        int[] hash = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int maxF = 0;
            Arrays.fill(hash, 0);
            for (int j = i; j < s.length(); j++) {
                hash[s.charAt(j) - 'A']++;
                maxF = Math.max(maxF, hash[s.charAt(j) - 'A']);
                if ((j - i + 1) - maxF <= k) {
                    maxL = Math.max(maxL, j - i + 1);
                } else {
                    break;
                }
            }

        }
        return maxL;
    }

    public static int characterReplacementEfficient(String s, int k) {
        int maxL = 0;
        for (char c = 'A'; c <= 'Z'; c++) {
            int j = 0;
            int tempL = 0;
            int canChange = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != c) {
                    canChange++;
                }
                while (canChange > k && j <= i) {
                    if (s.charAt(j) != c) {
                        canChange--;
                    }
                    j++;
                }
                tempL = Math.max(tempL, i - j + 1);
            }
            maxL = Math.max(maxL, tempL);
        }
        return maxL;
    }

    public static int characterReplacementMoreEfficient(String s, int k) {
        int maxL = 0;
        int maxF = 0;
        int[] hash = new int[26];
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            hash[s.charAt(i) - 'A']++;
            maxF = Math.max(maxF, hash[s.charAt(i) - 'A']);
            if ((i - j + 1) - maxF > k) {
                hash[s.charAt(j) - 'A']--;
                maxF = 0;
                for (int l = 0; l < 26; l++) {
                    maxF = Math.max(maxF, hash[l]);
                }
                j++;
            }
            if ((i - j + 1) - maxF <= k) {
                maxL = Math.max(maxL, (i - j + 1));
            }

        }
        return maxL;
    }
}
