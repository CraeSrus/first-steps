import javax.swing.*;
import java.awt.*;

public class SnakeDriver
{
    static JFrame snakeWindow;
    static boolean death = false, escape = false;
    static int score;

    public static void main(String[] args)
    {
        // Step 1
        snakeWindow = new JFrame();
        snakeWindow.setUndecorated(true);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int screenWidth = gd.getDisplayMode().getWidth();
        int screenHeight = gd.getDisplayMode().getHeight();
        snakeWindow.setSize(screenWidth,screenHeight);

        // Step 2
        SnakePanel snack = new SnakePanel();

        // Step 3
        Container game = snakeWindow.getContentPane();

        // Step 4
        game.add(snack);

        snakeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Step 5
        snakeWindow.setVisible(true);

        while(true)
        {
            if(death == true)
            {
                onDeath();
                break;
            }
        }
    }

    protected static boolean epilepsyMode()
    {
        int epilepsyMode = JOptionPane.showConfirmDialog(snakeWindow, "Would you like a rainbow-colored snake?\nWARNING: Flashing Multicolored Lights\nMAY TRIGGER EPILEPTIC REACTION(?)", "Flashing Lights Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if(epilepsyMode == JOptionPane.YES_OPTION)
            return false;
        return true;
    }

    protected static void onDeath()
    {
        death = false;
        if(escape == true)
            JOptionPane.showMessageDialog(snakeWindow, ("You stopped the game!\nYour Score: " + score + ""), "Game Over!", JOptionPane.OK_OPTION);
        else
            JOptionPane.showMessageDialog(snakeWindow, ("You Died!\nYour Score: " + score + ""), "Game Over!", JOptionPane.OK_OPTION);
        int replay = JOptionPane.showConfirmDialog(snakeWindow, "Would you like to play again?", "Continue?", JOptionPane.YES_NO_OPTION);
        closeGame();
        if(replay == JOptionPane.YES_OPTION)
            main(null);
    }

    protected static void closeGame()
    {
        //         snakeWindow.dispatchEvent(new WindowEvent(snakeWindow, WindowEvent.WINDOW_CLOSING));
        snakeWindow.setVisible(false);
        snakeWindow.dispose();
    }

}