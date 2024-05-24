import java.math.BigInteger;

public class j48BitInteger {

    public static void main(String args[]){

        BigInteger num1 = BigInteger.valueOf(13);
        BigInteger num2 = new BigInteger("235345235234523");
        BigInteger num3 = new BigInteger("8762388528394823482384");

        System.out.println(num1);
        System.out.println(num2);
        System.out.println(num3);

        BigInteger num4 = num1.multiply(num2);
        BigInteger num5 = num2.divide(num1);
        BigInteger num6 = num2.add(num3);

        System.out.println(num4);
        System.out.println(num5);
        System.out.println(num6);



    }
    
}
