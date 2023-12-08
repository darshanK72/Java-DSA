import java.util.Scanner;

// Complexity : O(N)

public class j11FibonacciSeries {
  public static void main(String args[]){
    Scanner in = new Scanner(System.in);

    int n = in.nextInt();

    int a = 0;
    int b = 1;
    int c = a + b;

    for(int i = 1; i <= n; i++){
        System.out.print(c + " ");
        a = b;
        b = c;
        c = a + b;
    }

    in.close();
  }  
}
