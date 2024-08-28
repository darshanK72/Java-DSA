import java.util.Arrays;
import java.util.Scanner;

public class j28GameOfLife {

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
        gameOfLife(matrix);
        System.out.println(Arrays.toString(matrix));
        in.close();
    }

    public static void gameOfLife(int[][] board) {
        int[][] mat = new int[board.length][board[0].length];

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                mat[i][j] = board[i][j];
            }
        }
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(liveOrDie(mat,i,j)){
                    board[i][j] = 1;
                }else{
                    board[i][j] = 0;
                }
            }
        }
    }

    public static boolean liveOrDie(int[][] board,int row,int col){
        int lifesCount = 0;
        if(row - 1 >= 0 && col - 1 >= 0 && board[row - 1][col - 1] == 1) lifesCount++;
        if(row - 1 >= 0 && board[row - 1][col] == 1) lifesCount++;
        if(row - 1 >= 0 && col + 1 < board[0].length && board[row - 1][col + 1] == 1) lifesCount++;
        if(col + 1 < board[0].length && board[row][col + 1] == 1) lifesCount++;
        if(row + 1 < board.length  && col + 1 < board[0].length && board[row + 1][col + 1] == 1) lifesCount++;
        if(row + 1 < board.length && board[row + 1][col] == 1) lifesCount++;
        if(row + 1 < board.length && col - 1 >= 0 && board[row + 1][col - 1] == 1) lifesCount++;
        if(col - 1 >= 0 && board[row][col - 1] == 1) lifesCount++;

        if(board[row][col] == 1){
            if(lifesCount < 2 || lifesCount > 3) return false;
        }else{
            if(lifesCount != 3) return false;
        }
        return true;
    }

    public static void gameOfLifeInplace(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (liveOrDie(board, i, j)) {
                    if (board[i][j] == 0) {
                        board[i][j] = 2; // Mark dead cell to become live
                    }
                } else {
                    if (board[i][j] == 1) {
                        board[i][j] = -1; // Mark live cell to become dead
                    }
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 0; // Update transitional state to dead
                } else if (board[i][j] == 2) {
                    board[i][j] = 1; // Update transitional state to live
                }
            }
        }
    }

    public static boolean liveOrDieInplace(int[][] board, int row, int col) {
        int lifesCount = 0;
        if (row - 1 >= 0 && col - 1 >= 0 && (board[row - 1][col - 1] == 1 || board[row - 1][col - 1] == -1)) lifesCount++;
        if (row - 1 >= 0 && (board[row - 1][col] == 1 || board[row - 1][col] == -1)) lifesCount++;
        if (row - 1 >= 0 && col + 1 < board[0].length && (board[row - 1][col + 1] == 1 || board[row - 1][col + 1] == -1)) lifesCount++;
        if (col + 1 < board[0].length && (board[row][col + 1] == 1 || board[row][col + 1] == -1)) lifesCount++;
        if (row + 1 < board.length && col + 1 < board[0].length && (board[row + 1][col + 1] == 1 || board[row + 1][col + 1] == -1)) lifesCount++;
        if (row + 1 < board.length && (board[row + 1][col] == 1 || board[row + 1][col] == -1)) lifesCount++;
        if (row + 1 < board.length && col - 1 >= 0 && (board[row + 1][col - 1] == 1 || board[row + 1][col - 1] == -1)) lifesCount++;
        if (col - 1 >= 0 && (board[row][col - 1] == 1 || board[row][col - 1] == -1)) lifesCount++;

        if (board[row][col] == 1) {
            return !(lifesCount < 2 || lifesCount > 3);
        } else {
            return lifesCount == 3;
        }
    }

    public static void gameOfLife2(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
    
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int lives = countLiveNeighbors(board, i, j);
    
                // If the cell is currently live (1)
                if (board[i][j] == 1) {
                    // It dies if it has fewer than 2 or more than 3 live neighbors
                    if (lives < 2 || lives > 3) {
                        board[i][j] = -1; // -1 signifies the cell was live, but now dead
                    }
                } 
                // If the cell is currently dead (0)
                else {
                    // It becomes live if it has exactly 3 live neighbors
                    if (lives == 3) {
                        board[i][j] = 2; // 2 signifies the cell was dead, but now live
                    }
                }
            }
        }
    
        // Final pass to update the board to the new state
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 0; // dead cell
                } else if (board[i][j] == 2) {
                    board[i][j] = 1; // live cell
                }
            }
        }
    }
    
    private static int countLiveNeighbors(int[][] board, int row, int col) {
        int lives = 0;
        int[] directions = {-1, 0, 1};
    
        for (int i : directions) {
            for (int j : directions) {
                if (i == 0 && j == 0) continue; // Skip the cell itself
    
                int r = row + i;
                int c = col + j;
    
                if (r >= 0 && r < board.length && c >= 0 && c < board[0].length) {
                    // Check the current or previous state of the cell
                    if (board[r][c] == 1 || board[r][c] == -1) {
                        lives++;
                    }
                }
            }
        }
        return lives;
    }    
}
