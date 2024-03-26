import java.util.Scanner;;

public class j1OddEven1 {
    public static void main(String args[]){
       Scanner in = new Scanner(System.in); 

       int n = in.nextInt();

       System.out.println("Number " + n + " is " + (isOdd(n) ? "Odd" : "Even"));

       in.close();
    }

    // O(1)
    public static boolean isOdd(int number){
        return number % 2 == 1;
    }
}
