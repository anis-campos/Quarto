/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur.observables;

import model.Coord;
import view.EtatGUI;

/**
 *
 * @author Anis
 */
public abstract class Notification {

    
    
  
    
    public final EtatGUI etatSuivant;
    public final EtatGUI etatActuel;

    public Notification( EtatGUI etatSuivant, EtatGUI etatActuel) {
  
        this.etatSuivant = etatSuivant;
        this.etatActuel = etatActuel;
    }

  
        
}
