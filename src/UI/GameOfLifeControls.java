package UI;
import com.BL.*;


public class GameOfLifeControls
{
    UIInterfaceIn gameControls;

    public GameOfLifeControls(GameOfLife gc)
    {
        this.gameControls = gc;
    }

    public void setGOLControls(GameOfLife g)
    {
        gameControls = g;
    }

    //Sending request to BL
    public int getCurrentZoom()
    {
        return this.gameControls.getZoom();
    }
    public int getCurrentSpeed()
    {
        return this.gameControls.getSpeed();
    }
    public void setCell(int x, int y, boolean status)
    {
        gameControls.setCell(x, y, status);
    }
    public boolean getCell(int x, int y)
    {
        return gameControls.getCellStatus(x, y);
    }




}
