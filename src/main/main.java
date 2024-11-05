
package main;

import Scenes.Scenes;
import core.engine.GameLoop;
import javax.swing.JFrame;
import core.engine.Renderer;

public class main {
    public static void main(String[] args) {
        
        JFrame frame = new JFrame("2D Collision Engine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        //frame.setLocationRelativeTo(null); //Center the frame
        
        Renderer renderer = new Renderer();
        frame.add(renderer);
        frame.pack(); //Adjust to renderer's desired size
        frame.setVisible(true);
        
        GameLoop gameloop = new GameLoop(Scenes.Scene1(), renderer);
        gameloop.startMainThread();
        
    }
}
