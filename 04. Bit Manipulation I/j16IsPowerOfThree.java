import java.util.Scanner;
public class j16IsPowerOfThree{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(isPowerOfThree(n));
        System.out.println(isPowerOfThreeEfficient(n));
        in.close();
    }

    public static boolean isPowerOfThree(int n){
        String binary = Integer.toBinaryString(n);
        int evenSum = 0;
        int oddSum = 0;
        for(int i = 0; i < binary.length();i++){
            if(i % 2 == 0){
                oddSum += binary.charAt(i) - '0';
            }
            else{
                evenSum += binary.charAt(i) - '0';
            }
        }

        if(evenSum == oddSum || Math.abs(evenSum - oddSum) % 3 == 0) return true;
        return false; 
    }

    public static boolean isPowerOfThreeEfficient(int n){
        return n > 0 && (int)Math.pow(3,19) % n == 0;
    }
}