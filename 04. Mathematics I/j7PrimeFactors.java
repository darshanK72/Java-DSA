import java.util.Scanner;

// Complexity : O(sqrt(N))

public class j7PrimeFactors {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int temp = n;

        for(int i = 2; i <= Math.sqrt(temp); i++){
            while(n % i == 0){
                System.out.print(i + " ");
                n /= i;
            }
        }
        in.close();
    }
}
