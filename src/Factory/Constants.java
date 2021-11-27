package Factory;

import UI.Board;
import UI.GameOfLifeControls;
import com.company.Grid;

public class Constants
{

    public static int minSpeed = 1260;
    public static int maxSpeed = 180;
    public static int currentSpeed = 720;
    public static int currentZoom = 20;
    public static int maxZoomOut = 5;
    public static int maxZoomIn = 35;
    public static int gridResolutionWidth = 1380;
    public static int getGridResolutionHeight = 774;
    public static int gridRows = getGridResolutionHeight/maxZoomOut;
    public static int gridCols = gridResolutionWidth/maxZoomOut;
    public static GameOfLifeControls controller = null;

}
