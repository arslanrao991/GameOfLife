import Factory.Constants;
import UI.GameOfLifeFrame;
import com.company.GameOfLife;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Scanner;

public class main
{
    public static void main(String[] args) throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException
    {
        Constants f = new Constants();
        GameOfLife game = new GameOfLife();
        GameOfLifeFrame g = new GameOfLifeFrame(game);

    }
}
