import java.util.Scanner;

public class j2PrimitiveDataTypes
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        boolean flag = true; // 1 bit (true or false);
        byte b = 4; // byte (1 - 256) (1 byte)
        char c = 'a'; // char (1 - 256) (1 byte)
        short s = 10; // short (1 - 1024) (2 bytes)
        int i = 23423; // int (4 bytes)
        float f = 234.23f; // float (4 bytes)
        double d = 2342342342.234223; // double (8 bytes)
        long l = 23423534243345345l;

        System.out.println("Flag : " + flag);

        System.out.print("Enter Byte : ");
        b = input.nextByte();
        System.out.println("You Entered : " + b);

        System.out.print("Enter Character : ");
        c = input.next().charAt(0);
        System.out.println("You Entered : " + c);

        System.out.print("Enter Short : ");
        s = input.nextShort();
        System.out.println("You Entered : " + s);

        System.out.print("Enter Integer : ");
        i = input.nextInt();
        System.out.println("You Entered : " + i);

        System.out.print("Enter Float : ");
        f = input.nextFloat();
        System.out.println("You Entered : " + f);

        System.out.print("Enter Double : ");
        d = input.nextDouble();
        System.out.println("You Entered : " + d);
        
        System.out.print("Enter Long : ");
        l = input.nextLong();
        System.out.println("You Entered : " + l);

        input.close();

    }
}