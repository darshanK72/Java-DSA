import java.util.Scanner;

// Complexity : O(1)

public class j8IsPythaghorasTriplet {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();

        if(a > b && a > c){
            System.out.print(a*a == b*b + c*c);
        }
        else if(b > a && b > c){
            System.out.print(b*b == a*a + c*c);
        }
        else{
            System.out.print(c*c == a*a + b*b);
        }

        in.close();
    }
}
