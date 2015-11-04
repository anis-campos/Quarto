/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.EnumMap;
import java.util.Map;

/**
 *
 * @author Anis
 */
public class MatriceDeTransition {

    private EnumMap< EtatGUI, EnumMap<EntreeGUI, EtatGUI>> matrice;

    private static MatriceDeTransition singleton;

    private MatriceDeTransition() {

        matrice = new EnumMap<>(EtatGUI.class);

        //Initialisation de la matrice avec etat non definit partout
        for (EtatGUI etatGUI : EtatGUI.values()) {

            EnumMap<EntreeGUI, EtatGUI> map = new EnumMap<>(EntreeGUI.class);
            for (EntreeGUI entreeGUI : EntreeGUI.values()) {
                map.put(entreeGUI, EtatGUI.EtatNonDefinit);

            }
            matrice.put(etatGUI, map);

        }

        //Ajout des transitions.
        matrice.get(EtatGUI.J1DoitDonner).put(EntreeGUI.DonnerJ1, EtatGUI.J2DoitPlacer);
        matrice.get(EtatGUI.J2DoitDonner).put(EntreeGUI.DonnerJ2, EtatGUI.J1DoitPlacer);
        matrice.get(EtatGUI.J1DoitDonner).put(EntreeGUI.J1AnnonceQuarto, EtatGUI.J1AAnnonceQuarto);
        matrice.get(EtatGUI.J2DoitDonner).put(EntreeGUI.J2AnnonceQuarto, EtatGUI.J2AAnnonceQuarto);

        matrice.get(EtatGUI.J1DoitPlacer).put(EntreeGUI.Plateau, EtatGUI.J1DoitChoisir);
        matrice.get(EtatGUI.J2DoitPlacer).put(EntreeGUI.Plateau, EtatGUI.J2DoitChoisir);
        matrice.get(EtatGUI.J1DoitPlacer).put(EntreeGUI.J1AnnonceQuarto, EtatGUI.J1AAnnonceQuarto);
        matrice.get(EtatGUI.J2DoitPlacer).put(EntreeGUI.J2AnnonceQuarto, EtatGUI.J2AAnnonceQuarto);

        matrice.get(EtatGUI.J1DoitChoisir).put(EntreeGUI.ListePiece, EtatGUI.J1DoitDonner);
        matrice.get(EtatGUI.J2DoitChoisir).put(EntreeGUI.ListePiece, EtatGUI.J2DoitDonner);
        matrice.get(EtatGUI.J1DoitChoisir).put(EntreeGUI.J1AnnonceQuarto, EtatGUI.J1AAnnonceQuarto);
        matrice.get(EtatGUI.J2DoitChoisir).put(EntreeGUI.J2AnnonceQuarto, EtatGUI.J2AAnnonceQuarto);
        matrice.get(EtatGUI.J1DoitChoisir).put(EntreeGUI.ListePieceVide, EtatGUI.J1DernierTour);
        matrice.get(EtatGUI.J2DoitChoisir).put(EntreeGUI.ListePieceVide, EtatGUI.J2DernierTour);

        matrice.get(EtatGUI.J1AAnnonceQuarto).put(EntreeGUI.Quarto, EtatGUI.J1ATrouveUnQuarto);
        matrice.get(EtatGUI.J1AAnnonceQuarto).put(EntreeGUI.PasQuarto, EtatGUI.J2ATrouveUnQuarto);
        matrice.get(EtatGUI.J2AAnnonceQuarto).put(EntreeGUI.Quarto, EtatGUI.J2ATrouveUnQuarto);
        matrice.get(EtatGUI.J2AAnnonceQuarto).put(EntreeGUI.PasQuarto, EtatGUI.J1ATrouveUnQuarto);

        matrice.get(EtatGUI.J2DernierTour).put(EntreeGUI.J2AnnonceMatchNull, EtatGUI.J1EtJ2OntAnnoncerMatchNull);
        matrice.get(EtatGUI.J1DernierTour).put(EntreeGUI.J1AnnonceMatchNUll, EtatGUI.J1EtJ2OntAnnoncerMatchNull);
        matrice.get(EtatGUI.J2DernierTour).put(EntreeGUI.J2AnnonceQuarto, EtatGUI.J2AAnnonceQuarto);
        matrice.get(EtatGUI.J1DernierTour).put(EntreeGUI.J1AnnonceQuarto, EtatGUI.J1AAnnonceQuarto);

    }

    //Obtention du singleton
    public static MatriceDeTransition getInstance() {
        if (singleton == null) {
            return singleton = new MatriceDeTransition();
        } else {
            return singleton;
        }
    }

    public EtatGUI getEtatSuivant(EtatGUI etatActuel, EntreeGUI entreeActive) {
        return matrice.get(etatActuel).get(entreeActive);
    }

    public String printMatice() {
        String rep = "ETAT_ACTUEL   ENTREE_ACTIVE   ETAT_SUIVANT\n";
        for (Map.Entry<EtatGUI, EnumMap<EntreeGUI, EtatGUI>> entrySet : matrice.entrySet()) {
            EtatGUI etatActuel = entrySet.getKey();
            EnumMap<EntreeGUI, EtatGUI> value = entrySet.getValue();
            if (etatActuel == EtatGUI.EtatNonDefinit) {
                continue;
            }
            for (Map.Entry<EntreeGUI, EtatGUI> entrySet1 : value.entrySet()) {
                EntreeGUI entreeActive = entrySet1.getKey();
                EtatGUI etatSuivant = entrySet1.getValue();
                if (etatSuivant == EtatGUI.EtatNonDefinit) {
                    continue;
                }
                rep += etatActuel.name() + " + " + entreeActive.name() + " ==> " + etatSuivant.name() + "\n";
            }

        }
        return rep;
    }

}
