// Not currently working/complete


import javax.swing.*;
import java.awt.*;
import java.awt.event.*; //need this for events
import java.util.*;

public class BeguiledPanel extends JPanel
{
    private Beguiler beguiler;
    private int bCount = 0, rb, gb, bb, r, g, b, rate, pWidth, pHeight, xRad, yRad, availX, availY, direction = 0;
    private Color color, bColor;
    private SnakeFood food;
    private boolean stop = false, eMode;

    public BeguiledPanel()
    {
        if(BeguiledDriver.playBeguiled() == false)
        {
            BeguiledDriver.closeGame();
        }
        
        color = randomBColor();
        setBackground(color);
        rate = 180;

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int screenWidth = gd.getDisplayMode().getWidth();
        int screenHeight = gd.getDisplayMode().getHeight();
        pWidth = screenWidth/2;
        pHeight = screenHeight/2;

        xRad = pWidth/5;
        yRad = pHeight/5;
        beguiler = new Beguiler(xRad, yRad, xRad+30, yRad+36);
        bCount ++;

        addKeyListener(new MyKeyListener());
        
        setFocusable(true);
        requestFocus();
    }

    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics); // super makes method move up one level in family tree

        getPanelWidth(this);
        getPanelHeight(this);

        beguiler.move(direction);
        beguiler.paint(graphics, this);
        direction = 0;

        //         if(sCount == 4)
        //         {
        //             for(int j = sCount; j > 0; j --)
        //             {
        //                 beguiler.get(j-1).draw(graphics);
        //             }
        //         }
        //         else
        //         {
        //             for(int j = 0; j < sCount; j ++)
        //             {
        //                 beguiler.get(j).draw(graphics);
        //             }
        //         }

        checkWallDeath(this);

        if(stop == false)
        {
            try
            {
                Thread.sleep(rate);
            }
            catch(InterruptedException slp)
            {

            }

            repaint(); //this is a way to re-call/loop the paintComponent method
        }
    }

    //writing an inner class for our listener
    public class MyKeyListener extends KeyAdapter
    {
        //overriding the keyPressed method from KeyAdapter
        public void keyPressed(KeyEvent e)
        {
            int key = e.getKeyCode();

            if(key == KeyEvent.VK_DOWN)
            {
                direction = 1;
            }
            if(key == KeyEvent.VK_UP)
            {
                direction = 2;
            }
            if(key == KeyEvent.VK_RIGHT)
            {
                direction = 3;
            }
            if(key == KeyEvent.VK_LEFT)
            {
                direction = 4;
            }
            if(key == KeyEvent.VK_ESCAPE)
            {
                stop = true;
                BeguiledDriver.end = true;
            }
        }
    }

    private void getPanelWidth(JPanel pane)
    {
        pWidth = pane.getWidth();
    }

    private void getPanelHeight(JPanel pane)
    {
        pHeight = pane.getHeight();
    }

    private void checkWallDeath(JPanel pane)
    {
        if(beguiler.getX() > pane.getWidth() ||
        beguiler.getX() < 0 ||
        beguiler.getY() > pane.getHeight() ||
        beguiler.getY() < 0)
        {
            death();
        }
    }

    private void death()
    {
        stop = true;
        //         BeguiledDriver.onDeath(sCount);
        BeguiledDriver.score = bCount-4;
        BeguiledDriver.death = true;
    }

//     private Color setBColor()
//     {
//         bColor = beguiler.get(sCount-1).getColor();
//         return bColor;
//     }

    private Color randomBColor()
    {
        rb = (int)(Math.random()*256);
        gb = (int)(Math.random()*256);
        bb = (int)(Math.random()*256);
        Color newBColor = new Color(rb,gb,bb);
        return newBColor;
    }

    private Color randomColor()
    {
        if(eMode == true)
        {
            r = 255-rb;
            g = 255-gb;
            b = 255-bb;
        }
        else
        {
            //         do {
            //             r = (int)(Math.random()*256);
            //         } while(Math.abs(r-rb) < 20);
            //         do {
            //             g = (int)(Math.random()*256);
            //         } while(Math.abs(g-gb) < 20);
            //         do {
            //             b = (int)(Math.random()*256);
            //         } while(Math.abs(b-bb) < 20);

            //         do {
            //             r = (int)(Math.random()*256);
            //             g = (int)(Math.random()*256);
            //             b = (int)(Math.random()*256);
            //         } while(Math.abs(r+g+b-rb+gb+bb) < 60);

            do {
                r = (int)(Math.random()*256);
                g = (int)(Math.random()*256);
                b = (int)(Math.random()*256);
            } while((Math.abs(r-rb)+Math.abs(g-gb)+Math.abs(b-bb))/3 < 20);
        }

        Color newColor = new Color(r,g,b);
        return newColor;
    }

}