package tkachuk.gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SudokuController
{

    private Gui gui;
    private JTextField[][] field;
    private int[][] arr;
    private Sudoku sudoku;

    public SudokuController(Sudoku sudoku, JTextField[][] field)
    {
        this.sudoku = sudoku;
        this.field = field;
    }

    public void checkCompletion()
    {
        boolean complete = true;

        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                String field = gui.getText()[i][j].getText().trim();
                if (field.isEmpty())
                {
                    complete = false;
                } else
                {
                    try
                    {
                        arr[i][j] = Integer.parseInt(field);
                    } catch (NumberFormatException e)
                    {
                        return;
                    }
                }
            }
        }

        if (complete)
        {
            checkErrors();
        }
    }

    public void checkErrors()
    {
        List<SudokuError> errors = sudoku.getErrors();

        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                field[i][j].setBackground(Color.WHITE);
            }
        }

        for (SudokuError error : errors)
        {
            field[error.row()][error.col()].setBackground(new Color(255, 204, 204));
        }
    }


    public void checkBoard()
    {
        checkCompletion();
        checkErrors();
    }
}
