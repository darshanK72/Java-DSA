import java.util.Scanner;;

public class j1OddEven {
    public static void main(String args[]){
       Scanner in = new Scanner(System.in); 
       int number1 = in.nextInt();
       System.out.println("Number " + number1 + " is " + (isOdd(number1) ? "Odd" : "Even"));
       String number2 = in.next();
       System.out.println("Number " + number2 + " is " + (isOdd(number2) ? "Odd" : "Even"));
       in.close();
    }

    // O(1)
    public static boolean isOdd(int number){
        return number % 2 == 1;
    }

    public static boolean isOdd(String number){
        return number.charAt(number.length() - 1) % 2 == 0;
    }
}
