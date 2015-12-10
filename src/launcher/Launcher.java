/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import view.JPanelMenuPrincipal;
import view.fenetrePrincipale;

/**
 *
 * @author Anis
 */
public class Launcher {

    
    public static void main(String[] args) {

        JFrame frame = new fenetrePrincipale();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation(600, 10);
        frame.setResizable(true);
        
        JPanelMenuPrincipal menu = new JPanelMenuPrincipal();
        
        frame.setContentPane(menu);
             
        frame.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
        frame.setVisible(true);
        
    }
}
