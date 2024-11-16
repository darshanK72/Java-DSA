import java.util.Scanner;

public class j10ReduceNumberToOne{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(countStepsToReduceNumberToOne(n));
        System.out.println(countStepsToReduceNumberToOneRecursion(n));
        in.close();
    }

    // O(n)
    public static int countStepsToReduceNumberToOne(int n){
        int count = 0;
        if(n == Integer.MAX_VALUE) return 32;
        while(n != 1){
            if(n % 2 == 0){
                n = n / 2;
            }else if(n == 3 || n % 4 == 1){
                n = n - 1;
            }else if(n % 4 == 3){
                n = n + 1;
            }
            count++;
        }
        return count;
    }

    // O(n)
    public static int countStepsToReduceNumberToOneRecursion(int n){
        if (n == Integer.MAX_VALUE)
            return 32;
        if(n == 1)
            return 0;
        if ((n & 1) == 0)
            return 1 + countStepsToReduceNumberToOneRecursion(n / 2);
        if (n == 3 || (n & 3) == 1)
            return 1 + countStepsToReduceNumberToOneRecursion(n - 1);
        else
            return 1 + countStepsToReduceNumberToOneRecursion(n + 1);
    }
}