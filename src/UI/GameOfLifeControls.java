package UI;
import com.company.CellGrid;
import com.company.Factory;
import com.company.GameOfLife;

public class GameOfLifeControls implements CellGrid
{
    CellGrid cellGrid = Factory.getGrid();
    @Override
    public boolean getCellStatus(int x, int y) {
        return false;
    }

    @Override
    public void setCell(int x, int y, boolean status)
    {
        cellGrid.setCell(x, y, status);
    }

    @Override
    public void clear() {

    }
}
