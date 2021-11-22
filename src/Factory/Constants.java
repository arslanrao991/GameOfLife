package Factory;

import UI.Board;
import UI.GameOfLifeControls;
import com.company.Grid;

public class Constants
{

    public static int minSpeed = 1000;
    public static int maxSpeed = 130;
    public static int currentSpeed = 400;
    public static int currentZoom = 10;
    public static int maxZoomOut = 5;
    public static int maxZoomIn = 30;
    public static int gridResolutionWidth = 5280;
    public static int getGridResolutionHeight = 5674;
    public static int gridRows = getGridResolutionHeight/maxZoomOut;
    public static int gridCols = gridResolutionWidth/maxZoomOut;
    public static GameOfLifeControls controller = null;

}
