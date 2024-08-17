import java.util.Scanner;

public class j3OutputTypes {
    public static void main(String[] args)
    {

        Scanner input = new Scanner(System.in);

        int i = input.nextInt();
        System.out.printf("You Entered Integer : %d\n",i);
        System.out.print("You Entered Integer" + i); // Prints in same line
        System.out.println("You Entered Integer " + i); // Prints newline after printing given statement

        input.close();
    }
    
}
