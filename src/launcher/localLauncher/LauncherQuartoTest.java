/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher.localLauncher;

import java.awt.AWTException;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import view.JPanelMenu;
import view.JPanelParametres;
import view.JPanelQuarto;
import view.fenetrePrincipale;

/**
 *
 * @author Anis
 */
public class LauncherQuartoTest {

    public static void main(String[] args) {

        LauncherQuarto2.main(args);

        testGUI(LauncherQuarto2.generalPanel);

    }

    public static void testGUI(JPanel contentPane) {
        CardLayout cl = (CardLayout) contentPane.getLayout();
        JPanel menu = (JPanel) contentPane.getComponent(0);
        JButton commencerPartie = (JButton) menu.getComponent(0);
        commencerPartie.doClick();
        JPanelQuarto jeu = (JPanelQuarto) contentPane.getComponent(2);

        try {
            //jeu.testQuarto();
            jeu.testDernierTour();
        } catch (AWTException ex) {
            Logger.getLogger(LauncherQuartoTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
