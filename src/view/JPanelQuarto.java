/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.EtatGUI;
import controlleur.observables.Notification;
import controlleur.observables.NotificationPieceDonnee;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import model.Coord;
import model.NumeroJoueur;
import controlleur.IControlleur;
import controlleur.observables.NotificationPiecePlacee;
import controlleur.observables.NotificationPieceSelectionnee;
import controlleur.observables.NotificationQuartoDetecte;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Anis
 */
public final class JPanelQuarto extends JPanel implements Observer {

    private HashMap<JPanelCase, Coord> mapCoordByCase;
    private HashMap<Coord, JPanelCase> mapCaseByCoord;

    //Map string=name  label
    private HashMap<String, JLabelPiece> listeDePiecesDisponibles;

    private JPanel jEntete;

    private JPanel jPlateau;

    private JPanel jPanelListePieces;

    private JPanelCase jPieceJ1;
    private JPanelCase jPieceJ2;

    private JButton bDonnerJ1;
    private JButton bDonnerJ2;
    private JButton bAfficherMenu;

    private final IControlleur controleur;

    private JPanel layeredPane;

    private JButton bAnnoncerQuartoJ1;
    private JButton bAnnoncerQuartoJ2;

    private JLabel jLabelJ2;
    private JLabel jLabelJ1;
    private final int initFontSize = 12;
    private final int fontSizeToUse = 15;// TODO = A changer et faire mieux pour gérer les FONTs

    JTextArea jTextArea1;
    private final double pourcentagePiece;
    private final Dimension dimensionCase;

    public JPanelQuarto(IControlleur controleur, Dimension dimensionCase) {

        super();
        this.pourcentagePiece = 0.95;

        this.controleur = controleur;

        this.dimensionCase = dimensionCase;

        initComponents();

        updateScreen(controleur.getEtatCourant());

    }

    public void test() throws AWTException {

        Robot bot = new Robot();
        ArrayList<Point> listLocationPiece = new ArrayList<>();
        ArrayList<Point> listLocationCase = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listLocationPiece.add(jPanelListePieces.getComponent(i).getLocationOnScreen());
        }
        for (int i = 0; i < 10; i++) {
            listLocationCase.add(jPlateau.getComponent(i).getLocationOnScreen());
        }

