import java.util.Scanner;

// Complexity : O(N)
public class j25FibonacciNumber {
  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    System.out.println(nthFib(n));
    in.close();
  }

  public static int nthFib(int n) {
    if (n == 0)
      return 0;
    if (n == 1)
      return 1;
    int a = 0;
    int b = 1;
    int c = a + b;
    for (int i = 2; i < n; i++) {
      a = b;
      b = c;
      c = a + b;
    }
    return c;
  }
}
