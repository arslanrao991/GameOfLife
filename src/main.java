<<<<<<< HEAD
import Console.main_frame;
import Factory.Factory;
=======
import Factory.Constants;
>>>>>>> b3dacd5fafebe7e647f090046505b4523ef1f007
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
<<<<<<< HEAD

        // For console
       // main_frame f =new main_frame();

        GameOfLifeFrame g = new GameOfLifeFrame(game);

        while(!game.grid.grid[2][2].isAlive())
        {
            game.uiController.updateGraphics(game.grid);
        }
        game.start();
        int a=5;
        for(int i=0;i<5;i++)
        {
            game.setZoom(a);
            a+=5;
            TimeUnit.MILLISECONDS.sleep(150);
            game.uiController.updateGraphics(game.grid);
        }
        a-=5;
        for(int i=0;i<5;i++)
        {
            game.setZoom(a);
            a-=5;
            TimeUnit.MILLISECONDS.sleep(150);
            game.uiController.updateGraphics(game.grid);
        }
        while(game.isGameRunning())
        {
            game.grid.next();
            TimeUnit.MILLISECONDS.sleep(150);
            game.uiController.updateGraphics(game.grid);
        }

=======
        GameOfLifeFrame g = new GameOfLifeFrame(game);

>>>>>>> b3dacd5fafebe7e647f090046505b4523ef1f007
    }
}
