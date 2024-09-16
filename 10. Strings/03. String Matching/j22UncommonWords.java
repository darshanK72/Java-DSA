import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class j22UncommonWords {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        System.out.println(Arrays.toString(uncommonFromSentences(s1,s2)));
        in.close();
    }

    public static String[] uncommonFromSentences(String s1, String s2) {
        String[] strs1 = s1.split(" ");
        String[] strs2 = s2.split(" ");
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : strs1) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        for (String s : strs2) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        ArrayList<String> temp = new ArrayList<>();
        for (String key : map.keySet()) {
            if (map.get(key) == 1)
                temp.add(key);
        }
        String[] out = new String[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            out[i] = temp.get(i);
        }
        return out;
    }
}
