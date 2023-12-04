import java.util.Arrays;
public class j11Array {
    public static void main(String args[])
    {
        int[] arr = new int[20];

        for(int i = 0; i < 20; i++)
        {
            arr[i] = (i+1)*(i+1);
        }

        for(int i = 0; i < 20; i++)
        {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        System.out.println(Arrays.toString(arr));
    }

    
}
