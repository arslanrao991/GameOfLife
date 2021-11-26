//import Database.textDB;

import Console.main_frame;
import Constants.Constants;
import Database.sqlDB;
import UI.GameOfLifeFrame;
import com.company.GameOfLife;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class main
{
    public static void main(String[] args) throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException
    {
        Constants c = new Constants();
        GameOfLife game = new GameOfLife();
       // GameOfLifeFrame f = new GameOfLifeFrame(game);
       main_frame obj = new main_frame(game);
        sqlDB db = new sqlDB(game);
        //textDB db = new textDB(game);

    }
}
