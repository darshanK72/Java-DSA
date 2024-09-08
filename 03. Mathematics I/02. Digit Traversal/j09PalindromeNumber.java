import java.util.Scanner;

// Complexity : O(log10 N)
public class j09PalindromeNumber {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (isPalindrome(n)) {
            System.out.println("Palindrome");
        } else {
            System.out.println("Not Palindrome");
        }
        in.close();
    }

    public static boolean isPalindrome(int n) {
        int temp = n;
        int reverse = 0;
        while (n > 0) {
            reverse = reverse * 10 + n % 10;
            n /= 10;
        }
        return reverse == temp;
    }
}
