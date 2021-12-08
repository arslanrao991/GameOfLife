import Database.MYSQLDB;
import Factory.Constants;
import BLLayer.GameOfLife;
import JavaSwing.GameOfLifeFrame;

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
        //new CLIInterface(game);
    }
}
