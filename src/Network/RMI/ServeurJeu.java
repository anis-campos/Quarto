/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI;

import com.sun.jmx.remote.internal.ArrayQueue;
import controlleur.IControlleurDistant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Joueur;
import model.NumeroJoueur;
import model.Parametre;
import model.Partie;

/**
 *
 * @author Anis
 */
public class ServeurJeu {

    private final Map<Long, ControlleurDistant> jeuEnCours;

    private long nbPartie;

    static ServeurJeu instance;

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

    public long creerPartie(Parametre p, Joueur j1, Joueur j2) {
        nbPartie++;
        jeuEnCours.put(nbPartie, new ControlleurDistant(new Partie(p, j1, j2)));
        return nbPartie;
    }
    
    public ControlleurDistant find(long idPartie){
        return jeuEnCours.get(idPartie);
    }

    List<PartieItem> getListItem() {
        List<PartieItem> rep = new ArrayList<>();
        for (Long keySet : jeuEnCours.keySet()) {
            System.out.println(keySet);
            ControlleurDistant cd = jeuEnCours.get(keySet);
            rep.add(new PartieItem(keySet, cd.getNomJoueur(NumeroJoueur.J1), cd.getNomJoueur(NumeroJoueur.J2), "Ajouter une methode dans le controlleur pour afficher les paramteres"));
        }
        return rep;
    }

}
