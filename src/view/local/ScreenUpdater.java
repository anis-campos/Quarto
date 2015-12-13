/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.local;

import java.awt.Font;
import model.EtatGUI;
import model.NumeroJoueur;

/**
 *
 * @author timotheetroncy
 */
public class ScreenUpdater {
    
    
    public static void updateScreen(JPanelQuarto jPQ, EtatGUI etat){
                switch (etat) {
            case J1DoitChoisir:
                jPQ.bDonnerJ1.setEnabled(false);
                jPQ.bDonnerJ2.setEnabled(false);
                jPQ.jPlateau.setEnabled(false);
                jPQ.jPanelListePieces.setEnabled(true);
                jPQ.jPieceJ2.setEnabled(false);
                jPQ.jPieceJ1.setEnabled(true);
                annoncerQuartoDisplay(jPQ, false);
                break;
            case J1DoitDonner:
                jPQ.bDonnerJ1.setEnabled(true);
                jPQ.jPlateau.setEnabled(false);
                jPQ.jPanelListePieces.setEnabled(true);
                jPQ.jPieceJ2.setEnabled(false);
                jPQ.jPieceJ1.setEnabled(true);
                annoncerQuartoDisplay(jPQ, false);
                break;
            case J1DoitPlacer:
                jPQ.bDonnerJ1.setEnabled(false);
                jPQ.bDonnerJ2.setEnabled(false);
                jPQ.jPlateau.setEnabled(true);
                jPQ.jPanelListePieces.setEnabled(false);
                jPQ.jPieceJ2.setEnabled(false);
                jPQ.jPieceJ1.setEnabled(true);
                annoncerQuartoDisplay(jPQ, true);
                break;
            case J2DoitChoisir:
                if (jPQ.controleur.onePlayer()) {
                    jPQ.jPanelListePieces.setEnabled(false);
                    jPQ.jPieceJ2.setEnabled(false);
                } else {
                    jPQ.jPanelListePieces.setEnabled(true);
                    jPQ.jPieceJ2.setEnabled(true);
                }
                jPQ.bDonnerJ1.setEnabled(false);
                jPQ.bDonnerJ2.setEnabled(false);
                jPQ.jPlateau.setEnabled(false);
                jPQ.jPieceJ1.setEnabled(false);
                annoncerQuartoDisplay(jPQ, false);
                break;
            case J2DoitDonner:
                if (jPQ.controleur.onePlayer()) {
                    jPQ.bDonnerJ2.setEnabled(false);
                    jPQ.jPanelListePieces.setEnabled(false);
                    jPQ.jPieceJ2.setEnabled(false);
                } else {
                    jPQ.bDonnerJ2.setEnabled(true);
                    jPQ.jPanelListePieces.setEnabled(true);
                    jPQ.jPieceJ2.setEnabled(true);
                }
                jPQ.bDonnerJ1.setEnabled(false);
                jPQ.jPlateau.setEnabled(false);
                jPQ.jPieceJ1.setEnabled(false);
                annoncerQuartoDisplay(jPQ, false);
                break;
            case J2DoitPlacer:
                if (jPQ.controleur.onePlayer()) {
                    jPQ.jPlateau.setEnabled(false);
                    jPQ.jPieceJ2.setEnabled(false);
                } else {
                    jPQ.jPlateau.setEnabled(true);
                    jPQ.jPieceJ2.setEnabled(true);
                }
                jPQ.bDonnerJ2.setEnabled(false);
                jPQ.bDonnerJ1.setEnabled(false);
                jPQ.jPanelListePieces.setEnabled(false);
                jPQ.jPieceJ1.setEnabled(false);
                annoncerQuartoDisplay(jPQ, true);
                break;
            case J1AAnnonceQuarto:
                break;
            case J2AAnnonceQuarto:
                break;
            case J2PeutConfirmerMatchNull:
                annoncerQuartoDisplay(jPQ, true);
                jPQ.bAnnoncerMatchNullJ1.setEnabled(false);
                jPQ.bAnnoncerMatchNullJ2.setEnabled(true);
                break;
            case J1PeutConfirmerMatchNull:
                annoncerQuartoDisplay(jPQ, true);
                jPQ.bAnnoncerMatchNullJ2.setEnabled(false);
                jPQ.bAnnoncerMatchNullJ1.setEnabled(true);
                break;
            case EtatNonDefinit:
                break;
            case J1DernierTour:
                annoncerQuartoDisplay(jPQ, false);
                jPQ.bAnnoncerMatchNullJ1.setEnabled(true);
                jPQ.bAnnoncerMatchNullJ2.setEnabled(false);
                break;
            case J2DernierTour:
                annoncerQuartoDisplay(jPQ, false);
                jPQ.bAnnoncerMatchNullJ2.setEnabled(true);
                jPQ.bAnnoncerMatchNullJ1.setEnabled(false);
                break;

            case J1ATrouveUnQuarto:
            case J2ATrouveUnQuarto:
            case J1EtJ2OntAnnonceMatchNull:
                jPQ.jPlateau.setEnabled(false);
                jPQ.jPanelListePieces.setEnabled(false);
                jPQ.bDonnerJ2.setEnabled(false);
                jPQ.bDonnerJ1.setEnabled(false);
                jPQ.bAnnoncerQuartoJ1.setEnabled(false);
                jPQ.bAnnoncerQuartoJ2.setEnabled(false);
                jPQ.bAnnoncerMatchNullJ1.setEnabled(false);
                jPQ.bAnnoncerMatchNullJ2.setEnabled(false);
                break;

            default:
                break;
        }
        majLabelJoueur(jPQ);
        jPQ.jTextArea1.setText("Le jeux est passé en état :" + etat);
        jPQ.revalidate();
        jPQ.repaint();
    }
    
