import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class j08AddNegBinary {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n1 = in.nextInt();
        int n2 = in.nextInt();
        int[] num1 = new int[n1];
        int[] num2 = new int[n2];
        for (int i = 0; i < n1; i++) {
            num1[i] = in.nextInt();
        }
        for (int i = 0; i < n2; i++) {
            num2[i] = in.nextInt();
        }

        System.out.println(Arrays.toString(addNegabinary(num1, num2)));

        in.close();
    }

    public static int[] addNegabinary(int[] arr1, int[] arr2) {
        int i = arr1.length - 1;
        int j = arr2.length - 1;
        ArrayList<Integer> output = new ArrayList<Integer>();
        int carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
            int sum = carry;
            if (i >= 0) {
                sum += arr1[i];
                i--;
            }
            if (j >= 0) {
                sum += arr2[j];
                j--;
            }

            output.add(0, sum & 1); // Use bitwise AND to get the least significant bit
            carry = -(sum >> 1); // Use bitwise shift to divide by -2 and account for negative carry
        }

        while (output.size() > 1 && output.get(0) == 0) {
            output.remove(0);
        }

        int[] array = new int[output.size()];
        for (int m = 0; m < output.size(); m++) {
            array[m] = output.get(m);
        }
        return array;
    }
}
