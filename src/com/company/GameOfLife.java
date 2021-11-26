package com.company;
import Constants.Constants;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

public class GameOfLife<audioStream> implements UIInterfaceIn, DBInterfaceIn
{
    public Grid grid;
    public UIListener uiController = null;
    public DBInterfaceOut dbListener = null;
    int counter;
    int zoom;
    int speed;
    boolean gameStatus;
    String s= new String("State 1");
    File file = new File("C:\\Users\\myacc\\Data\\IdealProjects\\GameOfLife\\src\\com\\company\\pinkPanther.wav");
    AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
    Clip clip = AudioSystem.getClip();

    public  GameOfLife() throws LineUnavailableException, UnsupportedAudioFileException, IOException
    {
        this.grid = new Grid();
        this.speed = Constants.currentSpeed;
        this.zoom = Constants.currentZoom;
        this.counter = 0;
        clip.open(audioStream);
    }

    public void start()
    {
        this.gameStatus = true;
        clip.start();
    }
    public void stop()
    {
        this.gameStatus = false;
        clip.stop();
    }

    public void reset()
    {
        grid.reset();
        counter=0;
    }

    //BLListener UI
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
//    @Override
//    public void onlyStopButtonClick()
//    {
//            stop();
//    }
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
    synchronized public void saveStateButtonClick(String str)
    {
        dbListener.saveState(this.grid.currentShape, str);
        s ="State 2";
    }
    @Override
    synchronized public void deleteStateButtonClick()
    {
        dbListener.deleteRecentState();
    }
    @Override
    synchronized public void loadStateButtonClick()
    {
        Cell c;
        Hashtable h;
        h = dbListener.loadRecentState();
        grid.clear();
        Enumeration enumerate = h.keys();
        while(enumerate.hasMoreElements())
        {
            c = (Cell) enumerate.nextElement();
            this.grid.setCell(c.x_axis, c.y_axis, true);
        }
    }

    @Override
    public String[] getSavedStates()
    {
        return dbListener.getStates();
    }

    @Override
    public Grid getState(String name)
    {
        Cell c;
        Hashtable h;
        h = dbListener.loadState(name);
        Grid g = new Grid();

        Enumeration enumerate = h.keys();
        while(enumerate.hasMoreElements())
        {
            c = (Cell) enumerate.nextElement();
            g.setCell(c.x_axis, c.y_axis, true);
        }
        return g;
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
    public void addDBListener(DBInterfaceOut l)
    {
        this.dbListener = l;
    }
    public void detachDB()
    {
        this.dbListener = null;
    }

}
