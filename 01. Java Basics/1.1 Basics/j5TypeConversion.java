import java.util.Scanner;
public class j5TypeConversion {
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);


        boolean bn = true;
        System.out.println(bn);

        byte b = input.nextByte();
        System.out.println(b);

        char c = input.next().charAt(0); // Char conversion
        System.out.println(c);

        short s = input.nextShort(); //We cannot give data types like float, double or long to short as this is smaller than that emplicitly
        System.out.println(s);

        int i = input.nextInt();
        System.out.println("Expelicit conversion of Integers : ");
        System.out.println((float)i);
        System.out.println((char)i);
        System.out.println((short)i);
        System.out.println((byte)i);

        float f = input.nextFloat();
        System.out.println("Expelicit conversion of Float : ");
        System.out.println(f);
        System.out.println((int)f);
        System.out.println((char)f);
        System.out.println((byte)f);
        System.out.println((short)f);

        double d = input.nextDouble();
        System.out.println("Expelicit conversion of  : ");
        System.out.println(d);
        System.out.println((int)d);
        System.out.println((char)d);
        System.out.println((byte)d);
        System.out.println((short)d);

        short x = 20;

        short y = (short) (x * 20);
        System.out.println(" y = " + y);

        input.close();
    }
}
