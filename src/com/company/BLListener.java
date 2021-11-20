package com.company;

import Factory.Factory;

public interface BLListener
{
    public boolean isGameRunning();

    public int getZoom();
    public int getSpeed();
    public Grid getGrid();
    public int getGeneration();
    public void addUIListener(UIListener l);
    public void detachUI();


    void startStopButtonClick();
    void nextButtonClick();
    void resetButtonClicked();

    void speedChanged(int value);
    void zoomChanged(int value);

    void saveStateButtonClick();
    void deleteStateButtonClick();
    void loadStateButtonClick();
    void viewStateButtonClick();

}
