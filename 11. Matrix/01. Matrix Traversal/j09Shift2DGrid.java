import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j09Shift2DGrid {

    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int col = in.nextInt();
        int[][] matrix = new int[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                matrix[i][j] = in.nextInt();
            }
        }
        int k = in.nextInt();

        List<List<Integer>> grid = shiftGrid(matrix,k);

        System.out.println(grid);
        in.close();
    }
    
    // O(n^2)
    public static List<List<Integer>> shiftGrid(int[][] grid, int k) {
        ArrayList<List<Integer>> out = new ArrayList<List<Integer>>();
        while(k > 0){
            shiftOne(grid);
            k--;
        }
        for(int i = 0; i < grid.length; i++){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for(int j = 0; j < grid[0].length;j++){
                temp.add(grid[i][j]);
            }
            out.add(temp);
        }
        return out;
    }
    // O(n^2)
    public static void shiftOne(int[][] grid){
        int[] temp = new int[grid.length];
        for(int i = 0; i < grid.length; i++){
            temp[i] = grid[i][grid[0].length - 1];
        }
        
        for(int j = grid[0].length - 1; j > 0; j--){
            for(int i = 0; i < grid.length; i++){
                grid[i][j] = grid[i][j - 1];
            }
        }
        int t = temp[grid.length -1];
        for(int i = grid.length - 1; i > 0; i--){
            grid[i][0] = temp[i - 1];
        }
        grid[0][0] = t;
    }
}
