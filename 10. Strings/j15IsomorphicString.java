import java.util.Scanner;

public class j15IsomorphicString {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        System.out.println(isIsomorphic(s1,s2));
        in.close();
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
