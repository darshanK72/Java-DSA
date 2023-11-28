public class j10Polymorphism {

	public static void main(String[] args) {
		
		System.out.println(add(1,3));
		System.out.println(add(23,54));
		System.out.println(add("Hello, ","World"));
		System.out.println(add(2343435,2233423));
		
		
		Ractangleee r1 = new Ractangleee(23,245);
		
		System.out.println(r1.area());
		
		Square s = new Square(53);
		
		System.out.println(s.area());
	}
	
	
	/*
	 * Function Overloading
	 */
	static int add(int a,int b)
	{
		return a+b;
	}
	
	static String add(String a,String b)
	{
		return a + b;
	}
	
	static float add(float a,float b)
	{
		return a+b;
	}
	
	static long add(long a,long b)
	{
		return a + b;
	}

}


class Ractangleee
{
	private int length;
	private int breadth;
	
	public Ractangleee(int length, int breadth) {
		super();
		this.length = length;
		this.breadth = breadth;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getBreadth() {
		return breadth;
	}

	public void setBreadth(int breadth) {
		this.breadth = breadth;
	}
	public int area()
	{
		return this.length*this.breadth;
	}
	
	
}

class Square extends Ractangleee
{
	Square(int side)
	{
		super(side,side);
	}
	
	
	/*
	 * Method Overriding 
	 */
	@Override
	public int area()
	{
		return this.getLength()*this.getBreadth();
	}
}
