import java.util.Scanner;

public class j17ValidAnagram {

     public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        System.out.println(isAnagram(s1,s2));
        in.close();
    }
    
    // O(s1.length + s2.length)
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
