import java.util.Scanner;
public class j2SkipCharacter{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        String s = in.next();
        char c = in.next().charAt(0);
        System.out.println(skipCharacter(s,c));
        in.close();
    }

    public static String skipCharacter(String s,char c){
        if(s.length() == 0) return "";
        String r = skipCharacter(s.substring(1),c);
        if(s.charAt(0) == c) return r;
        else return s.charAt(0) + r;
    }

    public static String skipCharacterBacktrack(String s, char c) {
        if (s.isEmpty()) {
            return "";
        }
        char firstChar = s.charAt(0);
        if (firstChar == c) {
            return skipCharacter(s.substring(1), c);
        } else {
            return firstChar + skipCharacter(s.substring(1), c);
        }
    }
}