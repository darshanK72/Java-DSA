import java.util.ArrayList;
import java.util.List;

public class j14GroupAnagrams {

    public static void main(String args[]){
        String[] strs = new String[]{"eat","tea","tan","ate","nat","bat"};
        System.out.println(groupAnagrams(strs));
    }
    public static List<List<String>> groupAnagrams(String[] strs) {
        ArrayList<List<String>> out = new ArrayList<>();
        for(int i = 0; i < strs.length; i++){
            if(strs[i] == null) continue;
            ArrayList<String> temp = new ArrayList<>();
            temp.add(strs[i]);
            for(int j = i+1; j < strs.length; j++){
                if(strs[j] != null && isAnagram(strs[i],strs[j])){
                    temp.add(strs[j]);
                    strs[j] = null;
                }
            }
            out.add(temp);
        }
        return out;
    }

     public static boolean isAnagram(String s1, String s2) {
        if(s1.length() != s2.length()) return false;
        int[] hash1 = new int[26];
        int[] hash2 = new int[26];
        for(int i = 0; i < s1.length();i++){
            hash1[s1.charAt(i) - 'a']++;
        }
        for(int i = 0; i < s2.length();i++){
            hash2[s2.charAt(i) - 'a']++;
        }

        for(int i = 0; i < 26; i++){
            if(hash1[i] != hash2[i]) return false;
        }
        return true;
    }
}
