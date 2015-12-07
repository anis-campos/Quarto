/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Anis
 */
public class GUIImageTool {
    
    
    public static Image getImage(String reltivePath){
        try {
            return ImageIO.read(GUIImageTool.class.getResourceAsStream(reltivePath));
        } catch (IOException ex) {
            Logger.getLogger(GUIImageTool.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
