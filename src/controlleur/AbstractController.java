/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import controlleur.observables.Notification;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import model.EtatGUI;
import model.NumeroJoueur;
import model.Partie;
import model.SortieGUI;

/**
 *
 * @author Anis
 */
public abstract class AbstractController extends Observable implements IControlleur {

    Partie partie;

    protected AbstractController(Partie partie) {
        this.partie = partie;

    }

    @Override
    public NumeroJoueur getJoueurCourant() {
        return partie.getNumeroJoueurCourant();
    }

    @Override
    public List<String> getListPiecePlacee() {
        return partie.getListPieceNamePlacees();
    }

    @Override
    public EtatGUI getEtatCourant() {
        return partie.getEtatGUI();
    }

    @Override
    public SortieGUI getSortieGui() {
        return partie.getSortieGUI();
    }

    @Override
    public String getNomJoueur(NumeroJoueur nj) {
        return partie.getNameJoueurFromNumero(nj);
    }

    @Override
    public Boolean getIsValidationAutoEnabled() {
        return partie.isValidationAutoEnabled();
    }

    @Override
    public List<Map.Entry<Integer, String>> getListPieceDisponible() {

        return partie.getListPieceNameDisponibles();
    }

    /**
     * Permet d'envoyer une notification.
     *
     * @param notif
     */
    protected void envoyerNotification(Notification notif) {
        setChanged();
        notifyObservers(notif);

    }

}
