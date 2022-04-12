package project2;

import lombok.Getter;

import java.util.Arrays;

//zajmuje sie kontrolą jednej gry
@Getter
public class BasicSudoku implements Sudoku{
    @Override
    public void playOneGame() {
        System.out.println("tak");
//        for (int[] row : board) {
//            System.out.println(Arrays.toString(row));
//        }
    }

    @Override
    public void createNewRandomBoard() {

    }

    @Override
    public void resetBoard() {
    }

    public static void main(String[] args){
        int[][] board = new int[][] {
                { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
                { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
                { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
                { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
                { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
                { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
                { 0, 0, 5, 2, 0, 6, 3, 0, 0 }
        };

        SudokuSolver solver = new SudokuSolver();
        if (solver.solveSudoku(board)) {
            solver.printSolvedSudoku(board);
        } else {
            System.out.println("Cant solve");
        }


        //playOneGame(board);
    }
}
