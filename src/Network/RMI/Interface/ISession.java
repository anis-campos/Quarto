/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI.Interface;

import Network.RMI.PartieItem;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import model.*;

/**
 *
 * @author Anis
 */
public interface ISession extends Remote {

    void logout() throws RemoteException;

    void registerCallback(IClientCallback client) throws RemoteException;

    PartieItem creerPartie(Parametre p) throws RemoteException;

    IJeu creerPartieAvecAdversaire(Parametre p, Joueur Adversaire) throws RemoteException;

    IJeu rejoindrePartie(long partieID) throws RemoteException;

    IJeu reprendrePartie(long partieID) throws RemoteException;

    List<PartieItem> listePartie() throws RemoteException;

    List<Joueur> listeJoueurs() throws RemoteException;

}
