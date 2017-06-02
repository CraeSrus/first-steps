// Not currently working/complete


import javax.swing.*;
import java.awt.*;

public class Beguiler
{
    private int xRad, yRad, x, y, colourVal, r, g, b;
    private Color colour;
    private static ImageIcon PLAYER_1 = new ImageIcon("Galaga_Player_Ship.jpg");
    private static ImageIcon PLAYER_2 = new ImageIcon("Galaga_Player_Ship.jpg");
    private static ImageIcon PLAYER_3 = new ImageIcon("Galaga_Player_Ship.jpg");
    private static ImageIcon PLAYER_4 = new ImageIcon("Galaga_Player_Ship.jpg");
    private ImageIcon player;


    public Beguiler(int xRadius, int yRadius, int repX, int repY)
    {
        xRad = xRadius;
        yRad = yRadius;
        x = repX;
        y = repY;
        player = PLAYER_1;
    }

    public void paint(Graphics g, JPanel bP)
    {
        player.paintIcon(bP, g, x, y);
    }
    
    public void move(int direction)
    {
        if(direction == 1)
        {
            y += 25;
            player = PLAYER_1;
        }
        else
        if(direction == 2)
        {
            y -= 25;
            player = PLAYER_2;
        }
        else
        if(direction == 3)
        {
            x += 25;
            player = PLAYER_3;
        }
        else
        if(direction == 4)
        {
            x -= 25;
            player = PLAYER_4;
        }
    }

    public int getXRad()
    {
        return xRad;
    }
    
    public int getYRad()
    {
        return yRad;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int newX)
    {
        x = newX;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int newY)
    {
        y = newY;
    }
    
    public ImageIcon getPlayer()
    {
        return player;
    }

    protected Color getColor()
    {
        return colour;
    }

}