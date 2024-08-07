import java.util.Scanner;

public class j6ValidPalindrome2 {
    
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        System.out.println(validPalindrome(s));
        in.close();
    }
    
    // O(str.length())
    public static boolean validPalindrome(String string) {
        char[] str = string.toCharArray();
        int s = 0;
        int e = string.length() - 1;
        while(s < e){
           if(str[s] != str[e]){
             return isPalindrome(str,s+1,e) || isPalindrome(str,s,e-1);
           }
           s++;
           e--;
        }
        return true;
    }

    public static boolean isPalindrome(char[] str,int s, int e){
        while(s < e){
            if(str[s] != str[e]){
                return false;
            }
            s++;
            e--;
        }
        return true;
    }
}
