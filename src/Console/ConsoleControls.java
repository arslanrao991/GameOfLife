package Console;
import com.BL.GameOfLife;
import com.BL.UIInterfaceIn;

public class ConsoleControls
{
    UIInterfaceIn gameControls;

    public ConsoleControls(GameOfLife gc)
    {
        this.gameControls = gc;
    }

    //UIInterfaceIN
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

