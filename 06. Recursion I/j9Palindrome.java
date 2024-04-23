import java.util.Scanner;

public class j9Palindrome{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        String str = in.next();
        System.out.println(isPalindrome(str,0,str.length()-1));            
        in.close();
    }
    public static boolean isPalindrome(String str,int s,int e){
        if(s >= e) return true;
        return (str.charAt(s) == str.charAt(e)) && isPalindrome(str,++s,--e);
    }
}   
