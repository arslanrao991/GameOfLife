package Factory;

import UI.Board;
import UI.GameOfLifeControls;
import com.company.Grid;

public class Factory
{

    public static int increaseNeighbour=1;
    public static int decreaseNeighbour=0;
    public static int defaultSpeed = 5;
    public static int currentZoom = 10;
    public static int maxZoomOut = 5;
    public static int maxZoomIn = 30;
    public static int gridResolutionWidth = 1280;
    public static int getGridResolutionHeight = 650;
    public static int gridRows = getGridResolutionHeight/maxZoomOut;
    public static int gridCols = gridResolutionWidth/maxZoomOut;
    public static GameOfLifeControls controller = null;
    //public static Grid grid = new Grid();

    public Factory(String type)
    {
        /*if(type.equals("ui"))
        {
            controller = new GameOfLifeControls();
        }*/
    }
    /*public static Grid getGrid()
    {
        return grid;
    }*/
    public static GameOfLifeControls getController()
    {
        return controller;
    }

}
