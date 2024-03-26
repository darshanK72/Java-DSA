import java.util.Scanner;

// Complexity : O(sqrt(N))

public class j7PrimeFactors {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        getPrimeFactorsEfficient(n);

        in.close();
    }

    public static void getPrimeFactorsNaive(int number){
        for(int i = 2; i <= number; i++){
            if(isPrime(i)){
                int x = i;
                while(number % x == 0){
                    System.out.println(i);
                    x *= i;
                }
            }
        }
    }

    public static void getPrimeFactorsEfficient(int number){
        if(number <= 1) return;
        for(int i = 2; i*i <= number; i++){
            if(number%i == 0){
                while (number % i == 0) {
                    System.out.println(i);
                    number /= i;
                }
            }
        }
        if(number > 1) System.out.print(number);
    }

    public static boolean isPrime(int number) {
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

}
