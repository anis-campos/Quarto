/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.AWTException;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.JPanel;

/**
 *
 * @author Anis
 */
public class PanelGeneral extends JPanel {

    JPanelMenuLocal Menu;

    JPanelQuarto Jeu;

    JPanel Parametres;
    private final CardLayout cardLayout;

    public PanelGeneral() {
        cardLayout = new CardLayout();
        cardLayout.setHgap(0);
        cardLayout.setVgap(0);
        Menu = new JPanelMenuLocal();
        Parametres = new JPanelParametresLocal();

        this.setLayout(cardLayout);
        this.setVisible(true);
        this.setEnabled(true);
        this.setName("generalPanel");
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setAlignmentY(Component.CENTER_ALIGNMENT);

        Menu.setVisible(true);
        Menu.setEnabled(true);
        Menu.setName("menu");
        Parametres.setVisible(true);
        Parametres.setEnabled(true);
        Parametres.setName("parametres");

        this.add("menu", Menu);
        this.add("parametres", Parametres);

        cardLayout.show(this, "menu");
    }

    public void show(String name) {
        this.cardLayout.show(this, name);
    }

    public void setJeu(JPanelQuarto jeu) {
        this.Jeu = jeu;
        this.add("jeu", jeu);
    }

    public void removeJeu() {
        try {
            this.remove(Jeu);
            this.cardLayout.removeLayoutComponent(Jeu);
        } catch (Exception e) {
        }

    }

    public void ToggleContinuerPartie(Boolean isEnable) {
        Menu.ToggleContinuerPartie(isEnable);
    }
    
    
    public void testDernierTour() throws AWTException{
        Menu.test();
        Jeu.testDernierTour();
    }

    public void testQuarto() throws AWTException {
        Menu.test();
        Jeu.testQuarto();
    }
    

}
