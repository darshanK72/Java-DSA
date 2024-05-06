import java.util.Scanner;
public class j20NQueens{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] chess = new int[n][n];
        placeNQueens(chess,"",0);
        in.close();
    }

    public static void placeNQueens(int[][] chess,String ans,int row){
        if(row == chess.length){
            System.out.println(ans);
            return;
        }
        for(int col = 0; col < chess.length; col++){
            if(isQueenSafe(chess,row,col)){
                chess[row][col] = 1;
                placeNQueens(chess,ans + row + "-" + col + ",",row + 1);
                chess[row][col] = 0;
            }
        }
    }

    public static boolean isQueenSafe(int[][] chess,int row,int col){
        for(int i = row - 1; i >= 0; i--){
            if(chess[i][col] == 1){
                return false;
            }
        }

        for(int i = row - 1,j = col - 1; i >= 0 && j >= 0; i--,j--){
            if(chess[i][j] == 1){
                return false;
            }
        }

        for(int i = row - 1,j = col + 1; i >= 0 && j < chess[0].length;i--,j++){
            if(chess[i][j] == 1){
                return false;
            }
        }

        return true;
    }


}