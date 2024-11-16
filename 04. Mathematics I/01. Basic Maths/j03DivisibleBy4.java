import java.util.Scanner;

public class j03DivisibleBy4 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String n = in.next();
        System.out.println(isDivisibleBy4(n));
        in.close();
    }

    // If number formed by last two digits is 
    // divisible by 4. 
    public static boolean isDivisibleBy4(String str) 
    { 
        int n = str.length(); 
        if (n == 0) 
            return false;
        if (n == 1) 
            return ((str.charAt(0) - '0') % 4 == 0); 
  
        int last = str.charAt(n - 1) - '0'; 
        int second_last = str.charAt(n - 2) - '0'; 
        return ((second_last * 10 + last) % 4 == 0); 
    } 
}
