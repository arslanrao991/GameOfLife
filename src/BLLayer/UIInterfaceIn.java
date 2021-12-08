//UI Layer use this interface to interact with BL
package BLLayer;

public interface UIInterfaceIn
{
    int getZoom();
    int getSpeed();
    Grid getGrid();
    int getGeneration();
    void addUIListener(UIListenerOut l);
    void detachUI();
    boolean isGameRunning();


    boolean getCellStatus(int x, int y);
    void setCell(int x, int y, boolean status);
    void next();
    void clear();
    void setGeneration();


    void startStopButtonClick();
    void nextButtonClick();
    void resetButtonClicked();

    void speedChanged(int value);
    void zoomChanged(int value);

    void saveStateButtonClick(String name);
    void deleteStateButtonClick(String name);
    void loadStateButtonClick(String name);
    String[] getSavedStates();
    Grid getState(String name);

}
