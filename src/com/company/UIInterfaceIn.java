package com.company;

public interface UIInterfaceIn
{
    public int getZoom();
    public int getSpeed();
    public Grid getGrid();
    public int getGeneration();
    public void addUIListener(UIListener l);
    public void detachUI();
    public boolean isGameRunning();


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

    void saveStateButtonClick(String str);
    void deleteStateButtonClick();
    void loadStateButtonClick();
    String[] getSavedStates();
    Grid getState(String name);

}
