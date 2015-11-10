/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur.observables;

import model.NumeroJoueur;
import model.EtatGUI;
import model.SortieGUI;

/**
 *
 * @author Anis
 */
public abstract class Notification {

    public final EtatGUI nouvelEtat;
    public final EtatGUI etatPrecedent;

    public final NumeroJoueur joueurSource;
    public final NumeroJoueur joueurAdversaire;
    public  final SortieGUI sortie;
    
    public Notification(NumeroJoueur joueurSource,EtatGUI nouvelEtat, EtatGUI etatPrecedent, SortieGUI sortie) {
  
        this.nouvelEtat = nouvelEtat;
        this.etatPrecedent = etatPrecedent;
        
        this.joueurSource = joueurSource;
        
        this.sortie = sortie;
        switch (joueurSource) {
                case J1:
                    joueurAdversaire = NumeroJoueur.J2;
                    break;
                case J2:
                    joueurAdversaire = NumeroJoueur.J1;             
                    break;  
                default:
                    joueurAdversaire = null;
            }
        
    }

  
        
}