        for (int i = 0; i < 4; i++) {
            robotClick(bot, listLocationPiece.get(i));
            Point bouton = i % 2 == 0 ? bDonnerJ1.getLocationOnScreen() : bDonnerJ2.getLocationOnScreen();
            robotClick(bot, bouton);
            robotClick(bot, listLocationCase.get(i));
        }

    }

    private void robotClick(Robot bot, Point p) {
        bot.mouseMove(p.x, p.y);
        bot.mousePress(InputEvent.BUTTON1_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_MASK);
        try {
            Thread.sleep(750);
        } catch (InterruptedException ex) {
            Logger.getLogger(JPanelQuarto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Fonction d'initialisation des composants de la GUI
     */
    private void initComponents() {

        //CONSTRUCTION DU PLATEAU
        /////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////
        jPlateau = new JPanel();

        GridLayout grid = new GridLayout(4, 4);
        grid.setHgap(5);
        grid.setVgap(5);
        jPlateau.setLayout(grid);
        jPlateau.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("enabled")) {
                    boolean etat = (boolean) evt.getNewValue();
                    for (Component component : jPlateau.getComponents()) {

                        component.setEnabled(etat);
                    }
                }
            }
        });
        mapCoordByCase = new HashMap<>();
        mapCaseByCoord = new HashMap<>();

        for (int i = 0; i < 16; i++) {
            Coord coord = new Coord(i / 4, i % 4);
            JPanelCase jPanel = new JPanelCase(dimensionCase, new CasePlateauClickAction(coord));
            mapCoordByCase.put(jPanel, coord);
            mapCaseByCoord.put(coord, jPanel);
            jPlateau.add(jPanel);
        }
        /////////////////////////////////////////////////////////////////

        //CONSTRUCTION DE L'ENTETE
        /////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////
        jEntete = new JPanel();
        jEntete.setLayout(new GridBagLayout());

        JLabel Titre = new JLabel("QUARTO");
        Titre.setFont(new Font("Arial", Font.BOLD, 48));

        bAfficherMenu = new JButton("Afficher le Menu");
        bAfficherMenu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) layeredPane.getParent().getParent().getLayout();
                cl.show(layeredPane.getParent().getParent(), "menu");
            }
        });

        jEntete.add(Titre);
        jEntete.add(bAfficherMenu);
        /////////////////////////////////////////////////////////////////

        //CONSTRUCTION DES PIECES
        /////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////
        jPanelListePieces = new JPanel();

        listeDePiecesDisponibles = new HashMap<>();

        grid = new GridLayout(2, 8);
        grid.setHgap(5);
        grid.setVgap(5);
        jPanelListePieces.setLayout(grid);
        jPanelListePieces.setBorder(BorderFactory.createTitledBorder("Liste des Pièces"));
        jPanelListePieces.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {

                if (evt.getPropertyName().equals("enabled")) {
                    for (Component piece : jPanelListePieces.getComponents()) {
                        piece.setEnabled((boolean) evt.getNewValue());
                    }
                }
            }
        });

        for (String piece : controleur.getListPieceDisponible()) {
            JLabelPiece jLabelPiece = new JLabelPiece(piece, dimensionCase, new PieceClickAction(piece));
            listeDePiecesDisponibles.put(piece, jLabelPiece);
            jPanelListePieces.add(jLabelPiece);
        }

        /////////////////////////////////////////////////////////////////
        //CONSTRUCTION DE LA ZONE JOUEUR 1
        /////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////
        Box jZoneJ1 = Box.createVerticalBox();
        jZoneJ1.setBackground(Color.red);
        jZoneJ1.setPreferredSize(new Dimension(210, 210));

        jPieceJ1 = new JPanelCase(dimensionCase, null);

        bDonnerJ1 = new JButton("Donner à " + controleur.getNomJoueur(NumeroJoueur.J2));
        bDonnerJ1.addActionListener(new ButtonDonnerClickListener());
        bDonnerJ1.setAlignmentX(Component.CENTER_ALIGNMENT);

        bAnnoncerQuartoJ1 = new JButton("Annoncer Quarto");
        bAnnoncerQuartoJ1.setAlignmentX(CENTER_ALIGNMENT);
        bAnnoncerQuartoJ1.addActionListener(new ButtonAnnoncerQuartoClickListener());

        jLabelJ1 = new JLabel(controleur.getNomJoueur(NumeroJoueur.J1));
        jLabelJ1.setAlignmentX(Component.CENTER_ALIGNMENT);
        jZoneJ1.add(jLabelJ1);
        jZoneJ1.add(jPieceJ1);
        jZoneJ1.add(bDonnerJ1);

        jZoneJ1.add(bAnnoncerQuartoJ1);
        /////////////////////////////////////////////////////////////////

        //CONSTRUCTION DE LA ZONE JOUEUR 2
        /////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////
        Box jZoneJ2 = Box.createVerticalBox();
        jZoneJ2.setBackground(Color.blue);
        jZoneJ2.setPreferredSize(new Dimension(210, 210));

        jPieceJ2 = new JPanelCase(dimensionCase, null);

        jLabelJ2 = new JLabel(controleur.getNomJoueur(NumeroJoueur.J2));

        bDonnerJ2 = new JButton("Donner à " + " " + controleur.getNomJoueur(NumeroJoueur.J1));
        bDonnerJ2.setAlignmentX(Component.CENTER_ALIGNMENT);
        bDonnerJ2.addActionListener(new ButtonDonnerClickListener());

        bAnnoncerQuartoJ2 = new JButton("Annoncer Quarto");
        bAnnoncerQuartoJ2.setAlignmentX(CENTER_ALIGNMENT);
        bAnnoncerQuartoJ2.addActionListener(new ButtonAnnoncerQuartoClickListener());

        jLabelJ2.setAlignmentX(Component.CENTER_ALIGNMENT);
        jZoneJ2.add(jLabelJ2);
        jZoneJ2.add(jPieceJ2);
        jZoneJ2.add(bDonnerJ2);
        jZoneJ2.add(bAnnoncerQuartoJ2);
        /////////////////////////////////////////////////////////////////
        Box Centre = Box.createHorizontalBox();
        Centre.add(jZoneJ1);
        Centre.add(jPlateau);
        Centre.add(jZoneJ2);

        Box Pied = Box.createVerticalBox();

        JPanel panel = new JPanel(new GridBagLayout());
        jTextArea1 = new JTextArea("Instructions sur le jeu");
        jTextArea1.setRows(2);
        jTextArea1.setFont(new Font("arial", Font.BOLD, 20));
        panel.add(jTextArea1);
        Pied.add(panel);
        Pied.add(Box.createRigidArea(new Dimension(1, 10)));
        Pied.add(jPanelListePieces);

        layeredPane = new JPanel();
        layeredPane.setLayout(new BorderLayout(20, 20));

        layeredPane.add(jEntete, BorderLayout.NORTH);
        layeredPane.add(Centre, BorderLayout.CENTER);
        layeredPane.add(Pied, BorderLayout.SOUTH);

        this.add(layeredPane);

    }

    /**
     * Recupère le JPanel du joueur ( contenant normalement une pièce )
     *
     * @param num Le NumeroJoueur du joueur
     * @return Le JPanel de ce joueur
     */
    private JPanelCase getPanelJoueur(NumeroJoueur num) {
        JPanelCase panel;
        if (num == NumeroJoueur.J1) {
            panel = jPieceJ1;
        } else {
            panel = jPieceJ2;
        }

        return panel;
    }
    
    

    /**
     * Met à jour la vue du jeu suite à une action du joueur
     *
     * @param o
     * @param arg Notification de l'action réalisée
     */
    @Override
    public void update(Observable o, Object arg) {
        //TODO : Deplacer les pieces
        Notification notif = (Notification) arg;

        if (notif instanceof NotificationPieceDonnee) {
            NotificationPieceDonnee donner = (NotificationPieceDonnee) notif;
            notifDonnerPiece(donner);
        } else if (notif instanceof NotificationPieceSelectionnee) {
            NotificationPieceSelectionnee selectionner = (NotificationPieceSelectionnee) notif;
            notifSelectionnerPiece(selectionner);

        } else if (notif instanceof NotificationPiecePlacee) {
            NotificationPiecePlacee placee = (NotificationPiecePlacee) notif;
            notifPlacerPiece(placee);
        }
        if (notif instanceof NotificationQuartoDetecte) {
            JFrame frame = (JFrame) SwingUtilities.getRoot(this);
            //  Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

            Icon icon;
            icon = new ImageIcon(GUIImageTool.getImage("/images/trophy.png"));
            JOptionPane.showMessageDialog(frame, "BRAVO '"+ controleur.getNomJoueur(notif.joueurSource)+"' , VOUS AVEZ GAGNE !!!", "Fin de Partie", JOptionPane.INFORMATION_MESSAGE, icon);

        }

        updateScreen(notif.nouvelEtat);
     
            
            
        this.revalidate();
        this.repaint();

    }

    /**
     * Met à jour l'interface à l'aide de la matrice d'état
     *
     * @param etat Etat du jeu (définit les éléments à afficher
     * activer/désactiver)
     */
    private void updateScreen(EtatGUI etat) {
        switch (etat) {
            case J1DoitChoisir:
                bDonnerJ1.setVisible(false);
                bDonnerJ2.setVisible(false);
                jPlateau.setEnabled(false);
                jPanelListePieces.setEnabled(true);
                 annoncerQuartoDisplay();
                break;
            case J1DoitDonner:
                bDonnerJ1.setVisible(true);
                jPlateau.setEnabled(false);
                jPanelListePieces.setEnabled(true);
                 annoncerQuartoDisplay();
                break;
            case J1DoitPlacer:
                bDonnerJ1.setVisible(false);
                bDonnerJ2.setVisible(false);
                jPlateau.setEnabled(true);
                jPanelListePieces.setEnabled(false);
                 annoncerQuartoDisplay();
                break;
            case J2DoitChoisir:
                bDonnerJ1.setVisible(false);
                bDonnerJ2.setVisible(false);
                jPlateau.setEnabled(false);
                jPanelListePieces.setEnabled(true);
                 annoncerQuartoDisplay();
                break;
            case J2DoitDonner:
                bDonnerJ1.setVisible(false);
                bDonnerJ2.setVisible(true);
                jPlateau.setEnabled(false);
                jPanelListePieces.setEnabled(true);
                 annoncerQuartoDisplay();
                break;
            case J2DoitPlacer:
                bDonnerJ2.setVisible(false);
                bDonnerJ1.setVisible(false);
                jPlateau.setEnabled(true);
                jPanelListePieces.setEnabled(false);
                 annoncerQuartoDisplay();
                break;
            case J1AAnnonceQuarto:
                break;
            case J2AAnnonceQuarto:
                break;
            case J2PeutConfirmerMatchNull:
                break;
            case J1PeutConfirmerMatchNull:
                break;
            case EtatNonDefinit:
                break;
            case J1DernierTour:
                break;
            case J2DernierTour:
                break;
                
                
            case J1ATrouveUnQuarto:
            case J2ATrouveUnQuarto:
            case J1EtJ2OntAnnoncerMatchNull:
                jPlateau.setEnabled(false);
                jPanelListePieces.setEnabled(false);
                bDonnerJ2.setVisible(false);
                bDonnerJ1.setVisible(false);
                bAnnoncerQuartoJ1.setVisible(false);
                bAnnoncerQuartoJ2.setVisible(false);
                break;
                
            default:
                break;
        }
        majLabelJoueur();
        jTextArea1.setText("Le jeux est passé en état :" + etat);
       
    }

    /**
     * Réalise l'action de donner une pièce à l'adversaire en terme d'interface
     *
     * @param donner Notification à l'interface après avoir mis à jour le model
     * (envoyée par le controller pour mettre à jour la vue)
     */
    private void notifDonnerPiece(NotificationPieceDonnee donner) {
        JPanelCase panelSource = getPanelJoueur(donner.joueurSource);
        JPanelCase panelDestination = getPanelJoueur(donner.joueurAdversaire);
        JLabelPiece lab = panelSource.getPiece();
        panelSource.removePiece();
        panelDestination.setPiece(lab.getClone());
    }

    /**
     * Réalise l'action de selectionner une pièce à partir de la liste de pièce
     *
     * @param selectionnee Notification de Selection de la pièce (envoyée par le
     * controlleur pour mettre à jour la vue)
     */
    private void notifSelectionnerPiece(NotificationPieceSelectionnee selectionnee) {

        JLabelPiece lab = listeDePiecesDisponibles.get(selectionnee.NomPiece);

        JPanelCase panelJoueurCourant;
        panelJoueurCourant = getPanelJoueur(selectionnee.joueurSource);

        if (!panelJoueurCourant.isEmpty()) {
            JLabelPiece labPresent = panelJoueurCourant.getPiece();
            JLabelPiece piece = listeDePiecesDisponibles.get(labPresent.getNomPiece());
            piece.setVisible(true);
            panelJoueurCourant.removeAll();
        }
        lab.setVisible(false);
        panelJoueurCourant.setPiece(lab.getClone());
    }

    /**
     * Place la pièce du joueur sur le plateau de jeu
     *
     * @param placee Notification du placement de la pièce sur le plateau
     */
    private void notifPlacerPiece(NotificationPiecePlacee placee) {
        JPanelCase casePlateau = mapCaseByCoord.get(placee.casePlateau);
        JPanelCase panelJoueur = getPanelJoueur(placee.joueurSource);
        if (!panelJoueur.isEmpty()) {
            JLabelPiece piece = panelJoueur.getPiece();
            casePlateau.setPiece(piece.getClone());
            panelJoueur.removePiece();
        }

    }

    /**
     * Gère l'affichage des boutons AnnoncerQuarto selon le joueur qui joue
     */
    private void annoncerQuartoDisplay() {
        if (controleur.getJoueurCourant() == NumeroJoueur.J1) {
            if (controleur.getListPiecePlacee().size() <= 3 || controleur.getIsValidationAutoEnabled()) {
                bAnnoncerQuartoJ1.setVisible(false);
                bAnnoncerQuartoJ2.setVisible(false);
            } else {
                bAnnoncerQuartoJ1.setVisible(true);
            }
        } else {
            if (controleur.getListPiecePlacee().size() <= 3) {
                bAnnoncerQuartoJ2.setVisible(false);
                bAnnoncerQuartoJ1.setVisible(false);
            } else {
                bAnnoncerQuartoJ1.setVisible(true);
            }
        }
    }

    /**
     * Mis en valeur du joueur courant ( nom plus grand et en GRAS)
     */
    private void majLabelJoueur() {

        if (controleur.getJoueurCourant() == NumeroJoueur.J1) {
            jLabelJ1.setFont(new Font(jLabelJ1.getFont().getName(), Font.BOLD, fontSizeToUse));
            jLabelJ2.setFont(new Font(jLabelJ2.getFont().getName(), Font.PLAIN, initFontSize));
        } else {
            jLabelJ1.setFont(new Font(jLabelJ1.getFont().getName(), Font.PLAIN, initFontSize));
            jLabelJ2.setFont(new Font(jLabelJ2.getFont().getName(), Font.BOLD, fontSizeToUse));
        }
    }

    //------------------------------------------------------------------------------------
    //--------------------- Classes Listeners pour les actions ---------------------------
    //------------------------------------------------------------------------------------
    /**
     * Action du click sur un bouton donner
     */
    private class ButtonDonnerClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controleur.donnerPieceAdversaire();
        }

    }

    /**
     * Action du click sur un bouton annoncer quarto
     */
    private class ButtonAnnoncerQuartoClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controleur.annoncerQuarto();
        }
    }

    /**
     * Action du click sur un pièce
     */
    private class PieceClickAction implements Runnable {

        private final String nomPiece;

        public PieceClickAction(String nomPiece) {
            this.nomPiece = nomPiece;
        }

        @Override
        public void run() {
            controleur.selectionPiece(nomPiece);
        }

    }

    /**
     * Action du click sur une case du plateau
     */
    private class CasePlateauClickAction implements Runnable {

        private Coord coord;

        public CasePlateauClickAction(Coord coord) {
            this.coord = coord;
        }

        @Override
        public void run() {
            controleur.poserPiece(coord);
        }

    }

}
