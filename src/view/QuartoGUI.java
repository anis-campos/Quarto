/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

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

/**
 * @author Anis
 */
public final class QuartoGUI extends JPanel implements Observer {

    private HashMap<JPanel, Coord> mapCoordByCase;
    private HashMap<Coord, JPanel> mapCaseByCoord;

    //Map string=name  label
    private HashMap<String, JLabel> pieces;

    private JPanel jEntete;

    private JPanel jPlateau;

    private JPanel jPieces;

    private JPanel jPieceJ1;
    private JPanel jPieceJ2;

    private JButton bDonnerJ1;
    private JButton bDonnerJ2;

    private JButton bValiderJ1;
    private JButton bValiderJ2;

    private final IControlleur controleur;

    private EtatGUI etat;
    private JButton bAnnoncerQuartoJ1;
    private JButton bAnnoncerQuartoJ2;

    public QuartoGUI(IControlleur controleur) {

        super();

        this.controleur = controleur;

        initComponents();

        if (controleur.getJoueurCourant() == NumeroJoueur.J1) {
            etat = EtatGUI.J1DoitChoisir;
            UpdateScreen(etat);
        } else {
            etat = EtatGUI.J2DoitChoisir;
            UpdateScreen(etat);
        }

    }

    void initComponents() {

        //CONSTRUCTION DU PLATEAU
        /////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////
        jPlateau = new JPanel();

        GridLayout grid = new GridLayout(4, 4);
        grid.setHgap(5);
        grid.setVgap(5);
        jPlateau.setLayout(grid);

        mapCoordByCase = new HashMap<>();
        mapCaseByCoord = new HashMap<>();

        for (int i = 0; i < 16; i++) {
            JPanel jPanel = new JPanel();
            jPanel.setBackground(Color.WHITE);
            jPanel.setPreferredSize(new Dimension(100, 100));
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
        JLabel lab = new JLabel("QUARTO");
        lab.setFont(new Font("Arial", Font.BOLD, 48));
        jEntete.add(lab);
        /////////////////////////////////////////////////////////////////

        //CONSTRUCTION DES PIECES
        /////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////
        jPieces = new JPanel();

        pieces = new HashMap<>();

        grid = new GridLayout(2, 8);
        grid.setHgap(5);
        grid.setVgap(5);
        jPieces.setLayout(grid);
        jPieces.setBorder(BorderFactory.createTitledBorder("Liste des Pièces"));

        for (String piece : controleur.getListPieceDisponible()) {

            JLabel jLabel = new JLabel(getImageFile(piece));
            jLabel.setName(piece);
            //jLabel.setText(piece);

            pieces.put(piece, jLabel);

            jLabel.addMouseListener(new PieceClickListener());
            jLabel.setBackground(Color.WHITE);
            jLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            jLabel.setPreferredSize(new Dimension(100, 100));
            jPieces.add(jLabel);
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

        JLabel j1 = new JLabel("JOUEUR 1");
        jZoneJ1.add(j1);
        jZoneJ1.add(jPieceJ1);
        jZoneJ1.add(bDonnerJ1);
        j1.setAlignmentX(Component.CENTER_ALIGNMENT);
        bDonnerJ1.setAlignmentX(Component.CENTER_ALIGNMENT);
        bAnnoncerQuartoJ1 = new JButton("Annoncer Quarto");
        bAnnoncerQuartoJ1.setAlignmentX(CENTER_ALIGNMENT);
        bAnnoncerQuartoJ1.addActionListener(new ButtonAnnoncerQuartoClickListener());
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

        JLabel j2 = new JLabel("JOUEUR 2");
        bDonnerJ2 = new JButton("Donner à J1");
        bDonnerJ2.setAlignmentX(Component.CENTER_ALIGNMENT);

        bDonnerJ2.addActionListener(new ButtonDonnerClickListener());
        j2.setAlignmentX(Component.CENTER_ALIGNMENT);
        jZoneJ2.add(j2);
        jZoneJ2.add(jPieceJ2);
        jZoneJ2.add(bDonnerJ2);
        bAnnoncerQuartoJ2 = new JButton("Annoncer Quarto");
        bAnnoncerQuartoJ2.setAlignmentX(CENTER_ALIGNMENT);
        bAnnoncerQuartoJ2.addActionListener(new ButtonAnnoncerQuartoClickListener());
        /////////////////////////////////////////////////////////////////
        Box Centre = Box.createHorizontalBox();
        Centre.add(jZoneJ1);
        Centre.add(jPlateau);
        Centre.add(jZoneJ2);

        /*this.layeredPane = new JPanel();
         this.setContentPane(layeredPane);
         layeredPane.setLayout(new BorderLayout(20, 20));

         layeredPane.add(jEntete, BorderLayout.NORTH);
         layeredPane.add(Centre, BorderLayout.CENTER);
         layeredPane.add(jPieces, BorderLayout.SOUTH);*/
        this.setLayout(new BorderLayout(20, 20));

        this.add(jEntete, BorderLayout.NORTH);
        this.add(Centre, BorderLayout.CENTER);
        this.add(jPieces, BorderLayout.SOUTH);

    }

    public static ImageIcon getImageFile(String piece) {

        try {
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(QuartoGUI.class.getResourceAsStream("/images/" + piece + ".png")));
            Image image = imageIcon.getImage();
            Image scaledInstance = image.getScaledInstance(96, 96, java.awt.Image.SCALE_SMOOTH);

            return new ImageIcon(scaledInstance);
        } catch (IOException ex) {
            Logger.getLogger(QuartoGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private JPanel getPanelJoueur(NumeroJoueur num) {
        JPanel panel;
        if (num == NumeroJoueur.J1) {
            panel = jPieceJ1;
        } else {
            panel = jPieceJ2;
        }

        return panel;
    }

    @Override
    public void update(Observable o, Object arg) {
        //TODO : Deplacer les pieces
        Notification notif = (Notification) arg;

        if (notif instanceof NotificationPieceDonnee) {
            NotificationPieceDonnee donner = (NotificationPieceDonnee) notif;
            notifDonnerPiece(donner);
        } else if (notif instanceof NotificationPieceSelectionnee) {
            NotificationPieceSelectionnee selectionner = (NotificationPieceSelectionnee) notif;
            NotifSelectionnerPiece(selectionner);

        } else if (notif instanceof NotificationPiecePlacee) {
            NotificationPiecePlacee placee = (NotificationPiecePlacee) notif;
            NotifPlacerPiece(placee);
        }

        UpdateScreen(notif.nouvelEtat);
        this.revalidate();
        this.repaint();

    }

    private void notifDonnerPiece(NotificationPieceDonnee donner) {
        JPanel panelSource = getPanelJoueur(donner.joueurSource);
        JPanel panelDestination = getPanelJoueur(donner.joueurAdversaire);
        JLabel lab = (JLabel) panelSource.getComponent(0);
        panelSource.removeAll();
        panelDestination.add(cloneLabel(lab));
    }

    public JLabel cloneLabel(JLabel label) {
        JLabel lab = new JLabel(label.getIcon());
        lab.setName(label.getName());
        return lab;
    }

    private void NotifSelectionnerPiece(NotificationPieceSelectionnee selectionnee) {

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

    private void NotifPlacerPiece(NotificationPiecePlacee placee) {
        JPanel panel = mapCaseByCoord.get(placee.casePlateau);
        JPanel panelJoueur = getPanelJoueur(placee.joueurSource);
        if (panelJoueur.getComponentCount() == 1) {
            JLabel piece = (JLabel) panelJoueur.getComponent(0);
            panel.add(cloneLabel(piece));
            
        }

    }

    public class ButtonDonnerClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controleur.donnerPieceAdversaire();
        }

    }

    public class CaseClickListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            JPanel caseClickee = (JPanel) e.getSource();
            Coord coord = mapCoordByCase.get(caseClickee);
            JPanel panelJoueur = getPanelJoueur(controleur.getJoueurCourant());
            if (panelJoueur.getComponentCount() == 1) {
                JLabel piece = (JLabel) panelJoueur.getComponent(0);
                controleur.poserPiece(coord);
                panelJoueur.removeAll();
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

//Choix de la pièce
    public class PieceClickListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            JLabel lab = (JLabel) e.getSource();
            controleur.selectionPiece(lab.getName());

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

    public void UpdateScreen(EtatGUI etat) {
        switch (etat) {
            case J1DoitChoisir:
                bDonnerJ1.setVisible(false);
                bDonnerJ2.setVisible(false);
                annoncerQuartoEnableJ1();
                break;
            case J1DoitDonner:
                bDonnerJ1.setVisible(true);
                annoncerQuartoEnableJ1();
                break;
            case J1DoitPlacer:
                bDonnerJ2.setVisible(false);
                annoncerQuartoEnableJ1();
                break;
            case J2DoitChoisir:
                bDonnerJ1.setVisible(false);
                bDonnerJ2.setVisible(false);
                annoncerQuartoEnableJ2();
                break;
            case J2DoitDonner:
                bDonnerJ2.setVisible(true);
                annoncerQuartoEnableJ2();
                break;
            case J2DoitPlacer:
                bDonnerJ1.setVisible(false);
                annoncerQuartoEnableJ2();
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

    }

    private void annoncerQuartoEnableJ1() {
        if (controleur.getListPiecePlacee().size() <= 3) {
            bAnnoncerQuartoJ1.setVisible(true);
        }
    }

    private void annoncerQuartoEnableJ2() {
        if (controleur.getListPiecePlacee().size() <= 3) {
            bAnnoncerQuartoJ2.setVisible(false);
        }
    }
}
