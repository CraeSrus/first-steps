import javax.swing.*;
import java.awt.*;

public class SnakeFood
{
    private int rad, x, y;
    private Color color;

    public SnakeFood(int radius, int availX, int availY, Color sColor)
    {
        rad = radius;
        x = availX;
        y = availY;
        color = sColor;
    }

    public void draw(Graphics graphics)
    {
        graphics.setColor(color);
        graphics.fillOval((x-rad),(y-rad),rad*2,rad*2);
    }

    public int getRad()
    {
        return rad;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
}