import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j02LargestWordInDictionary {
      public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        int n = in.nextInt();
        ArrayList<String> d = new ArrayList<>();
        for(int i = 0; i < n; i++){
            d.add(in.next());
        }
        System.out.println(findLongestWord(s,d));
        in.close();
    }
    public static String findLongestWord(String S, List<String> d) {
        String output = "";
        for (String s : d) {
            if (isSubsequence(s, S)) {
                if (s.length() > output.length() || (s.length() == output.length() && s.compareTo(output) < 0)) {
                    output = s;
                }
            }
        }
        return output;
    }

    public static boolean isSubsequence(String s1, String s2) {
        int i = 0;
        int j = 0;
        while (i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) == s2.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s1.length();
    }
}
