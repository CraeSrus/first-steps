// Not currently working/complete


import javax.swing.*;
import java.awt.*;

public class BeguiledDriver
{
    static JFrame beguiledWindow;
    static boolean death = false, end = false;
    static int score;

    public static void main(String[] args)
    {
        // Step 1
        beguiledWindow = new JFrame();
        beguiledWindow.setUndecorated(true);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int screenWidth = gd.getDisplayMode().getWidth();
        int screenHeight = gd.getDisplayMode().getHeight();
        beguiledWindow.setSize(screenWidth,screenHeight);

        // Step 2
        BeguiledPanel beguile = new BeguiledPanel();

        // Step 3
        Container game = beguiledWindow.getContentPane();

        // Step 4
        game.add(beguile);

        beguiledWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Step 5
        beguiledWindow.setVisible(true);

        while(true)
        {
            if(death == true)
            {
                onDeath();
                break;
            }
            if(end == true)
            {
                closeGame();
                break;
            }
        }
    }

    protected static boolean playBeguiled()
    {
        int startGame = JOptionPane.showConfirmDialog(beguiledWindow, "Would you like to play beguiled?", "Beguiled", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if(startGame == JOptionPane.YES_OPTION)
            return true;
        return false;
    }

    protected static void onDeath()
    {
        death = false;
        JOptionPane.showMessageDialog(beguiledWindow, ("You Died!\nYour Score: " + score + ""), "Game Over!", JOptionPane.OK_OPTION);
        int replay = JOptionPane.showConfirmDialog(beguiledWindow, "Would you like to play again?", "Continue?", JOptionPane.YES_NO_OPTION);
        closeGame();
        if(replay == JOptionPane.YES_OPTION)
            main(null);
    }

    protected static void closeGame()
    {
        //         beguiledWindow.dispatchEvent(new WindowEvent(beguiledWindow, WindowEvent.WINDOW_CLOSING));
        beguiledWindow.setVisible(false);
        beguiledWindow.dispose();
    }

}