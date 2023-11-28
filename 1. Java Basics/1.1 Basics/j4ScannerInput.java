import java.util.Scanner;

public class j4ScannerInput {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println(input.next());
        System.out.println(input.nextLine());
        System.out.println(input.nextInt());

        input.close();
        
    }  
}
