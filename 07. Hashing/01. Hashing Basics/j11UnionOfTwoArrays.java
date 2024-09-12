import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class j11UnionOfTwoArrays {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n1 = in.nextInt();
        int[] arr1 = new int[n1];
        for (int i = 0; i < n1; i++) {
            arr1[i] = in.nextInt();
        }
        int n2 = in.nextInt();
        int[] arr2 = new int[n2];
        for (int i = 0; i < n2; i++) {
            arr2[i] = in.nextInt();
        }
        System.out.println(doUnion(arr1, arr2));
        System.out.println(unionOfTowArrays(arr1, arr2));
        in.close();
    }

    public static int doUnion(int arr1[], int arr2[]) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < arr1.length; i++) {
            set.add(arr1[i]);
        }
        for (int i = 0; i < arr2.length; i++) {
            set.add(arr2[i]);
        }
        return set.size();
    }

    public static ArrayList<Integer> unionOfTowArrays(int arr1[], int arr2[]) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < arr1.length; i++) {
            set.add(arr1[i]);
        }
        for (int i = 0; i < arr2.length; i++) {
            set.add(arr2[i]);
        }
        return new ArrayList<Integer>(set);
    }
}
