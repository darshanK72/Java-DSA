import java.util.Scanner;

// Complexity : O(log10 N)

public class j07ReverseNumber {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        System.out.println(reverseNive(n));
        System.out.println(reverseForNegatives(n));
        System.out.println(reverseForNegativesEfficient(n));
        System.out.println(reverseForNegativesMoreEfficient(n));
        in.close();
    }

    // O(log10(N)) -> will work only for positive numbers
    public static int reverseNive(int n){
        int r = 0;
        while(n > 0){
            r = r*10 + n%10;
            n /= 10;
        }
        return r;
    }

    // O(log10(N)) -> will work for both positive and negative numbers, but cannot handle overflow condition
    public static int reverseForNegatives(int n){
        int r = 0;
        while(n != 0){
            r = r*10 + n%10;
            n /= 10;
        }
        return r;
    }

     // O(log10(N)) -> will work for both positive and negative numbers,and can handle Overflow conditions
    public static int reverseForNegativesEfficient(int n){
        int r = 0;
        while(n != 0){
            if(r > Integer.MAX_VALUE / 10) return 0;
            r = r*10 + n%10;
            n /= 10;
        }
        return r;
    }

    // O(log10(N)) -> will work for both positive and negative numbers, and can handle Overflow conditions
    public static int reverseForNegativesMoreEfficient(int n){
        int r = 0;
        while(n != 0){
            int d = n % 10;
            int rn = r*10 + d;
            if(rn / 10 != r) return 0;
            r = rn;
            n /= 10;
        }
        return r;
    }
}
