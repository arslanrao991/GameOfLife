//import Database.textDB;
import Console.mainFrame;
import Database.TextDB;
import Factory.Constants;
import UI.GameOfLifeFrame;
import com.company.GameOfLife;
import Database.sqlDB;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Scanner;

public class main
{
    public static void main(String[] args) throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException
    {
        Constants c = new Constants();
        GameOfLife game = new GameOfLife();
        //TextDB db = new TextDB(game);
        sqlDB db = new sqlDB(game);
        //mainFrame console = new mainFrame(game);Add Text DB and Interaction with BL (+DB Testing)
        GameOfLifeFrame f = new GameOfLifeFrame(game);


    }
}
