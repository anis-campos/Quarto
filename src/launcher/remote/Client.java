/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher.remote;


import java.awt.Dimension;
import java.awt.BorderLayout;

/**
 *
 * @author Anis
 */
public class Client {

    public static void main(String[] args) {

        MenuReseau frame = new MenuReseau();
        TestPane pane = new TestPane();
        frame.getContentPane().add(pane,BorderLayout.CENTER);
        
        frame.setSize(new Dimension(600, 600));
        
        //frame.pack();
        
        frame.setVisible(true);
        
    }

}
