package game.ui;


import game.Game;
import java.awt.Dimension;
import java.awt.Canvas;
import javax.swing.JFrame;

public class Window extends Canvas {

    public Window(int witdh, int height, String title, Game game) {
        JFrame frame = new JFrame(title);
        
        frame.setPreferredSize(new Dimension(witdh, height));
        
        frame.setMaximumSize(new Dimension(witdh, height));
        
        frame.setMinimumSize(new Dimension(witdh, height));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
       
        
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();
        
        

    }

}
