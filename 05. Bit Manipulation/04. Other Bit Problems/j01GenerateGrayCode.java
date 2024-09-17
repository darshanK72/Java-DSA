import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class j01GenerateGrayCode {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(generateGrayCode1(n));
        System.out.println(generateGrayCode2(n));
        in.close();
    }

    public static ArrayList<String> generateGrayCode1(int n) {
        if (n == 1)
            return new ArrayList<String>(Arrays.asList("0", "1"));

        ArrayList<String> temp = generateGrayCode1(n - 1);
        ArrayList<String> out = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            out.add("0" + temp.get(i));
        }

        for (int i = temp.size() - 1; i >= 0; i--) {
            out.add("1" + temp.get(i));
        }
        return out;
    }

    public static ArrayList<Integer> generateGrayCode2(int n) {
        if (n == 1) {
            return new ArrayList<>(Arrays.asList(0, 1));
        } else {

            ArrayList<Integer> out = generateGrayCode2(n - 1);
            for (int i = out.size() - 1; i >= 0; i--) {
                out.add(out.get(i) | (1 << (n - 1)));
            }
            return out;
        }
    }
}
