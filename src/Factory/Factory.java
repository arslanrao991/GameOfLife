package Factory;

import UI.Board;
import UI.GameOfLifeControls;
import com.company.Grid;

public class Factory
{

    public static int increaseNeighbour=1;
    public static int decreaseNeighbour=0;
    public static int defaultSpeed = 20;
    public static int defaultZoom = 20;
    public static int gridRows = 30;
    public static int gridCols = 53;
    public static GameOfLifeControls controler;
    public static Grid grid = new Grid();

    public Factory(String type)
    {

        if(type.equals("ui"))
        {
            controler = new GameOfLifeControls();
        }
    }
    public static Grid getGrid()
    {
        return grid;
    }
    public static GameOfLifeControls getControler()
    {
        return controler;
    }


}
