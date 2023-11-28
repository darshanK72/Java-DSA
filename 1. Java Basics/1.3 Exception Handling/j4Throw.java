import java.util.Scanner;

public class j4Throw {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		try {
			int a = in.nextInt();
			int b = in.nextInt();
			
			if(b == 0)
			{
				throw new ArithmeticException("Cannot divide by zero");
			}
			else
			{
				int c = a/b;
				System.out.println("C = " + c);
			}
			
			
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
		in.close();

	}

}
