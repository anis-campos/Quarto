/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher.remote;

import Network.RMI.ClientCallback;
import Network.RMI.Interface.IJeu;
import controlleur.IControlleur;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Coord;
import model.EtatGUI;
import model.NumeroJoueur;
import model.SortieGUI;

/**
 *
 * @author Anis
 */
public class InterfaceControleurLocal extends Observable implements IControlleur {

    private IJeu jeuDistant;
    ClientCallback callback;

    public InterfaceControleurLocal(IJeu jeuDistant) throws RemoteException {
        this.jeuDistant = jeuDistant;
        this.callback = new ClientCallback();
        jeuDistant.registerClientCallback(callback);
    }

    private Boolean transfererExceptionToGUI(Exception ex) {
        setChanged();
        notifyObservers(ex);
        Logger.getLogger(InterfaceControleurLocal.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }

    @Override
    public boolean poserPiece(Coord coord) {
        try {
            return jeuDistant.poserPiece(coord);
        } catch (RemoteException ex) {
            return transfererExceptionToGUI(ex);
        }
    }

    @Override
    public boolean donnerPieceAdversaire() {
        try {
            return jeuDistant.donnerPieceAdversaire();
        } catch (RemoteException ex) {
            return transfererExceptionToGUI(ex);
        }
    }

    @Override
    public boolean selectionPiece(int idPiece) {
        try {
            return jeuDistant.selectionPiece(idPiece);
        } catch (RemoteException ex) {
            return transfererExceptionToGUI(ex);
        }
    }

    @Override
    public boolean annoncerQuarto() {
        try {
            return jeuDistant.annoncerQuarto();
        } catch (RemoteException ex) {
            return transfererExceptionToGUI(ex);
        }
    }

    @Override
    public boolean annoncerMatchNul() {
        try {
            return jeuDistant.annoncerMatchNul();
        } catch (RemoteException ex) {
            return transfererExceptionToGUI(ex);
        }
    }

    @Override
    public NumeroJoueur getJoueurCourant() {
        try {
            return jeuDistant.getJoueurCourant();
        } catch (RemoteException ex) {
            transfererExceptionToGUI(ex);
            return null;
        }
    }

    @Override
    public EtatGUI getEtatCourant() {
        try {
            return jeuDistant.getEtatCourant();
        } catch (RemoteException ex) {
            transfererExceptionToGUI(ex);
            return null;
        }
    }

    @Override
    public SortieGUI getSortieGui() {
        try {
            return jeuDistant.getSortieGui();
        } catch (RemoteException ex) {
            transfererExceptionToGUI(ex);
            return null;
        }
    }

    @Override
    public List<Map.Entry<Integer, String>> getListPieceDisponible() {
        try {
            return jeuDistant.getListPieceDisponible();
        } catch (RemoteException ex) {
            transfererExceptionToGUI(ex);
            return null;
        }
    }

    @Override
    public List<String> getListPiecePlacee() {
        try {
            return jeuDistant.getListPiecePlacee();
        } catch (RemoteException ex) {
            transfererExceptionToGUI(ex);
            return null;
        }
    }

    @Override
    public String getNomJoueur(NumeroJoueur nj) {
        try {
            return jeuDistant.getNomJoueur(nj);
        } catch (RemoteException ex) {
            transfererExceptionToGUI(ex);
            return null;
        }
    }

    @Override
    public Boolean getIsValidationAutoEnabled() {
        try {
            return jeuDistant.getIsValidationAutoEnabled();
        } catch (RemoteException ex) {
            transfererExceptionToGUI(ex);
            return null;
        }
    }

    @Override
    public ArrayList<Coord> getAvailableCoords() {
        try {
            return jeuDistant.getAvailableCoords();
        } catch (RemoteException ex) {
            transfererExceptionToGUI(ex);
            return null;
        }
    }

    @Override
    public boolean onePlayer() {
        return true;
    }

    @Override
    public void addObserver(Observer observer) {
        super.addObserver(observer);
        callback.addObserver(observer);

    }

}
