//import Database.textDB;
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
        GameOfLifeFrame f = new GameOfLifeFrame(game);
        sqlDB db = new sqlDB(game);
        //textDB db = new textDB(game);

    }
}
