package tkachuk.gui;

public class SudokuError
{

    private int row;
    private int col;
    private int num;

    public SudokuError(int row, int col, int num)
    {
        this.row = row;
        this.col = col;
        this.num = num;
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    public int getNum()
    {
        return num;
    }


}
