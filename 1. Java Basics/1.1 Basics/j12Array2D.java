import java.util.Arrays;
import java.util.Scanner;
public class j12Array2D {

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        int[][] arr = new int[3][3]; // Number of columns is not necessary

        // Initilizing 2D array
        // Direcct Initilization
        int[][] arr2 = {{1,2,3,4},
            {5,6,7},
            {8,9,10,11}};

        // Initilization using for loop
        for(int i = 0; i < arr.length; i++)
        {
            for(int j = 0; j < arr[i].length; j++)
            {
                arr[i][j] = in.nextInt();
            }
        }

        //Printing 2D array
        //using for loop
        for(int i = 0; i < arr2.length; i++)
        {
            for(int j = 0; j < arr2[i].length; j++)
            {
                System.out.print(arr2[i][j] + " ");
            }
            System.out.println();
        }
        //using for each loop
        for(int[] ele:arr)
        {
            for(int x:ele)
            {
                System.out.print(x+" ");
            }
            System.out.println();
        }
        //using Arrays.toString()
        for(int[] ar:arr)
        {
            System.out.println(Arrays.toString(ar));
        }

        in.close();
    }
    
}
