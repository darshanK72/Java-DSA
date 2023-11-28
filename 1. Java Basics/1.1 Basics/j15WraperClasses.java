import java.util.Scanner;
public class j15WraperClasses {
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);

        Boolean bb = false;
        Byte b = 127;
        Character c = '2';
        Integer i = 20234;
        Long l = 2342523534546l;
        Float f = 234.534534f;
        Double d = 235345234.334;
        String s = "Hello, World";

        System.out.println(bb);
        System.out.println(b);
        System.out.println(c);
        System.out.println(i);
        System.out.println(l);
        System.out.println(f);
        System.out.println(d);
        System.out.println(s);
        //System.out.println(bb);

        in.close();
    }
}
