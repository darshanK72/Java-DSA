/*
 * Topic: Java Generics
 * This program demonstrates various concepts of Generic Programming in Java including:
 * - Generic Classes
 * - Generic Methods
 * - Bounded Type Parameters
 * - Wildcard Arguments
 * - Multiple Type Parameters
 */

public class j01Generics {
    // Generic class with single type parameter
    static class Gen<T>
    {
        T var;

        public Gen(T v)
        {
            this.var = v;
        }

        public T getValue()
        {
            return this.var;
        }
    }
    
    // Generic class with multiple type parameters
    static class Pair<K, V> {
        private K key;
        private V value;
        
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
        
        public K getKey() { return key; }
        public V getValue() { return value; }
    }
    
    // Generic class with bounded type parameter
    static class NumberContainer<T extends Number> {
        T number;
        
        public NumberContainer(T number) {
            this.number = number;
        }
        
        public double square() {
            return number.doubleValue() * number.doubleValue();
        }
    }
    
    // Generic method example
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
    
    // Wildcard example method
    public static void printNumberList(Gen<? extends Number> list) {
        System.out.println("Number value: " + list.getValue());
    }

    public static void main(String args[])
    {
        // Basic generic class usage
        System.out.println("Basic Generic Class Usage:");
        Gen<String> g1 = new Gen<>("Hello");
        Gen<Integer> g2 = new Gen<>(532);
        Gen<Float> g3 = new Gen<>(52.52f);
        
        System.out.println("String value: " + g1.getValue());
        System.out.println("Integer value: " + g2.getValue());
        System.out.println("Float value: " + g3.getValue());

        // Multiple type parameters
        System.out.println("\nMultiple Type Parameters:");
        Pair<String, Integer> pair = new Pair<>("Age", 25);
        System.out.println("Key: " + pair.getKey() + ", Value: " + pair.getValue());

        // Bounded type parameter
        System.out.println("\nBounded Type Parameters:");
        NumberContainer<Integer> nc1 = new NumberContainer<>(5);
        NumberContainer<Double> nc2 = new NumberContainer<>(5.5);
        System.out.println("Square of 5: " + nc1.square());
        System.out.println("Square of 5.5: " + nc2.square());

        // Generic method with arrays
        System.out.println("\nGeneric Method with Arrays:");
        Integer[] intArray = {1, 2, 3, 4, 5};
        String[] strArray = {"Hello", "World"};
        System.out.print("Integer Array: ");
        printArray(intArray);
        System.out.print("String Array: ");
        printArray(strArray);

        // Wildcard usage
        System.out.println("\nWildcard Usage:");
        Gen<Integer> intGen = new Gen<>(100);
        Gen<Double> doubleGen = new Gen<>(100.5);
        printNumberList(intGen);
        printNumberList(doubleGen);
    }
}
