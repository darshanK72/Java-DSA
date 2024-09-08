import java.util.Scanner;
public class j03Finaly {

	public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
		
		try {
			int a = in.nextInt();
			int b = in.nextInt();
			
			int c = a/b;
			
			System.out.println("C = " + c);
		}
		
		catch(ArithmeticException  ex)
		{
			System.out.println(ex.toString());
		}
		catch(NumberFormatException ex)
		{
			System.out.println(ex.toString());
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
		finally
		{
			System.out.println("This will always executed!!");
			
		}
		in.close();
	}

}
