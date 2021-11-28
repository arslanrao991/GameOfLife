import Console.MainFrame;
import Database.MYSQLDB;
import Database.TextDB;
import Factory.Constants;
import UI.GameOfLifeFrame;
import com.BL.GameOfLife;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class main
{
    public static void main(String[] args) throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException
    {
        new Constants();
        GameOfLife game = new GameOfLife();

        new MYSQLDB(game);
        //new TextDB(game);

        new GameOfLifeFrame(game);
        //new MainFrame(game);
    }
}
