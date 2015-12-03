/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher.remote;

import Databse.Compte;
import Network.RMI.Exceptions.PartieDoublonException;
import Network.RMI.Interface.IClientCallback;
import Network.RMI.Interface.IJeu;
import Network.RMI.Interface.ILogin;
import Network.RMI.Interface.ISession;
import Network.RMI.PartieItem;
import java.awt.BorderLayout;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static launcher.local.PartieBuilder.repackPartieQuarto;
import model.Parametre;
import view.GUIResolutionTool;
import view.JPanelQuarto;

/**
 *
 * @author Anis
 */
public class ModeReseau extends JFrame implements ISession {

    private final ILogin service;
    private final TestPane pane;

    private ISession session;

    private static ModeReseau instance;

    public static ModeReseau getInstance(ILogin service) {
        if (instance == null) {
            instance = new ModeReseau(service);
        }
        return instance;
    }

    public static ISession getInstance() {
        return instance;
    }

    private IJeu jeuEnCour;
    private InterfaceControleurLocal interfaceControleurLocal;

    /**
     * Creates new form MenuReseau
     *
     * @param service
     */
    private ModeReseau(ILogin service) {
        initComponents();
        this.service = service;

        pane = new TestPane();
        Connexion connexion = new Connexion(service);
        pane.addPage("connexion", connexion);
        pane.toggleNavBar();
        this.getContentPane().add(pane, BorderLayout.CENTER);
    }

    public void setSession(ISession session) {
        if (this.session == null) {
            this.session = session;
            pane.addPage("menu", new Menu(this.session));
            pane.setCurrentPage(1);
            pane.toggleNavBar();
            this.repaint();
        }
    }

    @Override
    public void logout() {
        try {
            session.logout();
        } catch (RemoteException ex) {
            Logger.getLogger(ModeReseau.class.getName()).log(Level.SEVERE, null, ex);
        }
        session = null;
        pane.setCurrentPage("connexion");
        pane.toggleNavBar();
        this.repaint();

    }

    @Override
    public void registerCallback(IClientCallback client) throws RemoteException {
        session.registerCallback(client);
    }

    @Override
    public PartieItem creerPartie(Parametre p) throws RemoteException {
        session.creerPartie(p);
        return null;
    }

    @Override
    public IJeu creerPartieAvecAdversaire(Parametre p, Compte Adversaire) throws RemoteException, PartieDoublonException {
        jeuEnCour = session.creerPartieAvecAdversaire(
                new Parametre(true, true, true, true, true, true, false, false),
                Adversaire);
        interfaceControleurLocal = new InterfaceControleurLocal(jeuEnCour);
        launch();
        return jeuEnCour;
    }

    private void launch() {

        JPanelQuarto panel = new JPanelQuarto(interfaceControleurLocal, GUIResolutionTool.getSizeOfCase());
        interfaceControleurLocal.addObserver((Observer) panel);

        pane.addPage("jeu", panel);

        pane.setCurrentPage("jeu");

        repackPartieQuarto(pane);

    }

    @Override
    public IJeu rejoindrePartie(long partieID) throws RemoteException {
        jeuEnCour = session.rejoindrePartie(partieID);

        interfaceControleurLocal = new InterfaceControleurLocal(jeuEnCour);
        launch();
        return jeuEnCour;
    }

    @Override
    public IJeu reprendrePartie(long partieID) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PartieItem> listePartie() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Compte> listeComptes() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Compte getCompteJoueurConnectee() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Titre = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Titre.setFont(new java.awt.Font("Lucida Grande", 1, 36)); // NOI18N
        Titre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titre.setText("MODE RESEAU");
        getContentPane().add(Titre, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Titre;
    // End of variables declaration//GEN-END:variables

    void afficherList() throws RemoteException {
        ListePartie listeJeu = new ListePartie();
        listeJeu.initList(session.listePartie());
        pane.addPage("listeJeu", listeJeu);
        pane.setCurrentPage("listeJeu");
    }

}
