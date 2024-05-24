public class j40Generics {

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

    public static void main(String args[])
    {
        Gen<String> g1 = new Gen<String>("Hello");
        System.out.println(g1.getValue());

        Gen<Integer> g2 = new Gen<Integer>(532);
        System.out.println(g2.getValue());

        Gen<Float> g3 = new Gen<Float>(52.52f);
        System.out.println(g3.getValue());
    }
    
}
