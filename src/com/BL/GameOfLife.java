package com.BL;
import Factory.Constants;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

public class GameOfLife<audioStream> implements UIInterfaceIn, DBInterfaceIn
{
    protected Grid grid;
    protected UIListenerOut uiController = null;
    protected DBInterfaceOut dbListener = null;
    protected int zoom;
    protected int speed;
    protected boolean gameStatus;
    File file = new File("C:\\Users\\myacc\\Data\\IdealProjects\\GameOfLife\\src\\com\\BL\\pinkPanther.wav");
    AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
    Clip clip = AudioSystem.getClip();

    public  GameOfLife() throws LineUnavailableException, UnsupportedAudioFileException, IOException
    {
        this.grid = new Grid();
        this.speed = Constants.currentSpeed;
        this.zoom = Constants.currentZoom;
        this.grid.generation = 0;
        clip.open(audioStream);

    }

    public void start()
    {
        this.gameStatus = true;
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop()
    {
        this.gameStatus = false;
        clip.stop();
    }

    public void reset()
    {
        grid.reset();
        this.grid.generation=0;
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
    }

    @Override
    public void clear()
    {
        grid.clear();
        this.grid.generation=0;
    }
    @Override
    public void setGeneration()
    {
        this.grid.generation=0;
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
    @Override
    public void nextButtonClick()
    {
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
    synchronized public void saveStateButtonClick(String name)
    {
        dbListener.saveState(this.grid.currentShape, name);
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
        return this.grid.generation;
    }

    public void addUIListener(UIListenerOut l)
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
