import javax.swing.*;
import java.awt.*;

public class Snake
{
    private int rad, x, y, spd, xSpd, ySpd, lastXSpd, lastYSpd, r, g, b;
    private Color colour;

    public Snake(int radius, int availX, int availY, Color sColor)
    {
        rad = radius;
        x = availX;
        y = availY;
        spd = radius*2;
        xSpd = 0;
        ySpd = 0;
        lastXSpd = 0;
        lastYSpd = 0;
        colour = sColor;
    }

    public Snake(int radius, int availX, int availY, int currXSpd, int currYSpd, Color sColor)
    {
        rad = radius;
        x = availX;
        y = availY;
        spd = radius*2;
        xSpd = 0;
        ySpd = 0;
        setXSpd(currXSpd);
        setYSpd(currYSpd);
        colour = sColor;
    }

    public void draw(Graphics graphics)
    {
        graphics.setColor(colour);
        graphics.fillRect((x-rad),(y-rad),rad*2,rad*2);
    }

    public void move()
    {
        x += xSpd;
        y += ySpd;
    }

    public int getRad()
    {
        return rad;
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

    public int getXSpd()
    {
        return xSpd;
    }

    public void setXSpd(int newXSpd)
    {
        setLastXSpd(xSpd);
        xSpd = newXSpd;
    }

    public int getYSpd()
    {
        return ySpd;
    }

    public void setYSpd(int newYSpd)
    {
        setLastYSpd(ySpd);
        ySpd = newYSpd;
    }

    public int getSpd()
    {
        return spd;
    }

    protected int getLastXSpd()
    {
        return lastXSpd;
    }

    protected int getLastYSpd()
    {
        return lastYSpd;
    }

    protected Color getColor()
    {
        return colour;
    }

    private void setLastXSpd(int newLastXSpd)
    {
        lastXSpd = newLastXSpd;
    }

    private void setLastYSpd(int newLastYSpd)
    {
        lastYSpd = newLastYSpd;
    }
}