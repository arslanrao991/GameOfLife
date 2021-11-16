import Database.Database;
import Factory.Factory;
import UI.GameOfLifeControls;
import UI.GameOfLifeFrame;
import com.company.GameOfLife;

import java.util.concurrent.TimeUnit;

public class main
{
    public static void main(String[] args) throws InterruptedException {
        Factory f = new Factory("ui");
        GameOfLife game = new GameOfLife();
        game.initGame();

        GameOfLifeFrame g = new GameOfLifeFrame();
        g.Frame();

        while(!game.grid.grid[2][2].isAlive())
        {
            game.uiControler.updateGraphics(game.grid);
        }
        game.start();

        while(game.isGameRunning())
        {
            game.grid.next();
            TimeUnit.MILLISECONDS.sleep(100);
            game.uiControler.updateGraphics(game.grid);

        }


        /*for(int i=0;i<5;i++)
        {
            game.grid.setCell(i, i, true);
            game.uiControler.updateGraphics(game.grid);
            TimeUnit.SECONDS.sleep(1);
        }*/



//        System.out.println(con);

        /*Cell c = new Cell(1, 1);
        System.out.print(c);

        Hashtable a=new Hashtable();
        a.put(c, c);
        Enumeration<Cell> e;
        e = a.keys();

        while(e.hasMoreElements())
        {
            System.out.print((Cell) e.nextElement());
        }



        GameOfLife game = new GameOfLife();
        game.initGame();*/

        /*Scanner myInput = new Scanner( System.in );
        Grid grid = new Grid();

        grid.grid[5][5].setCellStatus(true);
        grid.updateNeighbours(grid.grid[1][1]);
        grid.print();
        int a;
        System.out.print( "Enter first integer: " );
        //a = myInput.nextInt();

        for(int i=0;i<10;i++)
        {
            for (int j = 0; j < 10; j++)
            {
                grid.updateNeighbours(grid.grid[i][j]);
            }
        }
        grid.print();*/
    }

}