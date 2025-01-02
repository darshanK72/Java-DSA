/**
 * Topic: Inner Classes in Java
 * - An **inner class** is a class defined within another class. Inner classes can be static or non-static, depending on 
 *   whether the inner class is associated with the instance of the outer class or not.
 * - In this example, the `Shape` class is a **static inner class** because it is defined within the `j07InnerClasses` class 
 *   and marked as `static`. This allows it to be instantiated independently of the outer class.
 * - Non-static inner classes require an instance of the outer class to be instantiated.
 * - The outer class (`j07InnerClasses`) itself cannot be static, as the instance of the outer class is needed to instantiate 
 *   a non-static inner class.
 */

public class j07InnerClasses {

    // Static Inner Class
    static class Shape {
        int sides;
        int area;
    }

    public static void main(String args[]) {
        // Creating an instance of the static inner class
        Shape s1 = new Shape();
        s1.area = 20;
        s1.sides = 4;

        // Printing values of area and sides
        System.out.println(s1.area + " " + s1.sides);
    }

}

/*
 * Note: Outer classes in Java cannot be static.
 * If you try to declare the outer class `j07InnerClasses` as static, it will
 * result in a compile-time error.
 * This is because static classes can only exist inside a class that is static
 * itself.
 */
