import java.util.Arrays;
import java.util.Scanner;

public class j03ExitPointInMatrix {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int col = in.nextInt();
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
        System.out.println(Arrays.toString(FindExitPoint(row,col,matrix)));
        in.close();
    }

    // O(M * N)
    public static int[] FindExitPoint(int n, int m, int[][] matrix) {
        int i = 0; 
        int j = 0;
        int dir = 0;
        while(i >= 0 && i < n && j >= 0 && j < m){
            if(matrix[i][j] == 1){
                matrix[i][j] = 0;
                dir = (dir + 1) % 4;
            }
            if(dir == 0) j++;
            else if(dir == 1) i++;
            else if(dir == 2) j--;
            else if(dir == 3) i--;
        }
        
        if(i < 0) i = 0;
        if(j < 0) j = 0;
        if(i >= n) i = n - 1;
        if(j >= m) j = m - 1;
        
        return new int[]{i,j};
    }
}
