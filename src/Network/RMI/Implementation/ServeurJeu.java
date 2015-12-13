/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI.Implementation;

import Database.Compte;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import model.Joueur;
import model.NumeroJoueur;
import model.Parametre;
import model.Partie;
import org.apache.log4j.Logger;

/**
 *
 * @author Anis
 */
public class ServeurJeu {

    private final Map<Long, ControlleurDistant> jeuEnCours;

    private long nbPartie;

    static ServeurJeu instance;

    private static final Logger logger = Logger.getLogger(ServeurJeu.class);

    public static ServeurJeu getInstance() {
        if (instance == null) {
            instance = new ServeurJeu();
        }
        return instance;
    }

    private ServeurJeu() {
        jeuEnCours = new HashMap<>();
        nbPartie = 0;

    }

    public long creerPartie(Parametre p, Joueur j1, Joueur j2) throws RemoteException {
        nbPartie++;
        jeuEnCours.put(nbPartie, new ControlleurDistant(new Partie(p, j1, j2)));
        logger.info(String.format("Partie créee : {\n\tparametres: %s,\n\tJ1: %s,\n\tJ2: %s }", p.toString(), j1, j2));
        return nbPartie;
    }

    public ControlleurDistant find(long idPartie) {
        return jeuEnCours.get(idPartie);
    }

    List<PartieItem> getListItem() {
       
        List<PartieItem> rep = new ArrayList<>();
        for (Long keySet : jeuEnCours.keySet()) {

            ControlleurDistant cd = jeuEnCours.get(keySet);
            rep.add(new PartieItem(keySet, cd.getNomJoueur(NumeroJoueur.J1), cd.getNomJoueur(NumeroJoueur.J2), cd.getParametres()));
        }
        return rep;
    }

    boolean exists(Compte J1, Compte J2) {
        for (ControlleurDistant value : jeuEnCours.values()) {
            if (value.VerifierJoueurs(J1, J2)) {
                return true;
            }
        }
        return false;
    }

    void remove(long partieID) {
        jeuEnCours.remove(partieID);
    }

}
