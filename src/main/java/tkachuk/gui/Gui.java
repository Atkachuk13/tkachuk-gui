package tkachuk.gui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Gui extends JFrame
{
    private JTextField[][] text = new JTextField[9][9];

    public Gui()
    {
        // frame
        JFrame frame = new JFrame("Sudoku GUI");
        frame.setSize(600, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        // panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 9, 5, 5));

        // first row empty
        for (int col = 0; col < 9; col++)
        {
            panel.add(new JLabel(""));
        }

        // text fieledes
        for (int row = 0; row < 9; row++)
        {
            for (int col = 0; col < 9; col++)
            {
                text[row][col] = new JTextField(2);
                panel.add(text[row][col]);
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

        JButton check = new JButton("Check Board");
        check.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                int[][] nums = new int[9][9];
                boolean isFilled = true;

                for (int row = 0; row < 9; row++)
                {
                    for (int col = 0; col < 9; col++)
                    {
                        String value = text[row][col].getText().trim();
                        if (value.isEmpty())
                        {
                            isFilled = false;
                            break;
                        }
                        try
                        {
                            nums[row][col] = Integer.parseInt(value);
                        } catch (NumberFormatException ex)
                        {
                            isFilled = false;
                            break;
                        }
                    }
                    if (!isFilled) break;
                }

                if (!isFilled)
                {
                    JOptionPane.showMessageDialog(frame, "Not all fields are filled",
                            "Incomplete Board", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // check for errors
                Sudoku board = new Sudoku(nums);
                List<SudokuError> errors = board.getErrors();

                if (errors.isEmpty())
                {
                    JOptionPane.showMessageDialog(frame, "You got it!",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                } else
                {
                    for (SudokuError error : errors)
                    {
                        text[error.getRow()][error.getCol()].setBackground(new Color(255, 204, 204));
                    }
                }
            }
        });


        frame.add(panel, BorderLayout.CENTER);
        frame.add(check, BorderLayout.SOUTH);
        frame.setVisible(true);

    }

    public static void main(String[] args)
    {
        new Gui();

    }
}