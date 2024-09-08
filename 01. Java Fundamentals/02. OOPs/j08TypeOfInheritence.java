public class j08TypeOfInheritence {

	public static void main(String[] args) {
		
		Ractangle r1 = new Ractangle(4,20,30);
		
		System.out.println(r1.area());
		
		Triangle t1 = new Triangle(3,8,9,3);
		System.out.println(t1.area());
	}

}

/*
 * Type of Inheritence
 * 1) Multilevel Inheritence --> when class is inerited from other class which is also inheritted from another one
 * 2) Multiple Inheritence --> when class is inheritted by many 
 * 3) Dimond Inheritence --> when a class is inheritted from other two class, and has same methods in both of them.
 * it causes ambiguity, to eliminate that problem Interfaces are used
 */

class Shape1
{
	private int sides;
	
	public Shape1()
	{
		
	}

	public Shape1(int sides) {
		super();
		this.setSides(sides);
	}

	public int getSides() {
		return sides;
	}

	public void setSides(int sides) {
		this.sides = sides;
	}
	
}


// Using extends keyword we can derive properties of parent class in child class
/*
 * Inheritence is known as deriving propert
 */
class Ractangle extends Shape1
{
	private int length;
	private int breadth;
	
	public Ractangle(int sides,int length,int breadth)
	{
		super(sides);
		this.length = length;
		this.breadth = breadth;
	}
	
	public float area()
	{
		return this.length*this.breadth;
	}
}

class Triangle extends Shape1
{
	private int side_1;
	private int side_2;
	private int side_3;
	
	public Triangle(int side,int side_1, int side_2, int side_3) {
		super(side);
		this.side_1 = side_1;
		this.side_2 = side_2;
		this.side_3 = side_3;
	}
	
	public float area()
	{
		float f = (this.side_1 + this.side_2 + this.side_2) / 2;
		System.out.println(f);
		return (float) Math.sqrt(f*(f-this.side_1)*(f-this.side_2)*(f-this.side_3));
	}
	
	
}
