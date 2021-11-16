package UI;
import com.company.CellGrid;
import Factory.Factory;
import com.company.Grid;
import com.company.UIListener;

public class GameOfLifeControls implements UIListener
{
    CellGrid cellGrid = Factory.getGrid();
    Board board;

    public boolean getCellStatus(int x, int y) {
        return false;
    }


    public void setCell(int x, int y, boolean status)
    {
        cellGrid.setCell(x, y, status);
    }


    public void clear() {

    }

    @Override
    public void updateGraphics(Grid g)
    {
        board.updateBoard(g);
    }
    public void setBoard(Board b)
    {
        this.board=b;
    }
}
