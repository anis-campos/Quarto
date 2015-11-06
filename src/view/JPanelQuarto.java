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
import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import model.Coord;
import model.NumeroJoueur;
import controlleur.IControlleur;
import controlleur.observables.NotificationPiecePlacee;
import controlleur.observables.NotificationPieceSelectionnee;
import controlleur.observables.NotificationQuartoDetecte;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import model.Joueur;

/**
 * @author Anis
 */
public final class JPanelQuarto extends JPanel implements Observer {

    private HashMap<JPanel, Coord> mapCoordByCase;
    private HashMap<Coord, JPanel> mapCaseByCoord;

    //Map string=name  label
    private HashMap<String, JLabel> pieces;

    private JPanel jEntete;

    private JPanel jPlateau;

    private JPanel jPanelListePieces;

    private JPanel jPieceJ1;
    private JPanel jPieceJ2;

    private JButton bDonnerJ1;
    private JButton bDonnerJ2;
    private JButton bAfficherMenu;

    private final IControlleur controleur;

    private JPanel layeredPane;

    private JButton bAnnoncerQuartoJ1;
    private JButton bAnnoncerQuartoJ2;

    private JLabel jLabelJ2;
    private JLabel jLabelJ1;
    private int initFontSize = 12;
    private int fontSizeToUse = 14;// TODO = A changer et faire mieux pour gérer les FONTs

    JTextArea jTextArea1;

    public JPanelQuarto(IControlleur controleur) {

        super();

        this.controleur = controleur;

        initComponents();

        updateScreen(controleur.getEtatCourant());

    }

