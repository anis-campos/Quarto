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
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import model.Joueur;
import model.NumeroJoueur;
import model.Parametre;
import model.Partie;

/**
 *
 * @author Anis
 */
public class Session extends UnicastRemoteObject implements ISession {

    Compte CompteJoueur;

    Joueur joueur;

    IClientCallback Client;

    static ArrayList<PartieDistante> partiesEnAttente;

    static {
        partiesEnAttente = new ArrayList<>();
    }

    public Session(Compte compteJoueur, IClientCallback client) throws RemoteException {
        this.CompteJoueur = compteJoueur;
        this.Client = client;
        this.joueur = new Joueur(CompteJoueur.pseudo, false, NumeroJoueur.J1);
    }

    @Override
    public void logout() throws RemoteException {
        //TODO: Fermer proprement la connexion de ce client.
        //       Notifier 
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
