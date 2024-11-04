
package rendering;

import entities.RigidBody;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class Renderer extends JPanel {
    
    public List<RigidBody> bodies;
    
    public Renderer(){
        this.bodies = new ArrayList();
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        setDoubleBuffered(true); //Allegedly better for performance
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        //Antialiasing to ease the edges
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        for (RigidBody body : bodies) {
            body.draw(g2d);
        }
        
        g2d.dispose();
    }
    
    public void render(List<RigidBody> bodies) {
        repaint();
    }

    
}
