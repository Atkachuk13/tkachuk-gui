package tkachuk.gui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.List;

public class Gui extends JFrame
{
    private JTextField[][] text = new JTextField[9][9];
    int[][] arr = new int[9][9];
    Sudoku sudoku = new Sudoku(arr);

    public Gui()
    {
        // frame
        JFrame frame = new JFrame("Sudoku GUI");
        frame.setSize(600, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        // panel
        JPanel panel = new JPanel(new GridLayout(3, 3, 3, 3));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        JPanel[][] subPanel = new JPanel[3][3];

        //sub-panel
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                subPanel[i][j] = new JPanel(new GridLayout(3, 3));
                subPanel[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                panel.add(subPanel[i][j]);
            }
        }

        // text fields in sub-panel
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                text[i][j] = new JTextField(2);
                text[i][j].setHorizontalAlignment(JTextField.CENTER);
                text[i][j].setFont(new Font("Arial", Font.BOLD, 18));

                // Document Listener
                text[i][j].getDocument().addDocumentListener(new DocumentListener()
                {
                    @Override
                    public void insertUpdate(DocumentEvent e)
                    {
                        checkCompletion();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e)
                    {
                        checkCompletion();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e)
                    {
                        checkCompletion();
                    }
                });

                int boxRow = i / 3;
                int boxCol = j / 3;
                subPanel[boxRow][boxCol].add(text[i][j]);
            }
        }

        // initializing the board with numbers
        int[][] initVals = {
                {0, 0, 5}, {0, 1, 3}, {0, 4, 7},
                {1, 0, 6}, {1, 3, 1}, {1, 4, 9}, {1, 5, 5},
                {2, 1, 9}, {2, 2, 8},
                {3, 0, 8}, {3, 4, 6}, {3, 8, 3},
                {4, 0, 4}, {4, 3, 8}, {4, 5, 3}, {4, 8, 1},
                {5, 0, 7}, {5, 4, 2}, {5, 8, 6},
                {6, 1, 6}, {6, 7, 8},
                {7, 3, 4}, {7, 4, 1}, {7, 5, 9}, {7, 8, 5},
                {8, 4, 8}, {8, 7, 7}, {8, 8, 9}
        };

        for (int[] val : initVals)
        {
            int row = val[0];
            int col = val[1];
            int num = val[2];

            text[row][col].setText(String.valueOf(num));
            text[row][col].setEditable(false);
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);


    }

    public JTextField[][] getText()
    {
        return text;
    }

    public void setText(JTextField[][] text)
    {
        this.text = text;
    }

    public void checkCompletion()
    {
        boolean complete = true;

        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                String field = text[i][j].getText().trim();
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
                text[i][j].setBackground(Color.WHITE);
            }
        }

        for (SudokuError error : errors)
        {
            text[error.row()][error.col()].setBackground(new Color(255, 204, 204));
        }
    }

    public static void main(String[] args)
    {
        new Gui();

    }
}
