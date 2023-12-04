import java.math.BigDecimal;

public class j5BigDecimal {
    public static void main(String args[]){
        BigDecimal num1 = BigDecimal.valueOf(1.3);
        BigDecimal num2 = new BigDecimal("235345235523.234523");
        BigDecimal num3 = new BigDecimal("876238852839634.4823482384");

        System.out.println(num1);
        System.out.println(num2);
        System.out.println(num3);

        BigDecimal num4 = num1.multiply(num2);
        BigDecimal num5 = num2.multiply(num4);
        BigDecimal num6 = num2.add(num3);

        System.out.println(num4);
        System.out.println(num5);
        System.out.println(num6);
    }
}
