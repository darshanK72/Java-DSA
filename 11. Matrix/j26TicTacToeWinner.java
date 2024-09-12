import java.util.Scanner;

public class j26TicTacToeWinner {

     public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int[][] moves = new int[m][2];
        for (int i = 0; i < m; i++) {
            moves[i][0] = in.nextInt();
            moves[i][1] = in.nextInt();
        }
        System.out.println(tictactoeWinner(moves));
        in.close();
    }

    // O(9)
    public static String tictactoeWinner(int[][] moves) {
        char[][] board = new char[3][3];
        char c = 'X';
        for(int[] move : moves){
            board[move[0]][move[1]] = c;
            if(isWinner(board, move[0], move[1], c)){
                if(c == 'X') return "A";
                else return "B";
            }
            if(c == 'X') c = 'O';
            else c = 'X';
        }
        if(moves.length == 9) return "Draw";
        return "Pending";
    }

    public static boolean isWinner(char[][] board,int row,int col,char chr){
        int count = 0;
        for(int c = 0; c < 3; c++){
            if(board[row][c] == chr) count++;
        }
        if(count == 3) return true;
        count = 0;
        for(int r = 0; r < 3; r++){
            if(board[r][col] == chr) count++;
        }
        if(count == 3) return true;
        count = 0;
        for(int d = 0; d < 3; d++){
            if(board[d][d] == chr) count++;
        }
        if(count == 3) return true;
        count = 0;
        for(int d = 0; d < 3; d++){
            if(board[d][3 - d - 1] == chr) count++;
        }
        if(count == 3) return true;
        return false;
    }
}
