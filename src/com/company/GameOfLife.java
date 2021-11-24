package com.company;
import Factory.Constants;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class GameOfLife<audioStream> implements BLListener, BLListener_For_DB
{
    public Grid grid;
    public UIListener uiController = null;
    public DBListener dbListener = null;
    int counter;
    int zoom;
    int speed;
    boolean gameStatus;
//    File file = new File("C:\\Users\\myacc\\Data\\IdealProjects\\GameOfLife\\src\\com\\company\\gameOfLife.wav");
//    AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
//    Clip clip = AudioSystem.getClip();

    public  GameOfLife() throws LineUnavailableException, UnsupportedAudioFileException, IOException
    {
        this.grid = new Grid();
        this.speed = Constants.currentSpeed;
        this.zoom = Constants.currentZoom;
        this.counter = 0;
      //  clip.open(audioStream);
    }

    public void start()
    {
        this.gameStatus = true;
     //   clip.start();
    }
    public void stop()
    {
        this.gameStatus = false;
      //  clip.stop();
    }

    public void reset()
    {
        grid.reset();
        counter=0;
    }
    public boolean isGameRunning()
    {
        return this.gameStatus;
    }

    @Override
    public boolean getCellStatus(int x, int y)
    {
        return grid.getCellStatus(x, y);
    }

    @Override
    public void setCell(int x, int y, boolean status)
    {
        grid.setCell(x, y, status);
    }

    @Override
    public void next()
    {
        grid.next();
        counter++;
    }

    @Override
    public void clear()
    {
        grid.clear();
        counter=0;
    }

    @Override
    public void setGeneration()
    {
        counter=0;
    }

    public void setZoom(int value)
    {
        this.zoom=value;
    }
    public void setSpeed(int value)
    {
        this.speed=value;
    }
    public int getZoom()
    {
        return this.zoom;
    }
    public int getSpeed()
    {
        return this.speed;
    }

    //BLListener UI
    @Override
    public void startStopButtonClick()
    {
        if(isGameRunning())
            stop();
        else
        {
            start();
            grid.saveInitialShape();
        }
    }
    @Override
    public void nextButtonClick()
    {
        counter++;
        grid.next();
    }
    @Override
    public void resetButtonClicked()
    {
        if(isGameRunning())
        {
            reset();
        }
        else
            clear();
    }
    @Override
    public void zoomChanged(int value)
    {
        setZoom(value);
    }
    @Override
    public void saveStateButtonClick()
    {
        dbListener.saveState();
    }
    @Override
    public void deleteStateButtonClick()
    {
        dbListener.deleteState();
    }
    @Override
    public void loadStateButtonClick()
    {
        Hashtable h;
        h = dbListener.loadState();
    }
    @Override
    public void viewStateButtonClick()
    {
        Hashtable h;
        h = dbListener.viewState();
    }
    @Override
    public void speedChanged(int value)
    {
        setSpeed(value);
    }

    void updateState()
    {
        if(uiController != null)
            uiController.updateGraphics(grid);
    }


    public Grid getGrid()
    {
        return grid;
    }

    @Override
    public int getGeneration() {
        return counter;
    }

    public GameOfLife getGameOfLife()
    {
        return this;
    }
    public void addUIListener(UIListener l)
    {
        this.uiController = l;
    }
    public void detachUI()
    {
         this.uiController = null;
    }

    //BLListener_For_DB
    public void addDBListener(DBListener l)
    {
        this.dbListener = l;
    }
    public void detachDB()
    {
        this.dbListener = null;
    }

}
