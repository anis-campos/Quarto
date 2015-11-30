/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI;

import Network.RMI.Interface.IClientCallback;
import controlleur.ControlleurDistant;
import controlleur.observables.Notification;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import model.EtatGUI;
import model.Joueur;
import model.NumeroJoueur;
import org.apache.log4j.Logger;

/**
 *
 * @author Anis
 */
public class ClientCallback extends UnicastRemoteObject implements IClientCallback {

    private static final Logger logger = Logger.getLogger(ControlleurDistant.class);
    private final String nom;


    private final NumeroJoueur numJoueur;

    public ClientCallback(String nom, NumeroJoueur numJoueur) throws RemoteException{
        this.nom = nom;
        this.numJoueur = numJoueur;

    }

    @Override
    public void notifyMe(Notification notif) throws RemoteException {
        String message = String.format("[%s] - Notification Recu : %s", nom, notif.toString());
        logger.info(message);

        
    }


}
