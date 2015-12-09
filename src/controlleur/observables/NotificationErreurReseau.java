/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur.observables;

import model.EtatGUI;
import model.NumeroJoueur;

/**
 *
 * @author Anis
 */
public class NotificationErreurReseau extends Notification{
  
    public final Exception exception;

    public NotificationErreurReseau(Exception exception) {
        super(NumeroJoueur.J1, EtatGUI.EtatNonDefinit, EtatGUI.EtatNonDefinit);
        this.exception = exception;
    }
    
    
    
}
