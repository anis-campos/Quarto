/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI;

import java.io.Serializable;

/**
 *
 * @author Anis
 */
public class PartieItem implements Serializable {

    public final long ID;

    public final String NomJoueur1;

    public final String NomJoueur2;

    public final String Parametres;

    public PartieItem(long ID, String NomJoueur1, String NomJoueur2, String Parametres) {
        this.ID = ID;
        this.NomJoueur1 = NomJoueur1;
        this.NomJoueur2 = NomJoueur2;
        this.Parametres = Parametres;
    }

}
