import java.util.Scanner;

// Complexity : O(1)

public class j8IsPythaghorasTriplet {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();

        System.out.println(isPythagoreanTriplet(a, b, c));
        in.close();
    }

    public static boolean isPythagoreanTriplet(int a, int b, int c) {
        return a * a + b * b == c * c || b * b + c * c == a * a || a * a + c * c == b * b;
    }
}