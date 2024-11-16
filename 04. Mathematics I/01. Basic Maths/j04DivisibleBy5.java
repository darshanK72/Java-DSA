import java.util.Scanner;

public class j04DivisibleBy5 {
     public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String n = in.next();
        System.out.println(isDivisibleBy5(n));
        in.close();
    }

    // If last digit is 0 or 5
    // divisible by 5. 
    public static boolean isDivisibleBy5(String str) 
    {
        int n = str.length();
        int last = str.charAt(n - 1) - '0'; 
        return last == 0 || last == 5;
    } 
}
