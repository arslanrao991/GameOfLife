package UI;
import com.company.*;


public class GameOfLifeControls implements UIListener
{
    BLListener gameControls;
    Board board;

    public GameOfLifeControls(GameOfLife gc)
    {
        this.gameControls = gc;
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
        gameControls.setCell(x, y, status);
    }
    public boolean getCell(int x, int y)
    {
        return gameControls.getCellStatus(x, y);
    }

    //UIListener
    public void updateGraphics(Grid g)
    {
        board.updateBoard(g);
    }


}
