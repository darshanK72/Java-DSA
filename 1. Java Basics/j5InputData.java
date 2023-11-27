import java.util.Scanner;
public class j5InputData {
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);

        boolean b = in.nextBoolean();
        System.out.println(b);

        byte bb = in.nextByte();
        System.out.println(bb);

        char c = in.next().charAt(0);
        System.out.println(c);

        int integer = in.nextInt();
        System.out.println(integer);

        long lng = in.nextLong();
        System.out.println(lng);

        float f = in.nextFloat();
        System.out.println(f);

        double d = in.nextDouble();
        System.out.println(d);

        in.nextLine(); // to ignore string before taking actual input
        String str = in.nextLine();
        System.out.println(str);

        in.close();

    }
    
}