    /**
     * Fonction d'initialisation des composants de la GUI
     */
    void initComponents() {

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
            JPanel jPanel = new JPanel();
            jPanel.setBackground(Color.WHITE);
            jPanel.setPreferredSize(new Dimension(100, 100));
            jPanel.setName("case no " + i);
            jPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            Coord coord = new Coord(i / 4, i % 4);
            mapCoordByCase.put(jPanel, coord);
            mapCaseByCoord.put(coord, jPanel);
            jPanel.addMouseListener(new CaseClickListener());
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

        pieces = new HashMap<>();

        grid = new GridLayout(2, 8);
        grid.setHgap(5);
        grid.setVgap(5);
        jPanelListePieces.setLayout(grid);
        jPanelListePieces.setBorder(BorderFactory.createTitledBorder("Liste des Pièces"));

        for (String piece : controleur.getListPieceDisponible()) {

            JLabel jLabel = new JLabel(getImageFile(piece));
            jLabel.setName(piece);
            //jLabel.setText(piece);

            pieces.put(piece, jLabel);

            jLabel.addMouseListener(new PieceClickListener());
            jLabel.setBackground(Color.WHITE);
            jLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            jLabel.setPreferredSize(new Dimension(100, 100));
            jPanelListePieces.add(jLabel);
        }

        /////////////////////////////////////////////////////////////////
        //CONSTRUCTION DE LA ZONE JOUEUR 1
        /////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////
        Box jZoneJ1 = Box.createVerticalBox();
        jZoneJ1.setBackground(Color.red);
        jZoneJ1.setPreferredSize(new Dimension(210, 210));

        jPieceJ1 = new JPanel();
        jPieceJ1.setPreferredSize(new Dimension(100, 100));
        jPieceJ1.setBackground(Color.white);
        jPieceJ1.setMaximumSize(new Dimension(100, 100));

        bDonnerJ1 = new JButton("Donner à J2");
        bDonnerJ1.addActionListener(new ButtonDonnerClickListener());
        bDonnerJ1.setAlignmentX(Component.CENTER_ALIGNMENT);

        bAnnoncerQuartoJ1 = new JButton("Annoncer Quarto");
        bAnnoncerQuartoJ1.setAlignmentX(CENTER_ALIGNMENT);
        bAnnoncerQuartoJ1.addActionListener(new ButtonAnnoncerQuartoClickListener());

        jLabelJ1 = new JLabel("JOUEUR 1");
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

        jPieceJ2 = new JPanel();
        jPieceJ2.setPreferredSize(new Dimension(100, 100));
        jPieceJ2.setBackground(Color.white);
        jPieceJ2.setMaximumSize(new Dimension(100, 100));

        jLabelJ2 = new JLabel("JOUEUR 2");

        bDonnerJ2 = new JButton("Donner à J1");
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
     * Permet de recuprer l'image d'une pièce grace à son nom
     *
     * @param piece Le nom de la pièce
     * @return Un ImageIcon de la pièce
     */
    public static ImageIcon getImageFile(String piece) {

        try {
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(JPanelQuarto.class.getResourceAsStream("/images/" + piece + ".png")));
            Image image = imageIcon.getImage();
            Image scaledInstance = image.getScaledInstance(96, 96, java.awt.Image.SCALE_SMOOTH);

            return new ImageIcon(scaledInstance);
        } catch (IOException ex) {
            Logger.getLogger(JPanelQuarto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Recupère le JPanel du joueur ( contenant normalement une pièce )
     *
     * @param num Le NumeroJoueur du joueur
     * @return Le JPanel de ce joueur
     */
    private JPanel getPanelJoueur(NumeroJoueur num) {
        JPanel panel;
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

            frame.pack();
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

            JDialog QuartoPopup = new JDialog(frame, "Fin de Partie", true);
            QuartoPopup.add(new JLabel("BRAVO VOUS AVEZ GANGEZ !!!"));
            QuartoPopup.pack();
            QuartoPopup.setLocation(dim.width / 2 - QuartoPopup.getSize().width / 2, dim.height / 2 - QuartoPopup.getSize().height / 2);
            QuartoPopup.setVisible(true);

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
    public void updateScreen(EtatGUI etat) {
        switch (etat) {
            case J1DoitChoisir:
                bDonnerJ1.setVisible(false);
                bDonnerJ2.setVisible(false);
                jPlateau.setEnabled(false);
                jPanelListePieces.setEnabled(true);
                break;
            case J1DoitDonner:
                bDonnerJ1.setVisible(true);
                jPlateau.setEnabled(false);
                jPanelListePieces.setEnabled(true);
                break;
            case J1DoitPlacer:
                bDonnerJ1.setVisible(false);
                bDonnerJ2.setVisible(false);
                jPlateau.setEnabled(true);
                jPanelListePieces.setEnabled(false);
                break;
            case J2DoitChoisir:
                bDonnerJ1.setVisible(false);
                bDonnerJ2.setVisible(false);
                jPlateau.setEnabled(false);
                jPanelListePieces.setEnabled(true);
                break;
            case J2DoitDonner:
                bDonnerJ1.setVisible(false);
                bDonnerJ2.setVisible(true);
                jPlateau.setEnabled(false);
                jPanelListePieces.setEnabled(true);
                break;
            case J2DoitPlacer:
                bDonnerJ2.setVisible(false);
                bDonnerJ1.setVisible(false);
                jPlateau.setEnabled(true);
                jPanelListePieces.setEnabled(false);
                break;
            case J1AAnnonceQuarto:

                break;
            case J2AAnnonceQuarto:

                break;

            case J1AAnnonceMatchNull:
                break;
            case J2AAnnonceMatchNull:
                break;
            default:
                break;
        }
        majLabelJoueur();
        jTextArea1.setText("Le jeux est passé en état :" + etat);
        annoncerQuartoDisplay();
    }

    /**
     * Réalise l'action de donner une pièce à l'adversaire en terme d'interface
     *
     * @param donner Notification à l'interface après avoir mis à jour le model
     * (envoyée par le controller pour mettre à jour la vue)
     */
    private void notifDonnerPiece(NotificationPieceDonnee donner) {
        JPanel panelSource = getPanelJoueur(donner.joueurSource);
        JPanel panelDestination = getPanelJoueur(donner.joueurAdversaire);
        JLabel lab = (JLabel) panelSource.getComponent(0);
        panelSource.removeAll();
        panelDestination.add(cloneLabel(lab));
    }

    /**
     * Clone un label passer en paramètre
     *
     * @param label Label à cloner
     * @return
     */
    public JLabel cloneLabel(JLabel label) {
        JLabel lab = new JLabel(label.getIcon());
        lab.setName(label.getName());
        return lab;
    }

    /**
     * Réalise l'action de selectionner une pièce à partir de la liste de pièce
     *
     * @param selectionnee Notification de Selection de la pièce (envoyée par le
     * controlleur pour mettre à jour la vue)
     */
    private void notifSelectionnerPiece(NotificationPieceSelectionnee selectionnee) {

        JLabel lab = pieces.get(selectionnee.NomPiece);

        JPanel panel;
        panel = getPanelJoueur(selectionnee.joueurSource);

        if (panel.getComponentCount() == 1) {
            JLabel labPresent = (JLabel) panel.getComponent(0);
            JLabel piece = pieces.get(labPresent.getName());
            piece.setVisible(true);
            panel.removeAll();
        }
        lab.setVisible(false);
        panel.add(cloneLabel(lab));
    }

    /**
     * Place la pièce du joueur sur le plateau de jeu
     *
     * @param placee Notification du placement de la pièce sur le plateau
     */
    private void notifPlacerPiece(NotificationPiecePlacee placee) {
        JPanel panel = mapCaseByCoord.get(placee.casePlateau);
        JPanel panelJoueur = getPanelJoueur(placee.joueurSource);
        if (panelJoueur.getComponentCount() == 1) {
            JLabel piece = (JLabel) panelJoueur.getComponent(0);
            panel.add(cloneLabel(piece));

        }

    }

    /**
     * Gère l'affichage des boutons AnnoncerQuarto selon le joueur qui joue
     */
    private void annoncerQuartoDisplay() {
        if (controleur.getJoueurCourant() == NumeroJoueur.J1) {
            if (controleur.getListPiecePlacee().size() <= 3) {
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

    //------------------------------------------------------------------------------------
    //--------------------- Classes Listeners pour les actions ---------------------------
    //------------------------------------------------------------------------------------
    public class ButtonDonnerClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controleur.donnerPieceAdversaire();
        }

    }

    public class CaseClickListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (((JPanel) e.getSource()).isEnabled()) {
                JPanel caseClickee = (JPanel) e.getSource();
                Coord coord = mapCoordByCase.get(caseClickee);
                JPanel panelJoueur = getPanelJoueur(controleur.getJoueurCourant());
                if (panelJoueur.getComponentCount() == 1) {
                    JLabel piece = (JLabel) panelJoueur.getComponent(0);
                    controleur.poserPiece(coord);
                    panelJoueur.removeAll();
                }

            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

    }

    private static class ButtonAnnoncerQuartoClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
//            controleur.annoncerQuarto();
        }
    }

    public class PieceClickListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (jPanelListePieces.isEnabled()) {
                JLabel lab = (JLabel) e.getSource();
                controleur.selectionPiece(lab.getName());
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    private void majLabelJoueur() {

        if (controleur.getJoueurCourant() == NumeroJoueur.J1) {
            jLabelJ1.setFont(new Font(jLabelJ1.getFont().getName(), Font.BOLD, fontSizeToUse));
            jLabelJ2.setFont(new Font(jLabelJ2.getFont().getName(), Font.PLAIN, initFontSize));
        } else {
            jLabelJ1.setFont(new Font(jLabelJ1.getFont().getName(), Font.BOLD, initFontSize));
            jLabelJ2.setFont(new Font(jLabelJ2.getFont().getName(), Font.PLAIN, fontSizeToUse));
        }
    }

}
