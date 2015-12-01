/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI;

import Databse.Compte;
import Databse.CompteDAL;
import Network.RMI.Exceptions.PartieDoublonException;
import Network.RMI.Interface.IClientCallback;
import Network.RMI.Interface.IJeu;
import Network.RMI.Interface.ISession;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import model.Joueur;
import model.NumeroJoueur;
import model.Parametre;
import org.apache.log4j.Logger;

/**
 *
 * @author Anis
 */
public class Session extends UnicastRemoteObject implements ISession {

    Compte CompteJoueur;

    IClientCallback Client;

    static ArrayList<PartieDistante> partiesEnAttente;

    private static final Logger logger = Logger.getLogger(Session.class);

    static {
        partiesEnAttente = new ArrayList<>();
    }
    private String clientHost;

    public Session(Compte compteJoueur) throws RemoteException {
        this.CompteJoueur = compteJoueur;

        try {
            this.clientHost = getClientHost();
        } catch (ServerNotActiveException ex) {
            this.clientHost = "N/A";
            logger.error("Impossible d'obtenir l'hote du client", ex);
        }
    }

    @Override
    public void logout() throws RemoteException {
        //TODO: Fermer proprement la connexion de ce client.
        //       Notifier de a fermeture
        logger.warn(String.format(" Joueur: %s - IP: %s - DECONNECTE", CompteJoueur.pseudo, clientHost));
        unexportObject(this, true);

    }

    @Override
    public PartieItem creerPartie(Parametre p) throws RemoteException {
        PartieDistante partieDistante = new PartieDistante(p, new Joueur(CompteJoueur.pseudo, false, NumeroJoueur.J1));
        partiesEnAttente.add(partieDistante);
        int index = partiesEnAttente.indexOf(partieDistante);
        return new PartieItem(index, partieDistante.J1.getName(), "N/A", p.toString());
    }

    /**
     *
     * @param p
     * @param Adversaire
     * @return
     * @throws RemoteException
     * @throws PartieDoublonException
     */
    @Override
    public IJeu creerPartieAvecAdversaire(Parametre p, Compte Adversaire) throws RemoteException,PartieDoublonException {
        if (ServeurJeu.getInstance().exists(CompteJoueur, Adversaire)) {
            logger.info(String.format("Partie doublon !  Joueur: %s - IP: %s", CompteJoueur.pseudo, clientHost));
            throw new PartieDoublonException(Adversaire);
        }
        long idPartie = ServeurJeu.getInstance().creerPartie(p, new Joueur(CompteJoueur.pseudo, false, NumeroJoueur.J1), new Joueur(Adversaire.pseudo, false, NumeroJoueur.J2));
        return new InterfaceJeu(CompteJoueur, ServeurJeu.instance.find(idPartie));
    }

    @Override
    public List<PartieItem> listePartie() throws RemoteException {
        return ServeurJeu.instance.getListItem();
    }

    @Override
    public List<Compte> listeComptes() throws RemoteException {
        return CompteDAL.getDAL().getComptes();
    }

    @Override
    public IJeu rejoindrePartie(long partieID) throws RemoteException {
        //TODO : Syncroniser la liste des parties imcomplete
        return new InterfaceJeu(CompteJoueur, ServeurJeu.instance.find(partieID));
    }

    @Override
    public IJeu reprendrePartie(long partieID) throws RemoteException {
        return new InterfaceJeu(CompteJoueur, ServeurJeu.instance.find(partieID));
    }

    @Override
    public Compte getCompteJoueurConnectee() throws RemoteException {
        return CompteJoueur;
    }

    @Override
    public void registerCallback(IClientCallback client) throws RemoteException {
        Client = client;
    }

}
