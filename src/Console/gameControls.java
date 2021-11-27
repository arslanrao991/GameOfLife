package Console;
import com.company.GameOfLife;
import com.company.UIInterfaceIn;

public class gameControls
{
    UIInterfaceIn gameControls;

    public gameControls(GameOfLife gc)
    {
        this.gameControls = gc;
        //this.gameControls.addUIListener(this);
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


}

