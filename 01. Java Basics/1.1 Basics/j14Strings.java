import java.util.Scanner;
public class j14Strings {
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);

        String s1 = "Darshan";

        String s2 = "Khairnar";

        String s3 = "Abhishek Khairnar";

        // Concat

        String s4 = s1 +" "+ s2;

        System.out.println(s4);

        // compareTo

        System.out.println(s1.compareTo(s3));

        System.out.println(s1 == s2);

        //substring

        System.out.println(s3.substring(2,10));

        //charAt

        System.out.println(s1.charAt(0)); 


        // String Declaration
		String str1 = "my name is Darshan Khairnar"; 
		
		// Length of string
		System.out.println(str1.length());
		
		// Character at particular index
		System.out.println(str1.charAt(3));
		
		// Compare two string lexographically
		// if greater gives number greater than 0, if less then negative number if equal then gives 0
		System.out.println(str1.compareTo("ny name is Darshan Khairnar"));
		
		// Compare two strings lexographicall, igonring case
		System.out.println(str1.compareToIgnoreCase("MY NAME IS DARSHAN KHAIRNAR"));
		
		// checking equality using ==
		System.out.println("hello" == "hello");
		System.out.println("hii" == new String("hii"));
		/*
		 * == is true only when both strings objects are same, means same memory location in string pool
		 * else returns false
		 */
		
		// checking equality using equals
		// if lexographically two strings are equal returns true,else false
		System.out.println(str1.equals("my name is Darshan Khairnar"));
		System.out.println(str1.equalsIgnoreCase("MY NAME IS DARSHAN KHAIRNAR"));
		
		// concating strings
		System.out.println(str1.concat(" Hello,World"));
		
		// Uppercase, Lowercase
		System.out.println("abcd".toUpperCase());
		System.out.println("ABCD".toLowerCase());
		
		// index of
		System.out.println(str1.indexOf("Darshan"));
		System.out.println("abcdefffxyzxzy".indexOf('f'));
		System.out.println("abcdefffxyzxzy".lastIndexOf('f'));
		
		// substring
		System.out.println(str1.substring(8));
		System.out.println(str1.substring(3,11));
		
		// replace string
		System.out.println(str1.replace("Darshan", "Abhishek"));
		

        in.close();
    }
    
}
