package UI;
import com.company.*;
import Factory.Factory;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GameOfLifeControls implements UIListener
{
    CellGrid cellGrid;
    BLListener gameControls;
    Board board;

    public GameOfLifeControls(GameOfLife gc)
    {
        this.gameControls = gc;
        this.cellGrid = gameControls.getGrid();
        this.gameControls.addUIListener(this);
    }

    public void setBoard(Board b)
    {
        this.board=b;
    }
    public void setGOLControls(GameOfLife g)
    {
        gameControls = g;
    }

    //BLListener
    public int getCurrentZoom()
    {
        return this.gameControls.getZoom();
    }
    public int getCurrentSpeed()
    {
        return this.gameControls.getSpeed();
    }

    //CellGrid
    public void setCell(int x, int y, boolean status)
    {
        cellGrid.setCell(x, y, status);
    }
    public boolean getCell(int x, int y)
    {
        return cellGrid.getCellStatus(x, y);
    }

    //UIListener
    @Override
    public void updateGraphics(Grid g)
    {
        board.updateBoard(g);
    }


}
