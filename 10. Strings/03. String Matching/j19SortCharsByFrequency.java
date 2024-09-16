import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class j19SortCharsByFrequency {
     public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        System.out.println(frequencySortArrayHash(s));
        System.out.println(frequencySortHashing(s));
        in.close();
    }

    public static String frequencySortArrayHash(String s) {
        int[] hash = new int[128];
        for(int i = 0; i < s.length(); i++){
            hash[s.charAt(i)]++;
        }

        StringBuilder out = new StringBuilder();
        while(out.length() < s.length()){
            int max = 0;
            char c = '\0';
            for(int i = 0; i < 128; i++){
                if(hash[i] > max){
                    max = hash[i];
                    c = (char)i;
                }
            }
            for(int i = 0; i < max; i++){
                out.append(c);
            }
            hash[c] = 0;
        }
        return out.toString();
    }

    public static String frequencySortHashing(String s){
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        
        HashMap<Integer,ArrayList<Character>> countMap = new HashMap<>();
        for(char c : freqMap.keySet()){
            int count = freqMap.get(c);
            ArrayList<Character> list = countMap.getOrDefault(count, new ArrayList<Character>());
            list.add(c);
            countMap.put(count, list);
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (countMap.containsKey(i)) {
                for (char c : countMap.get(i)) {
                    for (int j = 0; j < i; j++) {
                        sb.append(c);
                    }
                }
            }
        }
        
        return sb.toString();
    }
}
