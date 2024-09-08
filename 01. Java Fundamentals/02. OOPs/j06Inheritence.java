public class j06Inheritence
{
    public static void main(String args[])
    {

        Shape s = new Shape(2,1);

        System.out.println("No of Vertices = " + s.vertices + " | No of Edges = " + s.edges);

        Circle c = new Circle(1,1,15);

        System.out.println("No of Vertices = " + c.vertices + " | No of Edges = " + c.edges + " | Circumference = " + c.circumference);

        Shape s1 = new Circle(2,3,55);

        System.out.println("No of Vertices = " + s1.vertices + " | No of Edges = " + s1.edges ); //" | Circumference = " + s1.circumference);we cannot access member of child class
        // when we assing child class object of parent class refference variable then it all can access the properties of only refference variable.

    }
}

class Shape // Parent Class
    {
        int vertices;
        int edges;

        Shape()
        {
            this.vertices = 0;
            this.edges = 0;
        }

        Shape(int vertices,int edges)
        {
            this.vertices = vertices;
            this.edges = edges;
        }

        Shape(Shape s)
        {
            this.vertices = s.vertices;
            this.edges = s.edges;
        }
    }

    class Circle extends Shape // Child Class extending the properties
    {
        int circumference;

        Circle()
        {
            super();
            circumference = 0;
        }

        Circle(int vertices,int edges,int circumference)
        {
            super(vertices,edges); // to initilized the members of base class
            this.circumference = circumference;
        }

        Circle(Circle c)
        {
            super(c);
            this.circumference = c.circumference;
        }
    }