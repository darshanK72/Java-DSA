import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class j1HighFrequencyChar {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        String s = in.next();
        System.out.println(highestFrequencyChar(s));
        System.out.println(highestFrequencyCharArrayHashing(s));
        in.close();
    }

    // TC : O(s.length) SC : O(26);
    public static char highestFrequencyChar(String s){
        HashMap<Character,Integer> map = new HashMap<>();
        for(char c : s.toCharArray()){
            if(!map.containsKey(c)){
                map.put(c, 0);
            }
            map.put(c,map.get(c) + 1);
        }
        char out = s.charAt(0);
        for(Map.Entry<Character,Integer> entry : map.entrySet()){
            if(entry.getValue() > map.get(out)){
                out = entry.getKey();
            }   
        }
        return out;
    }

     // TC : O(s.length) SC : O(26);
    public static char highestFrequencyCharArrayHashing(String s){
        int[] hash = new int[26];
        for(char c : s.toCharArray()){
            hash[c - 'a']++;
        }
        char out = s.charAt(0);
        for(int i = 0; i < 26; i++){
            if(hash[i] > hash[out - 'a']){
                out = (char)('a' + i);
            }
        }
        return out;
    }
}
