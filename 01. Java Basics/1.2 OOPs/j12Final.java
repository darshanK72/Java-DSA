public class j12Final {

	public static void main(String[] args) {
		final int speed_limint = 30;
		
		/*
		 * speed_limint += 32; we cannot modifiy final variable it act as constant
		 */
		Addition2 n = new Addition2();
		
		System.out.println(n.add(3, 2));
		System.out.println(speed_limint);
	}
	

}

class Addition
{
	final int add(int a,int b)
	{
		return a+b;
	}
}

final class Addition2 extends Addition
{
//	int add(int a,int b)
//	{
//		return a+b;
//	}
//	We cannot override final method
	
}

//class Addition3 extends Addition2
//{
//	
//}
// we cannot extend final class
