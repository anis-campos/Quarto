/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher.remote.view;

import Database.Compte;
import Network.RMI.Exceptions.PartieDoublonException;
import Network.RMI.Interface.IClientCallback;
import Network.RMI.Interface.IJeu;
import Network.RMI.Interface.ILogin;
import Network.RMI.Interface.ISession;
import Network.RMI.PartieItem;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import static launcher.local.PartieBuilder.repackPartieQuarto;
import launcher.remote.InterfaceControleurLocal;
import model.Parametre;
import view.GUIImageTool;
import view.JPanelQuarto;

/**
 *
 * @author Anis
 */
public class ModeReseau2 extends javax.swing.JPanel implements ISession {

    private final ILogin service;
    private final PanelCardlayout pane;

    private ISession session;

    private static ModeReseau2 instance;

    public static ModeReseau2 getInstance(ILogin service) {
        if (instance == null) {
            instance = new ModeReseau2(service);
        }
        return instance;
    }

    public static ISession getInstance() {
        return instance;
    }

    private IJeu jeuEnCour;
    private InterfaceControleurLocal interfaceControleurLocal;
    private final Image backgroundImage;

    /**
     * Creates new form MenuReseau
     *
     * @param service
     */
    private ModeReseau2(ILogin service) {
        initComponents();
        this.backgroundImage = GUIImageTool.getImage("/images/wood_texture.jpg");

        this.service = service;

        pane = new PanelCardlayout();
        Connexion connexion = new Connexion(service);
        pane.addPage("connexion", connexion);
        pane.toggleNavBar();
        this.add(pane, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(backgroundImage, 0, 0, this);
    }

    public void afficherParametrage() {
        pane.addPage("newPartie", new JPanelParametresReseau(session));
        pane.setCurrentPage("newPartie");
        repackPartieQuarto(pane);
        this.repaint();
    }

    public void setSession(ISession session) throws RemoteException {
        if (this.session == null) {
            this.session = session;
            pane.addPage("menu", new Menu(this.session));
            pane.setCurrentPage("menu");
            pane.toggleNavBar();
            jLabelNomJoueur.setVisible(true);
            Compte compteJoueurConnectee = session.getCompteJoueurConnectee();
            jLabelNomJoueur.setText(compteJoueurConnectee.NomPrenom());
            this.repaint();
        }
    }

    @Override
    public void logout() {
        try {
            session.logout();
        } catch (RemoteException ex) {
            Logger.getLogger(ModeReseau2.class.getName()).log(Level.SEVERE, null, ex);
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
                new Parametre(true, true, true, true, true, true, false, false, false),
                Adversaire);
        interfaceControleurLocal = new InterfaceControleurLocal(jeuEnCour);
        launch();
        return jeuEnCour;
    }

    private void launch() {

        JPanelQuarto panel = new JPanelQuartoRemote(interfaceControleurLocal, new Dimension(80, 80), jeuEnCour);
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

    void afficherList() throws RemoteException {
        ListePartie listeJeu = new ListePartie();
        try {
            listeJeu.initList(session.listePartie());
        } catch (NullPointerException ex) {
            System.out.println("Liste Vide");
        }
        pane.addPage("listeJeu", listeJeu);
        pane.setCurrentPage("listeJeu");
    }

    @Override
    public void terminerPartie(long partieID) throws RemoteException {
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

        jPanel1 = new javax.swing.JPanel();
        Titre = new javax.swing.JLabel();
        jLabelNomJoueur = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setOpaque(false);

        Titre.setFont(new java.awt.Font("Lucida Grande", 1, 36)); // NOI18N
        Titre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titre.setText("MODE RESEAU");

        jLabelNomJoueur.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNomJoueur.setText("Joueur :");
        jLabelNomJoueur.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Titre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelNomJoueur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(Titre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelNomJoueur)
                .addContainerGap())
        );

        jLabelNomJoueur.setVisible(false);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Titre;
    private javax.swing.JLabel jLabelNomJoueur;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
