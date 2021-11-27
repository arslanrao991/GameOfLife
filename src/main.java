import Console.MainFrame;
import Database.TextDB;
import Factory.Constants;
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

        TextDB db = new TextDB(game);
        //MYSQLDB db = new MYSQLDB(game);

        MainFrame console = new MainFrame(game);
        //GameOfLifeFrame f = new GameOfLifeFrame(game);
    }
}
