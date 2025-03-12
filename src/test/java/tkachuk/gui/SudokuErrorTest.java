package tkachuk.gui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SudokuErrorTest
{

    @Test
    public void toStringTest()
    {

        SudokuError error = new SudokuError(3,4,6);

        String expected = "row:3col:4num:6";

        assertEquals(expected, error.toString());
    }
}
