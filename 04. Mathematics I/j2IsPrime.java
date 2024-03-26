import java.util.Scanner;

public class j2IsPrime {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        System.out.println("Number " + n + " is " + (isPrimeNive(n) ? "Prime" : "Composite"));

        in.close();
    }

    // O(n)
    public static boolean isPrimeNive(int number) {
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    //O(sqrt(n))
    public static boolean isPrimeEfficient(int number){
        for(int i = 2; i*i <= number; i++){
            if(number % i == 0){
                return false;
            }
        }
        return true;
    }

    // every prime number is 6n+1 or 6n-1, where n = 1,2,3,4,...
    public static boolean isPrimeMoreEfficient(int number){
        if(number == 1) return false;
        else if(number == 2 || number == 3) return true;
        else if(number % 2 == 0 || number % 3 == 0) return false;
        else {
            for(int i = 5; i*i <= number; i += 6){
                if(number % i == 0 || number % (i+2) == 0) return false;
            }
            return true;
        }
    }
}