
import javax.swing.*;
import java.awt.*;
import java.awt.event.*; //need this for events
import java.util.*;

public class SnakePanel extends JPanel
{
    private ArrayList<Snake> snake = new ArrayList<Snake>();
    private int sCount = 0, rb, gb, bb, r, g, b, rate, pWidth, pHeight, rad, availX, availY, direction;
    private Color color, sColor;
    private SnakeFood food;
    private boolean stop = false, eMode;

    public SnakePanel()
    {
        color = randomBColor();
        setBackground(color);
        rate = 105;

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int screenWidth = gd.getDisplayMode().getWidth();
        int screenHeight = gd.getDisplayMode().getHeight();
        pWidth = screenWidth/2;
        pHeight = screenHeight/2;

        eMode = SnakeDriver.epilepsyMode();

        rad = 25;
        for(int i = 0; i < 4; i ++)
        {
            snake.add(new Snake(rad, pWidth, pHeight, randomColor()));
            sCount ++;
        }
        updateFood();

        setFocusable(true);
        requestFocus();

        addKeyListener(new MyKeyListener());
    }

    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics); // super makes method move up one level in family tree
        graphics.setFont(new Font("Newslab Bold", Font.BOLD, 30));
        graphics.setColor(new Color(255-rb, 255-gb, 255-bb));
        getPanelWidth(this);
        getPanelHeight(this);
        
        graphics.drawString("Score: " + (sCount-4) + "", 25, 50);

        if(sCount > 0)
        {
            updateDirection();
            snake.get(0).move();
            snake.get(0).setXSpd(snake.get(0).getXSpd());
            snake.get(0).setYSpd(snake.get(0).getYSpd());
        }
        checkFood();
        food.draw(graphics);
        for(int i = 1; i < sCount; i ++)
        {
            snake.get(i).move();
            snake.get(i).setXSpd(snake.get(i-1).getLastXSpd());
            snake.get(i).setYSpd(snake.get(i-1).getLastYSpd());
        }

        for(int j = sCount; j > 0; j --)
        {
            snake.get(j-1).draw(graphics);
        }

        //         if(sCount == 4)
        //         {
        //             for(int j = sCount; j > 0; j --)
        //             {
        //                 snake.get(j-1).draw(graphics);
        //             }
        //         }
        //         else
        //         {
        //             for(int j = 0; j < sCount; j ++)
        //             {
        //                 snake.get(j).draw(graphics);
        //             }
        //         }

        checkWallDeath(this);
        if(sCount > 4)
        {
            checkSelfDeath();
        }

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

            if(key == KeyEvent.VK_LEFT && snake.get(0).getXSpd() <= 0)
            {
                direction = 1;
            }
            if(key == KeyEvent.VK_UP && snake.get(0).getYSpd() <= 0)
            {
                direction = 2;
            }
            if(key == KeyEvent.VK_RIGHT && snake.get(0).getXSpd() >= 0)
            {
                direction = 3;
            }
            if(key == KeyEvent.VK_DOWN && snake.get(0).getYSpd() >= 0)
            {
                direction = 4;
            }
            if(key == KeyEvent.VK_ESCAPE)
            {
                SnakeDriver.escape = true;
                death();
            }
        }
    }

    private void updateDirection()
    {
        if(direction == 1)
        {
            snake.get(0).setXSpd(0-snake.get(0).getSpd());
            snake.get(0).setYSpd(0);
        }
        else
        if(direction == 2)
        {
            snake.get(0).setYSpd(0-snake.get(0).getSpd());
            snake.get(0).setXSpd(0);
        }
        else
        if(direction == 3)
        {
            snake.get(0).setXSpd(snake.get(0).getSpd());
            snake.get(0).setYSpd(0);
        }
        else
        if(direction == 4)
        {
            snake.get(0).setYSpd(snake.get(0).getSpd());
            snake.get(0).setXSpd(0);
        }
    }

    private void updateFood()
    {
        int foodRad = 15;
        checkAvailableXY(foodRad);
        food = new SnakeFood(foodRad, availX, availY, setSColor());
    }

    private void checkAvailableXY(int foodRad)
    {
        for(int i = 0; i < sCount; i ++)
        {
            do {
                availX = (int)((Math.random()*(pWidth-foodRad*2))+foodRad);
                availY = (int)((Math.random()*(pHeight-foodRad*2))+foodRad);
            } while(availX+foodRad > snake.get(i).getX()-rad &&
            availX-foodRad < snake.get(i).getX()+rad &&
            availY+foodRad > snake.get(i).getY()-rad &&
            availY-foodRad < snake.get(i).getY()+rad);
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
        if(snake.get(0).getX() > pane.getWidth() ||
        snake.get(0).getX() < 0 ||
        snake.get(0).getY() > pane.getHeight() ||
        snake.get(0).getY() < 0)
        {
            death();
        }
    }

    private void checkSelfDeath()
    {
        for(int i = 1; i < sCount; i ++)
        {
            if(snake.get(0).getX() == snake.get(i).getX() &&
            snake.get(0).getY() == snake.get(i).getY())
            {
                death();
            }
        }
    }

    private void checkFood()
    {
        if(food.getX()+food.getRad() > snake.get(0).getX()-rad &&
        food.getX()-food.getRad() < snake.get(0).getX()+rad &&
        food.getY()+food.getRad() > snake.get(0).getY()-rad &&
        food.getY()-food.getRad() < snake.get(0).getY()+rad)
        {
            //             stop = true;
            snake.add(new Snake(rad , snake.get(sCount-1).getX() , snake.get(sCount-1).getY() , 0 , 0 , randomColor()));
            sCount ++;
            updateFood();
        }
    }

    private void death()
    {
        stop = true;
        //         SnakeDriver.onDeath(sCount);
        SnakeDriver.score = sCount-4;
        SnakeDriver.death = true;
    }

    private Color setSColor()
    {
        sColor = snake.get(sCount-1).getColor();
        return sColor;
    }

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