import java.util.LinkedList;
import java.util.Queue;

public class j02RottenOranges {

    static class Cell {
        int i;
        int j;
        int time;

        Cell(int i, int j, int t) {
            this.i = i;
            this.j = j;
            this.time = t;
        }
    }

    public static int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] rowDir = new int[] { 1, 0, -1, 0 };
        int[] colDir = new int[] { 0, 1, 0, -1 };
        boolean[][] visited = new boolean[m][n];
        Queue<Cell> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    visited[i][j] = true;
                    queue.add(new Cell(i, j, 0));
                }
            }
        }
        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean hasRottenOranges = false;
            for (int i = 0; i < size; i++) {
                Cell cell = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int row = cell.i + rowDir[d];
                    int col = cell.j + colDir[d];
                    if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && !visited[row][col]
                            && grid[row][col] == 1) {
                        visited[row][col] = true;
                        grid[row][col] = 2;
                        queue.add(new Cell(row, col, cell.time + 1));
                        hasRottenOranges = true;
                    }
                }
            }
            if (hasRottenOranges) {
                time++;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1)
                    return -1;
            }
        }

        return time;
    }
}
