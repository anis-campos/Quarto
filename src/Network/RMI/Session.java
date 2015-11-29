/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI;

import Databse.Compte;
import Databse.CompteDAL;
import Network.RMI.Interface.IClientCallback;
import Network.RMI.Interface.IJeu;
import Network.RMI.Interface.ISession;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import model.Joueur;
import model.NumeroJoueur;
import model.Parametre;
import model.Partie;
import org.apache.log4j.Logger;

/**
 *
 * @author Anis
 */
public class Session extends UnicastRemoteObject implements ISession {

    Compte CompteJoueur;

    Joueur joueur;

    IClientCallback Client;

    static ArrayList<PartieDistante> partiesEnAttente;

    private static final Logger logger = Logger.getLogger(Session.class);

    static {
        partiesEnAttente = new ArrayList<>();
    }
    private String clientHost;

    public Session(Compte compteJoueur, IClientCallback client) throws RemoteException {
        this.CompteJoueur = compteJoueur;
        this.Client = client;
        this.joueur = new Joueur(CompteJoueur.pseudo, false, NumeroJoueur.J1);
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

    }

    @Override
    public void registerCallback(IClientCallback client) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PartieItem creerPartie(Parametre p) throws RemoteException {
        PartieDistante partieDistante = new PartieDistante(p, joueur);
        partiesEnAttente.add(partieDistante);
        int index = partiesEnAttente.indexOf(partieDistante);
        return new PartieItem(index, partieDistante.J1.getName(), "N/A", p.toString());
    }

    @Override
    public IJeu creerPartieAvecAdversaire(Parametre p, Compte Adversaire) throws RemoteException {
        if (ServeurJeu.getInstance().exists(CompteJoueur, Adversaire)) {
            logger.info(String.format("Partie doublon !  Joueur: %s - IP: %s", CompteJoueur.pseudo, clientHost));
            throw new RemoteException("Vous avez deja une partie contre ce joueur !");
        }
        long idPartie = ServeurJeu.getInstance().creerPartie(p, joueur, new Joueur(Adversaire.pseudo, false, NumeroJoueur.J2));
        return new InterfaceJeu(Client, CompteJoueur, ServeurJeu.instance.find(idPartie));
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IJeu reprendrePartie(long partieID) throws RemoteException {
        return new InterfaceJeu(Client, CompteJoueur, ServeurJeu.instance.find(partieID));
    }

}
