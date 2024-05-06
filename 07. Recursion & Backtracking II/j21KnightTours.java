import java.util.Scanner;
import java.util.Arrays;
public class j21KnightTours{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] chess = new int[n][n];
        knightTour(chess,0,0,1);
        in.close();
    }

    public static void knightTour(int[][] chess,int row,int col,int move){
        if(row < 0 || col < 0 || row >= chess.length || col >= chess.length || chess[row][col] > 0){
            return;
        }
        else if(move == chess.length * chess.length){
            chess[row][col] = move;
            System.out.println(Arrays.deepToString(chess));
            chess[row][col] = 0;
            return;
        }

        chess[row][col] = move;
        knightTour(chess,row - 2,col + 1,move + 1);
        knightTour(chess,row - 1,col + 2,move + 1);
        knightTour(chess,row + 1,col + 2,move + 1);
        knightTour(chess,row + 2,col + 1,move + 1);

        knightTour(chess,row + 2,col - 1,move + 1);
        knightTour(chess,row + 1,col - 2,move + 1);
        knightTour(chess,row - 1,col - 2,move + 1);
        knightTour(chess,row - 2,col - 1,move + 1);
        chess[row][col] = 0;
    }

}