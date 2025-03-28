package tkachuk.gui;

import org.junit.jupiter.api.Test;
import tkachuk.gui.Sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SudokuTest
{
    @Test
    public void getErrors()
    {

        // board with errors
        int[][] nums = {
                {4, 3, 5, 2, 6, 9, 7, 8, 1},
                {6, 8, 2, 5, 7, 1, 4, 9, 3},
                {1, 9, 7, 8, 3, 4, 5, 6, 2},
                {8, 2, 6, 1, 9, 5, 3, 4, 7},
                {3, 5, 4, 7, 8, 2, 9, 1, 8},
                {9, 7, 1, 2, 4, 3, 6, 2, 5},
                {5, 1, 9, 3, 2, 6, 8, 7, 4},
                {2, 4, 8, 9, 5, 7, 1, 3, 6},
                {7, 6, 3, 4, 1, 8, 2, 5, 9}};

        Sudoku board = new Sudoku(nums);

        List<SudokuError> expectedList = Arrays.asList(
                new SudokuError(5, 3, 2),
                new SudokuError(4, 8, 8),
                new SudokuError(5, 7, 2),
                new SudokuError(1, 2, 2));

        List<SudokuError> actualList = board.getErrors();

        assertEquals(expectedList, actualList);

    }

    @Test
    public void getErrorsCorrect()
    {

        // correct board
        int[][] nums = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}};

        Sudoku board = new Sudoku(nums);

        List<String> expectedList = Arrays.asList();

        assertTrue(board.getErrors().isEmpty());

    }

    @Test
    public void toStringTest()
    {

        int[][] nums = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}};

        Sudoku board = new Sudoku(nums);

        String expectedBoard =
                """

                        -------------------------------------
                        | 5 | 3 | 4 | 6 | 7 | 8 | 9 | 1 | 2 |
                        -------------------------------------
                        | 6 | 7 | 2 | 1 | 9 | 5 | 3 | 4 | 8 |
                        -------------------------------------
                        | 1 | 9 | 8 | 3 | 4 | 2 | 5 | 6 | 7 |
                        -------------------------------------
                        | 8 | 5 | 9 | 7 | 6 | 1 | 4 | 2 | 3 |
                        -------------------------------------
                        | 4 | 2 | 6 | 8 | 5 | 3 | 7 | 9 | 1 |
                        -------------------------------------
                        | 7 | 1 | 3 | 9 | 2 | 4 | 8 | 5 | 6 |
                        -------------------------------------
                        | 9 | 6 | 1 | 5 | 3 | 7 | 2 | 8 | 4 |
                        -------------------------------------
                        | 2 | 8 | 7 | 4 | 1 | 9 | 6 | 3 | 5 |
                        -------------------------------------
                        | 3 | 4 | 5 | 2 | 8 | 6 | 1 | 7 | 9 |
                        ---------------------------------------
                        """;

        assertEquals(expectedBoard, board.toString());

    }

}