import java.util.ArrayList;
import java.util.List;

public class j13FindAndReplacePattern {
    public static void main(String args[]){
        String[] words = new String[]{"abc","deq","mee","aqq","dkd","ccc"};
        String pattern = "abb";
        System.out.println(findAndReplacePattern(words,pattern));
    }
    public static List<String> findAndReplacePattern(String[] words, String pattern) {
        ArrayList<String> out = new ArrayList<String>();
        for(String s : words){
            if(isIsomorphic(s,pattern)){
                out.add(s);
            }
        }
        return out;
    }

     public static boolean isIsomorphic(String s, String t) {
        return mapToHash(s,t) && mapToHash(t,s);
    }

    public static boolean mapToHash(String s,String t){
        char[] hash = new char[256];

        for(int i = 0; i < s.length(); i++){
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if(hash[c1] != '\0' && hash[c1] != c2) return false;
            hash[c1] = c2;
        }
        return true;
    }
}
