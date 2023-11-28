import java.util.Scanner;
public class j9LoopStatements {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);


        // print table of any number 
        int num = input.nextInt();

        for(int i = 1; i <= 10; i++)
        {
            System.out.println(num + " X " + i + " = " + num*i);
        }

        // print the input string until we get quit as input

        int s = input.nextInt();
        int sum = 0;
        while(s != -1)
        {
            sum += s;
            s = input.nextInt();
        }


        System.out.println(sum);

        //Do while loop

        int i = input.nextInt();
        do
        {
            System.out.println(i); // Runs atleast once no matter condition is true or not
            i++;

        }while(i <= 20);




        input.close();

    }
    
}
