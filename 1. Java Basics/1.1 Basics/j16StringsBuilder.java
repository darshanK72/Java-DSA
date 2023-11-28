import java.util.Scanner;
public class j16StringsBuilder {
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        //Making String Builder Object string
        StringBuilder s = new StringBuilder("Hello,World");
        StringBuilder s1 = new StringBuilder(); // Empty String

        // iterating through string  
        for(int i = 0; i < s.length(); i++)
        {
            System.out.println(s.charAt(i));
        }

        //printing stringbuilder
        System.out.println(s);

        // String comparasion

        StringBuilder s2 = new StringBuilder("Hello,World");

        System.out.println(s1.compareTo(s2));
        System.out.println(s.compareTo(s2));

        System.out.println(s.equals(s2));


        // Appending to string
        s1.append("Hello,World");
        System.out.println(s1);

        s.append(" Thank You!");
        System.out.println(s);

        // Deleting from String
        s.deleteCharAt(3);
        System.out.println(s);

        s.delete(3,11);
        System.out.println(s);

        // Searching in stirng
        System.out.println(s.indexOf("You"));
        System.out.println(s.indexOf("ank",21));

        // Changing existing string inserting the existing string
        s.replace(1,10,"Darshanzaz");
        System.out.println(s);

        s.insert(4,"Helloooo");
        System.out.println(s);


        // Substring
        System.out.println(s.substring(3));
        System.out.println(s.substring(5,18));

        // Striping
        System.out.println("###2sdfsdfs  ".strip());


        


        in.close();
    }
    
}
