package project2;

import java.util.Arrays;

//rozwiazuje gre
public class SudokuSolver {
    private static boolean checkIfThisNumberIsCorrect(int[][] board, int rowOfEmptySpace, int colOfEmptySpace, int number) {
        // check column
        for (int tempCol = 0; tempCol < board.length; tempCol++) {
            if (board[rowOfEmptySpace][tempCol] == number) {
                return false;
            }
        }

        // check row
        for (int[] tempRow : board) {
            if (tempRow[colOfEmptySpace] == number) {
                return false;
            }
        }

        // check square
        int sqrt = (int)Math.sqrt(board.length);
        int boxRowStart = rowOfEmptySpace - rowOfEmptySpace % sqrt;
        int boxColStart = colOfEmptySpace - colOfEmptySpace % sqrt;

        for (int tempRow = boxRowStart; tempRow < boxRowStart + sqrt; tempRow++) {
            for (int tempCol = boxColStart; tempCol < boxColStart + sqrt; tempCol++) {
                if (board[tempRow][tempCol] == number) {
                    return false;
                }
            }
        }

        // nie znalezlismy powtorzenia mozna wstawic te liczbe
        return true;
    }

    private static boolean solveSudokuHelper(int[][] board, int boardLength) {
        // we need default values
        // and we will never have negative values here
        int rowOfEmptySpace = -1;
        int colOfEmptySpace = -1;
        boolean noEmptySpaces = true;

        //check if there are empty spaces
        for (int tempRow=0; tempRow < boardLength; tempRow++) {
            for (int tempCol=0; tempCol < boardLength; tempCol++) {
                if(board[tempRow][tempCol] == 0) {
                    //founded empty space
                    rowOfEmptySpace = tempRow;
                    colOfEmptySpace = tempCol;

                    noEmptySpaces = false;
                    break;
                }
            }

            // if empty space is founded we can break loop
            if(!noEmptySpaces)
                break;
        }

        if (noEmptySpaces)
            return true;

        //patrzymy czy na pierwsze puste miejsce ktore znalezlismy mozna wstawic ktoras liczbe 1-9
        for (int num = 1; num <= boardLength; num++) {
            if (checkIfThisNumberIsCorrect(board, rowOfEmptySpace, colOfEmptySpace, num)) {

                //jezeli mozemy ją wstawić probujemy rozwiazac sudoku ze wstawioną tą liczbą
                board[rowOfEmptySpace][colOfEmptySpace] = num;

                if (solveSudokuHelper(board, boardLength)) {
                    //udalo sie rozwiazac sudoku z tą liczbą
                    return true;
                }
                else {
                    // nie udało się rozwiązać sudoku z tą liczba patrzymy pozostałe
                    board[rowOfEmptySpace][colOfEmptySpace] = 0;
                }
            }
        }

        // z rzadną liczbą 1-9 nie rozwiązemy obecnego ulozenia liczb sudoku
        // zwracamy false i bedziemy probwac zmienic ktorąś opcje w przeszlosci
        return false;
    }

    public void printSolvedSudoku(int[][] board) {
        for (int[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }

    public boolean solveSudoku(int[][] board) {
        return solveSudokuHelper(board, board.length);
    }
}
