/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.EnumMap;

/**
 *
 * @author Anis
 */
public class MatriceDeSortie {

    private EnumMap< EtatGUI, SortieGUI> matrice;

    private static MatriceDeSortie singleton;

    //Obtention du singleton
    public static MatriceDeSortie getInstance() {
        if (singleton == null) {
            return singleton = new MatriceDeSortie();
        } else {
            return singleton;
        }
    }

    private MatriceDeSortie() {

        matrice = new EnumMap<>(EtatGUI.class);
        //Initialisation de la matrice avec etat non definit partout
        for (EtatGUI etatGUI : EtatGUI.values()) {
            for (SortieGUI entreeGUI : SortieGUI.values()) {
                matrice.put(etatGUI, SortieGUI.PartieEnCours);
            }
        }
        
        matrice.put(EtatGUI.J1ATrouveUnQuarto, SortieGUI.J1AGagne);
        matrice.put(EtatGUI.J2ATrouveUnQuarto, SortieGUI.J2AGagne);
        matrice.put(EtatGUI.J1EtJ2OntAnnoncerMatchNull, SortieGUI.MatchNull);
       
    }

    public SortieGUI getEtatSortie(EtatGUI etatActuel) {
        return matrice.get(etatActuel);
    }

}
