/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.local;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author Anis
 */
public class GUIResolutionTool {
    
    public static Dimension getSizeOfCase(){
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        
        int taille = (int) (dim.height*0.1f);
        
        return new Dimension(taille,taille);
        
    }
    
}
