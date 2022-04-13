package project2;

import lombok.Getter;

//zajmuje sie kontrolą jednej gry typu podstawowego
@Getter
public class BasicSudoku implements Sudoku{
    private final int[][] board;

    public BasicSudoku() {
        this.board = new int[][] {
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
    }
    public BasicSudoku(int[][] board) {
        this.board = board;
    }

    @Override
    public void playOneGame() {
        System.out.println("tak");
    }

    @Override
    public void createNewRandomBoard() {

    }

    @Override
    public void resetBoard() {
    }

    public static void main(String[] args){
        BasicSudoku basicSudoku = new BasicSudoku();
        int[][] board = basicSudoku.getBoard();

        SudokuSolver solver = new SudokuSolver();
        if (solver.solveSudoku(board)) {
            solver.printSolvedSudoku(board);
        } else {
            System.out.println("Cant solve");
        }
    }
}
