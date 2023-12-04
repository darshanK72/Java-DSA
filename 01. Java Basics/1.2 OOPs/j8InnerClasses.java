public class j8InnerClasses {

    static class Shape
    {
        int sides;
        int area;
    } // Inner Classes
    public static void main(String args[])
    {
        Shape s1 = new Shape();
        s1.area = 20;
        s1.sides = 4;

        System.out.println(s1.area + " " + s1.sides);
    }
    
}

// Outer classes cannot be static it will give error.