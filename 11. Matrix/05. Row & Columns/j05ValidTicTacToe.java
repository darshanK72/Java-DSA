import java.util.Scanner;

public class j05ValidTicTacToe {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String[] board = new String[3];
        for(int i = 0; i < 3; i++){
            board[i] = in.nextLine();
        }
        System.out.println(validTicTacToe(board));
        in.close();
    }

    public static boolean validTicTacToe(String[] board) {
        int countX = 0;
        int countO = 0;
        for (String s : board) {
            for (int i = 0; i < 3; i++) {
                if (s.charAt(i) == 'X')
                    countX++;
                else if (s.charAt(i) == 'O')
                    countO++;
            }
        }
        if (countX != countO && countO != countX - 1)
            return false;
        if (isWinner(board, 'X') && countX != countO + 1)
            return false;
        if (isWinner(board, 'O') && countX != countO)
            return false;
        return true;
    }

    public static boolean isWinner(String[] board, char c) {
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == c && board[i].charAt(1) == c && board[i].charAt(2) == c)
                return true;
        }

        for (int i = 0; i < 3; i++) {
            if (board[0].charAt(i) == c && board[1].charAt(i) == c && board[2].charAt(i) == c)
                return true;
        }

        if (board[0].charAt(0) == c && board[1].charAt(1) == c && board[2].charAt(2) == c)
            return true;

        if (board[0].charAt(2) == c && board[1].charAt(1) == c && board[2].charAt(0) == c)
            return true;

        return false;
    }
}