        /**
     * Gère l'affichage des boutons AnnoncerQuarto selon le joueur qui joue
     */
    private static void annoncerQuartoDisplay(JPanelQuarto jPQ, Boolean quartoAdversaire) {

        NumeroJoueur numJoueurCourant = jPQ.controleur.getJoueurCourant();
        if (!jPQ.controleur.getIsValidationAutoEnabled()) {
            //tant qu'il n'y a pas de pièce posées on grise les boutons Quarto
            if (!jPQ.controleur.getListPiecePlateauJeu().isEmpty()) {
                if (numJoueurCourant == NumeroJoueur.J1) {
                    if (quartoAdversaire) {
                        jPQ.bAnnoncerQuartoJ1.setText("Quarto Adverse!");
                    } else {
                        jPQ.bAnnoncerQuartoJ1.setText("Quarto!");
                    }
                    jPQ.bAnnoncerQuartoJ1.setEnabled(true);
                    jPQ.bAnnoncerQuartoJ2.setEnabled(false);

                } else {

                    if (quartoAdversaire) {
                        jPQ.bAnnoncerQuartoJ2.setText("Quarto Adverse!");
                    } else {
                        jPQ.bAnnoncerQuartoJ2.setText("Quarto!");
                    }
                    jPQ.bAnnoncerQuartoJ2.setEnabled(true);
                    jPQ.bAnnoncerQuartoJ1.setEnabled(false);

                }
            } else {
                jPQ.bAnnoncerQuartoJ1.setEnabled(false);
                jPQ.bAnnoncerQuartoJ2.setEnabled(false);
            }
        } else {
            jPQ.bAnnoncerQuartoJ1.setVisible(false);
            jPQ.bAnnoncerQuartoJ2.setVisible(false);
        }
    }
    
        /**
     * Mis en valeur du joueur courant ( nom plus grand et en GRAS)
     */
    private static void majLabelJoueur(JPanelQuarto jPQ) {

        if (jPQ.controleur.getJoueurCourant() == NumeroJoueur.J1) {
            jPQ.jLabelJ1.setFont(new Font(jPQ.jLabelJ1.getFont().getName(), Font.BOLD, jPQ.fontSizeToUse));
            jPQ.jLabelJ2.setFont(new Font(jPQ.jLabelJ2.getFont().getName(), Font.PLAIN, jPQ.initFontSize));
        } else {
            jPQ.jLabelJ1.setFont(new Font(jPQ.jLabelJ1.getFont().getName(), Font.PLAIN, jPQ.initFontSize));
            jPQ.jLabelJ2.setFont(new Font(jPQ.jLabelJ2.getFont().getName(), Font.BOLD, jPQ.fontSizeToUse));
        }
    }
}
