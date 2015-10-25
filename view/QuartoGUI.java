/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Coord;

/**
 *
 * @author Anis
 */
public class QuartoGUI extends JFrame implements MouseListener, MouseMotionListener, Observer{

    
    private Map<JPanel,Coord> cases;
    
    private Map<JLabel,String> pieces;
    
    private JPanel Plateau;

    public QuartoGUI() {
       super();
       initComponents();
        
    }
    
    
    
    void initComponents(){
        
        Plateau = new JPanel();
        
        
        GridLayout grid = new GridLayout(4,4);
        grid.setHgap(5);
        grid.setVgap(5);
        Plateau.setLayout(grid);
        
        cases = new HashMap<>();
        
        for (int i = 0; i < 16; i++) {
            JPanel newJpanel = new JPanel();
            newJpanel.setBackground(Color.getHSBColor(i*10, i*10, i*10));
            cases.put(newJpanel, new Coord((int)(i-1)/4, (i-1)%4));
        }
        
        this.add(Plateau);
        
        
 
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
