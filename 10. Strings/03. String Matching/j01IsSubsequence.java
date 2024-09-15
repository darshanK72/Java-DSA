import java.util.Scanner;

public class j01IsSubsequence {
     public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        System.out.println(isSubsequence(s1,s2));
        in.close();
    }

    public static boolean isSubsequence(String s1,String s2){
        int i = 0;
        int j = 0;
        while(i < s1.length() && j < s2.length()){
            if(s1.charAt(i) == s2.charAt(j)){
                i++;
            }
            j++;
        }
        return i == s1.length();
    }

}
