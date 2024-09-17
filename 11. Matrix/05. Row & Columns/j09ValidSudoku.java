import java.util.Scanner;

public class j09ValidSudoku{
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int[][] board = new int[9][9];

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                board[i][j] = in.nextInt();
            }
        }
        System.out.println(isValidSudoku(board));
        in.close();
    }

    public static boolean isValidSudoku(int[][] board) {
        for(int i = 0; i < 9; i++){
            int[] map = new int[9];
            for(int j = 0; j < 9; j++){
                int n = board[i][j];
                if(n >= 1 && n <= 9){
                    map[n - 1]++;
                }
            }
            if(!isValid(map)) return false;
            map = new int[9];
            for(int j = 0; j < 9; j++){
                int n = board[j][i];
                if(n >= 1 && n <= 9){
                    map[n - 1]++;
                }
            }
            if(!isValid(map)) return false;
        }

        for(int r = 0; r < 9; r+=3){
            for(int c = 0; c < 9; c+=3){
                int[] map = new int[9];
                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        int n = board[r+i][c+j];
                        if(n >= 1 && n <= 9){
                            map[n - 1]++;
                        }
                    }
                }
                if(!isValid(map)) return false;
            }
        }
        return true;
    }

    public static boolean isValid(int[] map){
        for(int i = 0; i < 9; i++){
            if(map[i] > 1) return false;
        }
        return true;
    }

}