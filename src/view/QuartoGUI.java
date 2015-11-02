/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controlleur.IControlleur;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

import model.Coord;
import model.Couleur;
import model.Joueur;
import model.NumeroJoueur;
import tools.ChessImageProvider;

/**
 * @author Anis
 */
public class QuartoGUI extends JFrame implements Observer {

    private Map<JPanel, Coord> cases;

    private Map<String, JLabel> pieces;

    private JPanel jEntete;

    private JPanel jPlateau;

    private JPanel jPieces;

    private JPanel jPieceJ1;
    private JPanel jPieceJ2;

    private JButton bDonnerJ1;
    private JButton bDonnerJ2;

    private NumeroJoueur courant;
    private final IControlleur controleur;

    

    private JPanel layeredPane;

    public QuartoGUI(IControlleur controleur) {

        super();

        this.controleur = controleur;

        initComponents();

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

        cases = new HashMap<>();

        for (int i = 0; i < 16; i++) {
            JPanel jPanel = new JPanel();
            jPanel.setBackground(Color.WHITE);
            jPanel.setPreferredSize(new Dimension(100, 100));
            jPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            cases.put(jPanel, new Coord((i - 1) / 4, (i - 1) % 4));
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
            
              JLabel jLabel = new JLabel();
              jLabel.setName(piece);
              
              pieces.put(piece, jLabel);
              
              
        }
        
        for (int i = 0; i < 16; i++) {
            
            JLabel jLabel = new JLabel();
            jLabel.setText("PIECE_" + i);

            pieces.put("PIECE_" + i, jLabel);

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
        bDonnerJ1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (jPieceJ1.getComponentCount() == 1) {
                    donnerPiece(((JLabel) jPieceJ1.getComponent(0)).getText());
                }
            }
        });

        JLabel j1 = new JLabel("JOUEUR 1");
        jZoneJ1.add(j1);
        jZoneJ1.add(jPieceJ1);
        jZoneJ1.add(bDonnerJ1);
        j1.setAlignmentX(Component.CENTER_ALIGNMENT);
        bDonnerJ1.setAlignmentX(Component.CENTER_ALIGNMENT);
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

        bDonnerJ2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (jPieceJ2.getComponentCount() == 1) {
                    donnerPiece(((JLabel) jPieceJ2.getComponent(0)).getText());
                }
            }
        }
        );

        j2.setAlignmentX(Component.CENTER_ALIGNMENT);
        jZoneJ2.add(j2);
        jZoneJ2.add(jPieceJ2);
        jZoneJ2.add(bDonnerJ2);

        /////////////////////////////////////////////////////////////////
        Box Centre = Box.createHorizontalBox();
        Centre.add(jZoneJ1);
        Centre.add(jPlateau);
        Centre.add(jZoneJ2);

        this.layeredPane = new JPanel();
        this.setContentPane(layeredPane);
        layeredPane.setLayout(new BorderLayout(20, 20));

        layeredPane.add(jEntete, BorderLayout.NORTH);
        layeredPane.add(Centre, BorderLayout.CENTER);
        layeredPane.add(jPieces, BorderLayout.SOUTH);

    }
    
     public static BufferedImage getImageFile(String piece) {
        

        try {
            return ImageIO.read(ChessImageProvider.class.getResourceAsStream("/images/"+piece+".png"));
        } catch (IOException ex) {
            Logger.getLogger(ChessImageProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    void donnerPiece(String nom) {
        if (courant == NumeroJoueur.J1) {
            if (jPieceJ2.getComponents().length > 0) {
                jPieceJ2.removeAll();
            }
            jPieceJ1.removeAll();
            jPieceJ2.add(new JLabel(nom));
            courant = NumeroJoueur.J2;
        } else {
            if (jPieceJ1.getComponents().length > 0) {
                jPieceJ1.removeAll();
            }
            jPieceJ2.removeAll();
            jPieceJ1.add(new JLabel(nom));
            courant = NumeroJoueur.J1;

        }
    }

    void placerPiece(JLabel lab) {
        if (courant == NumeroJoueur.J1) {
            if (jPieceJ1.getComponents().length > 0) {
                String nom = ((JLabel) jPieceJ1.getComponent(0)).getText();
                pieces.get(nom).setVisible(true);
                jPieceJ1.removeAll();
            }

            jPieceJ1.add(lab);

        } else {
            if (jPieceJ2.getComponents().length > 0) {
                String nom = ((JLabel) jPieceJ2.getComponent(0)).getText();
                pieces.get(nom).setVisible(true);
                jPieceJ2.removeAll();
            }
            jPieceJ2.add(lab);

        }
    }

    @Override
    public void update(Observable o, Object arg) {
        //TODO : Deplacer les pieces
    }

    public class PieceClickListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel lab = (JLabel) e.getSource();
            lab.setVisible(false);
            placerPiece(new JLabel(lab.getText()));

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

}
