import java.util.Scanner;

public class j04CheckRotatedString{

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        System.out.println(rotateString(s1,s2));
        System.out.println(rotateStringEfficient(s1,s2));
        in.close();
    }

    // Brut Force
    // O(s.length * s.length)
    public static boolean rotateString(String s, String goal) {
        if(s.length() != goal.length()) return false;
        int i = 0;
        while(i < s.length()){
          s = shift(s);
          if(s.equals(goal)) return true;
          i++;
        }
        return false;
     }

    public static boolean rotateStringEfficient(String s, String goal) {
        if(s.length() != goal.length()) return false;
       goal += goal;
       if(goal.indexOf(s) != -1) return true;
       return false;
    }
     // O(s.length)
     public static String shift(String s){
         String out = s.substring(s.length() - 1);
         out += s.substring(0, s.length() - 1);
         return out;
     }
}